package mirror.weather.Tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fr.report.script.function.ENNUMBER;

import mirror.weather.bean.HttpURLBean;
import mirror.weather.bean.ResponseBean;



/**
 *		   ┌─┐       ┌─┐
*       ┌──┘ ┴───────┘ ┴──┐
*       │                 │			草
*       │       ───       │
*       │  ─┬┘       └┬─  │			泥
*       │                 │
*       │       ─┴─       │			马
*       │                 │
*       └───┐         ┌───┘
*           │         │
*           │         │
*           │         │
*           │         └──────────────┐
*           │                        │
*           │                        ├─┐
*           │                        ┌─┘    
*           │                        │
*           └─┐  ┐  ┌───────┬──┐  ┌──┘         
*             │ ─┤ ─┤       │ ─┤ ─┤         
*             └──┴──┘       └──┴──┘
*              	      神兽保佑 
*                代码无BUG!  
 * 
 * Url请求类
 * @author liujia
 *
 */
public class URLRequest {
	
	private HttpURLBean bean;
	HttpURLConnection connection;
	
	public URLRequest() {
		
	}
	
	public URLRequest(String key,String type,Map<String, Object> param) {
		// 初始化请求套接字URL
		initUrl(key,type,param);
		// 初始化http链接
		_prase();
		
	}
	
	private void initUrl(String key, String type, Map<String, Object> param) {
		if (bean == null) {
			bean = new HttpURLBean();
			bean.setTimeout(UrlConfig.TIMEOUT);
			if (key.equals("weather")) {
				switch (type) {
					case "citylist":
						bean.setHttpUrl(UrlConfig.BAIDU_CITYLISTURL);
						if (param.isEmpty()) {
							UrlConfig.BAIDU_PARAMS.put("cityname", "北京");
							bean.setHttpArg(UrlConfig.BAIDU_PARAMS);
						} else {
							bean.setHttpArg(param);
						}
						bean.setExistHeader(true);
						break;
					case "recentweathers":
						bean.setHttpUrl(UrlConfig.BAIDU_RECENTWEATHERS);
						if (param.isEmpty()) {
							UrlConfig.BAIDU_PARAMS.put("cityname", "北京");
							UrlConfig.BAIDU_PARAMS.put("cityid", "101010100");
							bean.setHttpArg(UrlConfig.BAIDU_PARAMS);
						} else {
							bean.setHttpArg(param);
						}
						bean.setExistHeader(true);
						break;
					case "cityinfo":
						bean.setHttpUrl(UrlConfig.BAIDU_CITYINFOURL);
						if (param.isEmpty()) {
							UrlConfig.BAIDU_CITY_URLPARAMS.put("cityname", "北京");
							bean.setHttpArg(UrlConfig.BAIDU_CITY_URLPARAMS);
						} else {
							bean.setHttpArg(param);
						}
						bean.setExistHeader(true);
						break;				
				}			
				UrlConfig.HEADER.put(UrlConfig.BAIDU_HEADER_KEY, UrlConfig.BAIDU_HEADER_VALUE);
				bean.setHttpHeaderMap(UrlConfig.HEADER);
				bean.setRequestMethod(UrlConfig.REQUESTMETHOD_GET);
			} else if (key.equals("face")) {
				bean.setHttpUrl(UrlConfig.FACE_URL + type);
				param.put(UrlConfig.FACE_API_KEY, UrlConfig.FACE_API_VALUE);
				param.put(UrlConfig.FACE_API_SECRET_KEY, UrlConfig.FACE_API_SECRET_VALUE);
				bean.setHttpArg(param);
				bean.setExistHeader(false);
				bean.setRequestMethod(UrlConfig.REQUESTMETHOD_GET);
			}
		}
	}
	
	private void _prase(){
		try {
			URL url = new URL(bean.getHttpUrl()+"?"+bean.getHttpArg());
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setConnectTimeout(bean.getTimeout());
			connection.setRequestMethod(bean.getRequestMethod());
			if(bean.isExistHeader()){
				for(Map.Entry<String, Object> entry : UrlConfig.HEADER.entrySet()){
					connection.setRequestProperty(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			connection.connect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getRequestParams(){
		BufferedReader reader = null;
		String result = "";
		StringBuffer sbf = new StringBuffer();
		try {
			InputStream is = connection.getInputStream();	
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public Map<String,String> getParamsMap(HttpServletRequest request){
		Map<String,String> params = new HashMap<String,String>();
		Enumeration<String> enu = request.getParameterNames();
		while(enu.hasMoreElements()){
			String paramName = (String) enu.nextElement();
			String paramValue = (String) request.getParameter(paramName);
			params.put(paramName, paramValue);
		}
		return params;
	}
	
	
	public Map<String, String> getResult(){
		BufferedReader reader = null;
		Map<String, String> result = new HashMap<String, String>();
		String resultCode = "";
		StringBuffer sbf = new StringBuffer();
		try {
			InputStream is = null;
			if(connection.getResponseCode()==200){
				is = connection.getInputStream();	
				resultCode = "0";
			}else{
				is = connection.getErrorStream();
				resultCode = String.valueOf(connection.getResponseCode());
			}
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result.put("resultCode", resultCode);
			result.put("result", sbf.toString());			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public void writerOut(HttpServletResponse response,ResponseBean result){
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(result.getHtml());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
