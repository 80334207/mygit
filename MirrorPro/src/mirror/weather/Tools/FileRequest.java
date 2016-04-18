package mirror.weather.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.util.WebUtils;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;


public class FileRequest {

	private final  String UPLOAD_PATH = "/uploadfile/face";
	private final  String UPLOAD_TEMP_PATH = "/uploadfile/temp";
	private final  int MAX_SIZE = 1024 * 1024 * 1;
	private final  long FILE_MAX_SIZE = 1024 * 1024 * 1;
	private final  String boundaryAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";

	
	public Map<String, Object> imgUpload(HttpServletRequest request){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		try {
			resultmap = FileUpload(request);
			
			File tempfile = (File) resultmap.get("tempfile");
			
			// 对IMG 进行人脸识别
			JSONArray array = requestImgDetected(tempfile);
			if(array.length() != 1){
				tempfile.delete();
				throw new RuntimeException("不是合理的img,无法识别脸部特征!");
			}else{
				String face_id = getFace_id(array);
				String ext = getFileEXT(tempfile);
				String newfilepath = WebUtils.getRealPath(request.getSession().getServletContext(), 
						UPLOAD_PATH + "/" + face_id + "." + ext);
				// 用face_id重命名文件
				File newFile = new File(newfilepath);
				tempfile.renameTo(newFile);
				resultmap.put("face_id", face_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultmap;
	}
	
	
	public Map<String, Object> imgDetected(HttpServletRequest request,File imagefile){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		try {
			File tempfile = null;
			if(imagefile == null){
				resultmap = FileUpload(request);				
				tempfile = (File) resultmap.get("tempfile");	
			}else{
				imagefile = tempfile;
			}
			
			// 对IMG 进行人脸识别
			JSONArray array = requestImgDetected(tempfile);
			if(array.length() == 0){
				tempfile.delete();
				throw new RuntimeException("不是合理的img,无法识别脸部特征!");
			}else{
				String face_id = getFace_id(array);
				tempfile.delete();
				resultmap.put("face_id", face_id);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultmap;
	}
	
	public Map<String, Object> verifyGroup(HttpServletRequest request,File imagefile){
		Map<String, Object> resultmap = new HashMap<String, Object>();
		try {
			File tempfile = null;
			if(imagefile == null){
				resultmap = FileUpload(request);
				tempfile = (File) resultmap.get("tempfile");
			}else{
				tempfile = imagefile; 
			} 
			
			String groupids = requestGrouplist();
			String[] idarr = groupids.split(",");
			JSONArray personarr = new JSONArray();
			for (int i = 0;i < idarr.length;i++) {
				JSONArray arr = requestVerifygroup(tempfile, idarr[i]);
				if(arr != null){
					for (int j=0;j<arr.length();j++){
						JSONObject obj = arr.getJSONObject(j);
						personarr.put(obj);
					}	
				}else{
					throw new RuntimeException("人脸验证失败，face为空！");
				}
			}
			resultmap.put("persons", personarr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultmap;
	}
	
	
	@SuppressWarnings({ "rawtypes" })
	public Map<String,Object> FileUpload(HttpServletRequest request)
			throws UnsupportedEncodingException, FileNotFoundException {

		request.setCharacterEncoding("UTF-8");
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		
		boolean isupload = ServletFileUpload.isMultipartContent(request);

		if (isupload) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			String temp_path = WebUtils.getRealPath(request.getSession().getServletContext(), UPLOAD_TEMP_PATH);
			String save_path = WebUtils.getRealPath(request.getSession().getServletContext(), UPLOAD_PATH);
			File tempfile = new File(temp_path);
			File savefile = new File(save_path);
			tempfile.mkdirs();
			savefile.mkdirs();
			factory.setRepository(tempfile);
			factory.setSizeThreshold(MAX_SIZE);

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(FILE_MAX_SIZE);

			String[] filetype = new String[] { "jpg", "jpeg", "png", "bmp", "gif" };

			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						resultmap.put(item.getFieldName(), item.getString());						
					} else {
						String filename = item.getName();
						String ext = filename.substring(filename.lastIndexOf(".") + 1);
						boolean isexist = false;
						for (int i = 0; i < filetype.length; i++) {
							if (ext.equals(filetype[i])) {
								isexist = true;
							}
						}
						if (isexist) {							
							String tempfilename = "temp." + ext;							
							String temppath = UPLOAD_PATH + "/" + tempfilename;							
							String tempsavepath = WebUtils.getRealPath(request.getSession().getServletContext(), temppath);
							File file = new File(tempsavepath);
							item.write(file);
							resultmap.put("tempfile", file);
						} else {
							throw new RuntimeException("文件类型" + ext + "不支持!");
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return resultmap;

	}
	
	private String getFileEXT(File file){
		String ext = "";
		try {
			if (file != null){
				String filename = file.getName();
				ext = filename.substring(filename.lastIndexOf(".")+1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ext;
	}
	
	public String requestGrouplist(){
		HttpRequests requests = new HttpRequests(UrlConfig.FACE_API_VALUE, UrlConfig.FACE_API_SECRET_VALUE, true,
				true);	
		JSONObject result;
		JSONArray array = null;
		String groupids = "";
		try {
			result = requests.infoGetGroupList();
			array = result.getJSONArray("group");
			for (int i = 0;i < array.length();i++) {
				JSONObject obj = array.getJSONObject(i);
				groupids += obj.getString("group_id") + ",";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return groupids.substring(0,groupids.length()-1);
	}
	
	private JSONArray requestImgDetected(File file){
		// 对IMG 进行人脸识别
		HttpRequests requests = new HttpRequests(UrlConfig.FACE_API_VALUE, UrlConfig.FACE_API_SECRET_VALUE, true,
				true);			
		JSONObject result;
		JSONArray array = null;
		try {
			result = requests.detectionDetect(new PostParameters().setImg(file));
			array = result.getJSONArray("face");			
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}		
		return array;
	}
	
	private JSONArray requestVerifygroup(File file,String groupid){
		HttpRequests requests = new HttpRequests(UrlConfig.FACE_API_VALUE, UrlConfig.FACE_API_SECRET_VALUE, true,
				true);		
		JSONObject result;
		JSONArray array = null;
		try {
			result = requests.recognitionIdentify(new PostParameters().setGroupId(groupid).setImg(file));
			JSONArray  facearr = result.getJSONArray("face");
			for (int i = 0;i < facearr.length(); i++){
				JSONObject obj = facearr.getJSONObject(i);
				array = obj.getJSONArray("candidate");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return array;
	}
	
	
    public String getFace_id(JSONArray array){
    	String face_id="";
		try {
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				face_id += obj.getString("face_id") + ",";
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
    	return face_id.substring(0,face_id.length()-1);
    }
    
    
	
	/**
	 * auto generate boundary string
	 * @return a boundary string
	 */
	private String getBoundary() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 32; ++i)
			sb.append(boundaryAlphabet.charAt(random.nextInt(boundaryAlphabet.length())));
		return sb.toString();
	}

}
