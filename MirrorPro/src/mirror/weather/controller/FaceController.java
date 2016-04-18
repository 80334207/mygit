package mirror.weather.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.google.gson.Gson;

import mirror.weather.Tools.FileRequest;
import mirror.weather.Tools.URLRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.bean.face.ErrorBean;
import mirror.weather.bean.face.PersonBean;

@Controller
public class FaceController {
	
	private FileRequest filerequest = new FileRequest();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/faceAdd")
	public String faceAdd(HttpServletRequest request) throws UnsupportedEncodingException{
		
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
		return "face/faceAdd";
	}
	
	@RequestMapping(value="/toupload")
	public String toupload(HttpServletRequest request){
		String per_id = request.getParameter("person_id");
		request.setAttribute("person_id", per_id);
		return "face/index";
	}
	
	@RequestMapping(value="/faceImgAdd")
	public void faceImgAdd(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> paramsmap = filerequest.imgUpload(request);
			
			URLRequest urlRequest = new URLRequest("face","person/add_face", paramsmap);
			Map<String, String> result = urlRequest.getResult();
			
			Gson gson = new Gson();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value="/imgDetect")
	public String imgDetect(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String, Object> paramsmap = filerequest.imgDetected(request,null);

			request.setAttribute("face", paramsmap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "face/faceInfo";
	}
	
	
}
