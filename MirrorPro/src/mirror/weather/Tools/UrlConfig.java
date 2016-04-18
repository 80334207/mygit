package mirror.weather.Tools;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

/**
 * 		   ┌─┐       ┌─┐
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
 * 套接字配置类
 * @author liujia
 *
 */
public class UrlConfig {
	public static final Map<String, Object> HEADER = new HashMap<String,Object>();
	
	public static final int TIMEOUT = 30000;
	
	public static final String REQUESTMETHOD_GET = "GET";
	public static final String REQUESTMETHOD_POST = "POST";
	
	/*==================================百度天气接口==============================================*/
	
	
	
	public static final String BAIDU_HEADER_KEY = "apikey";
	public static final String BAIDU_HEADER_VALUE = "e792d65fd81ace6c1a416f46be0bfa73";
	/**
	 * 百度aipstore天气接口套接字配置信息----------可用城市列表接口
	 */
	public static final String BAIDU_CITYLISTURL = "http://apis.baidu.com/apistore/weatherservice/citylist";
	public static final Map<String, Object> BAIDU_PARAMS = new HashMap<String,Object>();
	/**
	 * 百度aipstore天气接口套接字配置信息----------天气查询带历史7天和未来4天
	 */
	public static final String BAIDU_RECENTWEATHERS = "http://apis.baidu.com/apistore/weatherservice/recentweathers";
	public static final Map<String, Object> BAIDU_RECENT_URLPARAMS = new HashMap<String,Object>();
	/**
	 * 百度aipstore天气接口套接字配置信息----------根据城市名称查询信息
	 */
	public static final String BAIDU_CITYINFOURL = "http://apis.baidu.com/apistore/weatherservice/cityname";
	public static final Map<String, Object> BAIDU_CITY_URLPARAMS = new HashMap<String,Object>();
	
	
	/*======================================================================================================*/
	
	/*==================================人脸识别face++接口==================================================*/
	
	/**
	 * face++人脸识别
	 */
	public static final String FACE_URL = "http://apicn.faceplusplus.com/v2/";
	public static final String FACE_API_KEY = "api_key";
	public static final String FACE_API_SECRET_KEY = "api_secret";
	public static final String FACE_API_VALUE = "7e37439f03b80f5a7d74db8029b81e6b";
	public static final String FACE_API_SECRET_VALUE = "JHHcToJZAUdsfflZNLHlxSPw1aGacJ01";

	
	
	/*===================================================================================================*/
	
	/*============================================数据存储路径==============================================*/
	public static final String FILE_PATH = "data/blog";
	public static final String FILE_EXT = ".xml";
	public static final String[] IDCHARTS = {"0","1","2","3","4","5","6","7","8","9",
											 "A","B","C","D","E","F","G","H","I","G"};
	/*============================================数据存储路径==============================================*/
	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getParameterMap(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		
	    // 参数Map
	    Map properties = request.getParameterMap();
	    // 返回值Map
	    Map returnMap = new HashMap();
	    Iterator entries = properties.entrySet().iterator();
	    Map.Entry entry;
	    String name = "";
	    String value = "";
	    while (entries.hasNext()) {
	        entry = (Map.Entry) entries.next();
	        name = (String) entry.getKey();
	        Object valueObj = entry.getValue();
	        if(null == valueObj){
	            value = "";
	        }else if(valueObj instanceof String[]){
	            String[] values = (String[])valueObj;
	            for(int i=0;i<values.length;i++){
	                value = values[i] + ",";
	            }
	            value = value.substring(0, value.length()-1);
	        }else{
	            value = valueObj.toString();
	        }
	        returnMap.put(name, value);
	    }
	    return returnMap;
	}
	
	public static String getRealPath() {
		String realPath = "";
		if(realPath == ""){
			realPath = UrlConfig.class.getResource("/").getPath();
		}
		return realPath.substring(1, realPath.indexOf("WEB-INF/"));
	}
	
	public static String getCustomCode(){
		String code = "";
		Random random = new Random();
		for (int i = 0;i < 6;i++){
			code += IDCHARTS[random.nextInt(20)];
		}
		return code;
	}

}
