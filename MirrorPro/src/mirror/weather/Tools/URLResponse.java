package mirror.weather.Tools;

import com.google.gson.Gson;

public class URLResponse {
	
	@SuppressWarnings("unchecked")
	public static String goToJsp(String type,String key,Class clazz,String result){
		String returnUrl="";		
		
		switch(result){
			case "453":
			break;
		}
		
		Gson gson = new Gson();
		try {
			Object obj = clazz.newInstance();
			obj = gson.fromJson(result, clazz);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return returnUrl;
	}
	
	
}
