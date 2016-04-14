package mirror.weather.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mirror.weather.Tools.URLRequest;
import mirror.weather.Tools.UrlConfig;
import mirror.weather.bean.face.ErrorBean;
import mirror.weather.bean.face.GroupBean;
import mirror.weather.bean.face.ResultGoupsBean;


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
* 人脸识别控制器
* @author liujia
*
*/

@Controller
public class GroupController {
	
	
	@RequestMapping(value="/facedemolist")
	public String tofacelist(HttpServletRequest request) throws UnsupportedEncodingException{
		
		URLRequest urlRequest = new URLRequest("face","info/get_group_list", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		if(String.valueOf(resultmap.get("resultCode")).equals("0")){
			ResultGoupsBean groups = gson.fromJson(String.valueOf(resultmap.get("result")), ResultGoupsBean.class);
			request.setAttribute("grouplist", groups.getGroup());
		}else{
			ErrorBean errorBean = gson.fromJson(String.valueOf(resultmap.get("result")), ErrorBean.class);
			request.setAttribute("errormsg", errorBean);
		}
		
		return "face/facedemoList";
	}
	
	@RequestMapping(value="/interfacefacelist")
	public String facelist(){
		return "face/interfacefaceList";
	}
	
	/**
	 * 信息查询类接口
	 * @throws UnsupportedEncodingException 
	 */
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/grouplist")
	public String grouplist(HttpServletRequest request) throws UnsupportedEncodingException{		
		URLRequest urlRequest = new URLRequest("face","info/get_group_list", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		if(String.valueOf(resultmap.get("resultCode")).equals("0")){
			ResultGoupsBean groups = gson.fromJson(String.valueOf(resultmap.get("result")), ResultGoupsBean.class);
			request.setAttribute("grouplist", groups.getGroup());
		}else{
			ErrorBean errorBean = gson.fromJson(String.valueOf(resultmap.get("result")), ErrorBean.class);
			request.setAttribute("errormsg", errorBean);
		}
		return "face/groupList";
	} 
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/interface_grouplist")
	public void grouplist(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","info/get_group_list", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		if(String.valueOf(resultmap.get("resultCode")).equals("0")){
			ResultGoupsBean groups = gson.fromJson(String.valueOf(resultmap.get("result")), ResultGoupsBean.class);			
			response.getWriter().write(gson.toJson(groups));
		}else{
			ErrorBean errorBean = gson.fromJson(String.valueOf(resultmap.get("result")), ErrorBean.class);
			response.getWriter().write(gson.toJson(errorBean));
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/groupinfo")
	public String getGroupInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","group/get_info", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		String weburl = "";
		Gson gson = new Gson();
		if(String.valueOf(resultmap.get("resultCode")).equals("0")){
			GroupBean group = gson.fromJson(String.valueOf(resultmap.get("result")), GroupBean.class);
			request.setAttribute("groupinfo", group);
			weburl = "face/groupinfo";
			System.out.println(gson.toJson(group));
		}else{
			ErrorBean errorBean = gson.fromJson(String.valueOf(resultmap.get("result")), ErrorBean.class);
			request.setAttribute("errormsg", errorBean);
			weburl = "error";
		}
		
		return weburl;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/groupcreate")
	public void groupCreate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","group/create", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/groupsetinfo")
	public void groupSetinfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","group/set_info", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/groupdelete")
	public void groupDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","group/delete", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/traingroup")
	public void trainGroup(HttpServletRequest request,HttpServletResponse response) throws IOException{
		URLRequest urlRequest = new URLRequest("face","train/identify", UrlConfig.getParameterMap(request));
		Map<String, String> resultmap = urlRequest.getResult();
		
		Gson gson = new Gson();

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(resultmap));
	}
	
}
