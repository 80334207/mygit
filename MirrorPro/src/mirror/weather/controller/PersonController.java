package mirror.weather.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import mirror.weather.Tools.URLRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.bean.face.ErrorBean;
import mirror.weather.bean.face.PersonBean;
import mirror.weather.bean.face.ResultGoupsBean;

@Controller
public class PersonController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/personCreate")
	public void personCreate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","person/create", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
	@RequestMapping(value="/personinfo")
	public String personInfo(HttpServletRequest request) throws UnsupportedEncodingException{
		URLRequest urlRequest = new URLRequest("face","person/get_info", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		if(String.valueOf(resultmap.get("resultCode")).equals("0")){
			PersonBean person = gson.fromJson(String.valueOf(resultmap.get("result")), PersonBean.class);
			request.setAttribute("personinfo", person);
		}else{
			ErrorBean errorBean = gson.fromJson(String.valueOf(resultmap.get("result")), ErrorBean.class);
			request.setAttribute("errormsg", errorBean);
		}		
		
		return "face/personInfo";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/personsetinfo")
	public void groupSetinfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","person/set_info", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/trainperson")
	public void trainPerson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","train/verify", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
}
