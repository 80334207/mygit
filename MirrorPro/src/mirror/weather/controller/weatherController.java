package mirror.weather.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mirror.weather.Tools.Tools;
import mirror.weather.Tools.URLRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.bean.ResultCitynameBean;
import mirror.weather.bean.ResultCtiyListBean;
import mirror.weather.bean.ResultRecentWeathersBean;

/**
     	   ┌─┐       ┌─┐
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
 * 
 * 控制器
 * @author liujia
 *
 */
@Controller
public class weatherController {
	
	
	@RequestMapping(value="/demolist")
	public String toList(){
		return "weather/demoList";
	}
	
	@RequestMapping(value="/interfacelist")
	public String toInterfaceList(){
		return "weather/interfaceList";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/citylist")
	public String cityList(HttpServletRequest request) throws UnsupportedEncodingException {

		URLRequest urlRequest = new URLRequest("weather","citylist", UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();

		Gson gson = new Gson();
		ResultCtiyListBean bean = gson.fromJson(result, ResultCtiyListBean.class);

		request.setAttribute("citylist", bean.getRetData());
		return "weather/citylist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/interface_citylist")
	public void interface_cityList(HttpServletRequest request,HttpServletResponse response) throws IOException {

		URLRequest urlRequest = new URLRequest("weather","citylist", UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();

		Gson gson = new Gson();
		ResultCtiyListBean bean = gson.fromJson(result, ResultCtiyListBean.class);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(bean));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/recentWeathers")
	public String recentWeathers(HttpServletRequest request) throws UnsupportedEncodingException{
		
		URLRequest urlRequest = new URLRequest("weather","recentweathers",UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();
		
		Gson gson = new Gson();
		ResultRecentWeathersBean bean = gson.fromJson(result, ResultRecentWeathersBean.class);
		request.setAttribute("Weathers", bean.getRetData());
		return "weather/weathers";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/interface_recentWeathers")
	public void interface_recentWeathers(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		URLRequest urlRequest = new URLRequest("weather","recentweathers",UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();
		
		Gson gson = new Gson();
		ResultRecentWeathersBean bean = gson.fromJson(result, ResultRecentWeathersBean.class);
				
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(bean));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/cityinfo")
	public String cityInfo(HttpServletRequest request) throws UnsupportedEncodingException{
		
		URLRequest urlRequest = new URLRequest("weather","cityinfo",UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();
		
		Gson gson = new Gson();
		ResultCitynameBean bean = gson.fromJson(result, ResultCitynameBean.class);
		
		request.setAttribute("cityinfo", bean.getRetData());
		return "weather/cityinfo";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/interface_cityinfo")
	public void interface_cityInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		URLRequest urlRequest = new URLRequest("weather","cityinfo",UrlConfig.getParameterMap(request));
		String result = urlRequest.getRequestParams();
		
		Gson gson = new Gson();
		ResultCitynameBean bean = gson.fromJson(result, ResultCitynameBean.class);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(bean));
	}
	
}
