package mirror.weather.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import mirror.weather.Tools.FileRequest;
import mirror.weather.Tools.URLRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.bean.face.GroupBean;
import mirror.weather.bean.face.PersonBean;
import mirror.weather.bean.face.ResultGoupsBean;

@Controller
public class VerifyFaceController {
	
	
	
	@RequestMapping(value="/verifydemo")
	public String verifyDemo(HttpServletRequest request){
		return "verify/demo";
	}
	
	@RequestMapping(value="/verifyinterface")
	public String verifyInterface(HttpServletRequest request){
		return "verify/interface";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/verifySameperson")
	public void verifySameperson(HttpServletRequest request,HttpServletResponse response){
		try {			
			URLRequest urlRequest = new URLRequest("face","recognition/verify", UrlConfig.getParameterMap(request));
			Map<String, String> result = urlRequest.getResult();
			
			Gson gson = new Gson();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value="/interface_verify")
	public void interface_verify(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> responsemap = new HashMap<String, Object>();
		try {				
			FileRequest fileRequest = new FileRequest();
			responsemap = fileRequest.verifyGroup(request,null);
			
			JSONArray perlist = (JSONArray)responsemap.get("persons");
			BigDecimal max = new BigDecimal(0.000000);
			JSONObject person = null;
			for (int i=0;i < perlist.length();i++){
				JSONObject obj = perlist.getJSONObject(i);
				BigDecimal temp = new BigDecimal(obj.getInt("confidence"));
				if(max.compareTo(temp)<0){
					max = temp;
					person = obj;
				}
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(person.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
