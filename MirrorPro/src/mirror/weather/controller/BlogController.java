package mirror.weather.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;
import com.google.gson.Gson;

import mirror.weather.Tools.FileRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.service.WriteDataService;
import sun.misc.BASE64Decoder;

@Controller
public class BlogController {

	private final  String UPLOAD_TEMP_PATH = "/uploadfile/temp";
	
	
	@Autowired
	private WriteDataService service;

	public WriteDataService getService() {
		return service;
	}

	public void setService(WriteDataService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/top.do")
	public String blogTop(){
		return "/blog/top";
	}
	@RequestMapping(value="/footer.do")
	public String blogFooter(){
		return "/blog/footer";
	}
	
	@RequestMapping(value="/tologin.do")
	public String blogToLogin(){
		return "/blog/faceLogin";
	}
	
	
	@RequestMapping(value="/sendblog.do")
	public String sendBlog(HttpServletRequest request){
		return "/blog/sendblog";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveblog.do")
	public void saveBlog(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params = UrlConfig.getParameterMap(request);
		String result = "";
		if(service.insertBlog(params)){
			result = "success";
		}else{
			result = "failure";
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/blogdetail.do")
	public String blogdetail(HttpServletRequest request){
		String id = request.getParameter("id");
		request.setAttribute("bloglist", service.getBloglist(1, 5));
		request.setAttribute("pageinfo", service.getBlogByID(id));
		return "/blog/detail";
	}
	
	@RequestMapping(value="/verityface.do")
	public void verityFace(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JSONException {
		String imageBase64 = request.getParameter("img");
		
		BASE64Decoder decoder = new BASE64Decoder();
		String temp_path = WebUtils.getRealPath(request.getSession().getServletContext(), UPLOAD_TEMP_PATH);
		
		File tempdir = new File(temp_path);
		if(!tempdir.exists()){
			tempdir.mkdirs();
		}
		byte[] bytes = decoder.decodeBuffer(imageBase64);		
		
		HttpRequests requests = new HttpRequests(UrlConfig.FACE_API_VALUE, 
				UrlConfig.FACE_API_SECRET_VALUE, true,
				true);	
		
		FileRequest fileRequest = new FileRequest();
		
		JSONObject resultdata = new JSONObject();
		try {
			JSONObject result = requests.detectionDetect(new PostParameters().setImg(bytes));
			JSONArray array = result.getJSONArray("face");
			if(array.length() > 0){
				String groupids = fileRequest.requestGrouplist();				
				String[] idarr = groupids.split(",");
				JSONArray personarr = new JSONArray();
				for (int i = 0;i < idarr.length;i++) {
					JSONObject identifyresult = requests.recognitionIdentify(new PostParameters().setGroupId(idarr[i]).setImg(bytes));
					JSONArray  facearr = identifyresult.getJSONArray("face");
					JSONArray candidataArray = null;
					for (int j = 0;j < facearr.length(); j++){
						JSONObject obj = facearr.getJSONObject(j);
						candidataArray = obj.getJSONArray("candidate");
						if(candidataArray != null){
							for (int k=0;k<candidataArray.length();k++){
								personarr.put(candidataArray.getJSONObject(k));
							}	
						}else{
							throw new RuntimeException("人脸验证失败，face为空！");
						}
					}
				}
				
				BigDecimal max = new BigDecimal(0.000000);				
				for (int i=0;i < personarr.length();i++){
					JSONObject obj = personarr.getJSONObject(i);
					System.out.println(obj);
					BigDecimal temp = new BigDecimal(obj.getInt("confidence"));
					if(max.compareTo(temp)<0){
						max = temp;
						resultdata = obj;
					}
				}

			}
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resultdata.toString());
	}
	
}
