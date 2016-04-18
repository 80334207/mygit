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

@Controller
public class SessionController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getSession")
	public String getSession(HttpServletRequest request) throws UnsupportedEncodingException{
		URLRequest urlRequest = new URLRequest("face","info/get_session", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		request.setAttribute("resultmap", resultmap);
		return "face/sessionResult";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/sessionStatus")
	public void sessionStatus(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","info/get_session", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
}
