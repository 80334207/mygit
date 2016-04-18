package mirror.weather.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import mirror.weather.Tools.UrlConfig;
import mirror.weather.Tools.XmlUtil;

@Service
public class WriteDataService {
	
	
	private String createID(){
		String id = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = format.format(new Date());
		id = now + "_" + UrlConfig.getCustomCode();
		return id;
	}
	
	public boolean insertBlog(Map<String, Object> params){
		boolean flog = false;
		String id = createID();
		params.put("id", id);
		params.put("create_time", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		params.put("author","ljstone");
		XmlUtil xmllist = new XmlUtil("datalist");
		XmlUtil xmldetail = new XmlUtil(id);
		if(xmllist.isExist("datalist")){
			if(xmllist.insertXml(params) > 0){
				xmldetail.CreateXml(params, "detail");
				flog = true;
			}
		}else{
			if(xmllist.CreateXml(params,"list")){
				xmldetail.CreateXml(params, "detail");
				flog = true;
			}
		}		
		return flog;
	} 
	
	public Map<String,Object> getBlogByID(String id){
		return new XmlUtil("datalist").queryDataFromXml(id);
	}
	
	public Map<String,Object> getBloglist(int curpage,int pagesize){
		return new XmlUtil("datalist").queryListFromXml(curpage, pagesize);
	}

}
