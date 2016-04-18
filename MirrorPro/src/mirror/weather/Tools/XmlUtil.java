package mirror.weather.Tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XmlUtil {
	
	private Document document;
	private SAXBuilder builder;
	
	public XmlUtil(String filename) {
		if(document == null){
			File file = new File(getFilePath(filename));
			if(file.exists()){
				builder = new SAXBuilder(false);
				try {
					document = builder.build(file);
				} catch (JDOMException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				document = new Document();
			}
		}
	}
	
	
	public boolean CreateXml(Map<String, Object> params,String type){
		boolean flog = false;
		Element root = null;
		if(type.equals("list")){
			root = new Element("datalist");
			root.setAttribute("rows", "1");
			//root.setAttribute("ids",String.valueOf(params.get("id")));
		}else{
			root = new Element("content");
		}
		document.addContent(root);
		
		if(!params.isEmpty()){
			Element data = null;
			if(type.equals("list")){
				data = new Element("data");
				data.setAttribute("id",String.valueOf(params.get("id")));
			}
			for (String key : params.keySet()){
				if(type.equals("list")){
					if(key.equals("id") || key.equals("title") || key.equals("create_time") || key.equals("author")){
						Element elements = new Element(key);
						elements.setText(String.valueOf(params.get(key)));
						data.addContent(elements);	
					}
				}else{	
					if(key.equals("content")){
						root.setText(String.valueOf(params.get(key)));
					}
				}
			}
			if(type.equals("list")){
				root.addContent(data);
			}
		}
		if(type.equals("list")){
			flog = writeIntoXml(document,"datalist");				
		}else{
			flog = writeIntoXml(document,String.valueOf(params.get("id")));	
		}
		return flog;
	}
	
	private boolean writeIntoXml(Document doc,String filename){
		boolean flog = false;
		Format format = Format.getPrettyFormat();
		XMLOutputter xmlout = new XMLOutputter(format);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(getFilePath(filename));				
			xmlout.output(doc, os);
			flog = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flog;
	}
	
	public int insertXml(Map<String,Object> params){
		int count = 0;
		if(document.hasRootElement()){
			Element root = document.getRootElement();
			if(!params.isEmpty()){
				Element data = new Element("data");
				data.setAttribute("id",String.valueOf(params.get("id")));
				for (String key : params.keySet()){
					if(key.equals("id") || key.equals("title") || key.equals("create_time") || key.equals("author")){
						Element elements = new Element(key);
						elements.setText(String.valueOf(params.get(key)));
						data.addContent(elements);	
					}
				}
				root.addContent(data);
				count++;
			}
			int rootAttribute = Integer.valueOf(root.getAttributeValue("rows"));
			root.setAttribute("rows", String.valueOf(rootAttribute+count));
//			String ids = root.getAttributeValue("ids");
//			ids += "," + String.valueOf(params.get("id"));
//			root.setAttribute("ids", ids);
		}
		writeIntoXml(document,"datalist");		
		return count;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryListFromXml(int curpage,int pagesize){
		Map<String,Object> resultmap = new HashMap<String,Object>();
		List<Map<String,Object>> resultlist = new ArrayList<Map<String,Object>>();
		int totalpage = 0;
		int totalrecord = 0;
		if(document.hasRootElement()){
			Element root = document.getRootElement();
			List<Element> datalist = root.getChildren();
			totalrecord = datalist.size();
			totalpage = getTotalPage(totalrecord,pagesize);
			if(datalist.size()>0){
				int start = curpage * pagesize - pagesize;
				int end = curpage * pagesize;
				for(int i = start;i < end;i++){
					if(i < datalist.size()){
						Element data = (Element) datalist.get(i);
						Map<String,Object> datamap = new HashMap<String,Object>();
						List<Element> valuelist = data.getChildren();
						for (int j = 0;j < valuelist.size();j++){
							Element value = (Element) valuelist.get(j);
							datamap.put(value.getName(), value.getText());
						}
						resultlist.add(datamap);
					}
				}
			}
		}
		resultmap.put("curpage", curpage);
		resultmap.put("totalrecord", totalrecord);
		resultmap.put("totalpage", totalpage);
		resultmap.put("datalist", resultlist);
		return resultmap;
	}
	
	private int getTotalPage(int totalrecord,int pagesize){
		int totalpage = 0;
		int n = totalrecord % pagesize;
		if (n == 0){
			totalpage = totalrecord / pagesize;
		}else{
			totalpage = (totalrecord / pagesize) + 1;
		}
		return totalpage;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> queryDataFromXml(String id){
		Map<String,Object> resultmap = new HashMap<String,Object>();
		Map<String,Object> curmap = new HashMap<String,Object>();
		Map<String,Object> nextmap = new HashMap<String,Object>();
		Map<String,Object> permap = new HashMap<String,Object>();
		// 读取列表值
		if(document.hasRootElement()){
			Element root = document.getRootElement();
			List<Element> datalist = root.getChildren();
			if(datalist.size() > 0){
				for (int i = 0;i < datalist.size();i++){
					Element data = (Element) datalist.get(i);
					String curid = data.getAttributeValue("id");
					if(id.equals(curid)){
						// 获取当前篇
						List<Element> valuelist = data.getChildren();
						for (Element value:valuelist){
							curmap.put(value.getName(), value.getText());
						}
						// 没有上一篇 
						Element nextdata = null;
						Element perdata = null;		
						if (i == 0){
							// 获取下一篇
							nextdata = (Element) datalist.get(i+1);
							for (Element value:(List<Element>)nextdata.getChildren()){
								nextmap.put(value.getName(), value.getText());
							}
						}else if(i == datalist.size() - 1){   // 没有下一篇
							// 获取上一篇
							perdata = (Element) datalist.get(i-1);
							for (Element value:(List<Element>)perdata.getChildren()){
								permap.put(value.getName(), value.getText());
							}
						}else{
							// 获取下一篇
							nextdata = (Element) datalist.get(i+1);
							for (Element value:(List<Element>)nextdata.getChildren()){
								nextmap.put(value.getName(), value.getText());
							}
							// 获取上一篇
							perdata = (Element) datalist.get(i-1);
							for (Element value:(List<Element>)perdata.getChildren()){
								permap.put(value.getName(), value.getText());
							}
						}
					}
				}
			}
		}
		// 读取内容值
		Document doc = getDataDoc(id);
		if(doc.hasRootElement()){
			Element root = doc.getRootElement();
			curmap.put(root.getName(), root.getText());
		}
		resultmap.put("curinfo", curmap);
		resultmap.put("nextinfo", nextmap);
		resultmap.put("perinfo", permap);
		return resultmap;
	}
	
	private Document getDataDoc(String id){
		File file = new File(getFilePath(id));
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			doc = builder.build(file);
		} catch (JDOMException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public boolean isExist(String filename){
		boolean flog = false;
		File file = new File(getFilePath(filename));
		if(file.exists()){
			flog = true;
		}
		return flog;
	}
	
	private String getFilePath(String filename){
		String filesavePath = UrlConfig.getRealPath() + UrlConfig.FILE_PATH;
		checkPath(filesavePath);
		return filesavePath + File.separator + filename + UrlConfig.FILE_EXT;
	}
	
	private void checkPath(String savepath){
		File file = new File(savepath);
		file.mkdirs();
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		String path = "E:\\eclipse_workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\MirrorPro\\data\\blog\\datalist.xml";
		path = path.replace("\\", "/");
		File file = new File(path);
		if(file.exists()){
			SAXBuilder builder = new SAXBuilder(false);		
			try {
				Document doc = builder.build(file);
				Element root = doc.getRootElement();
				List<Element> bloglist = root.getChildren();
				for (int i = 0;i < bloglist.size();i++){
					Element blog = (Element) bloglist.get(i);
					if(blog.getName().equals("id")){
						System.out.println("id:"+blog.getText());
					}else if(blog.getName().equals("title")){
						System.out.println("title:"+blog.getText());
					}else if(blog.getName().equals("create_time")){
						System.out.println("create_time:"+blog.getText());
					}else{
						System.out.println("author:"+blog.getText());
					}
				}
			} catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
