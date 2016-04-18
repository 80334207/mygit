package mirror.weather.Tools;


public class Tools {
	
	public static boolean isEmptyString(Object obj){
		String str = String.valueOf(obj);
		return str == null || str.length() == 0 || str.equals("null");
	}

	public static String returnValidStr(String currentStr) {
		try {
			if (currentStr == null)
				return "";
			return currentStr.trim();
		} catch (Exception e) {
			return " ";
		}
	}

	public static String toChi(String input) {
		try {
			if (input == null) {
				input = "";
			}
			byte[] bytes = input.getBytes("ISO8859-1");
			return returnValidStr(new String(bytes,"gbk"));
		} catch (Exception ex) {
		}
		return null;
	}

	public static String toChi1(String input) {
		try {
			if (input == null) {
				input = "";
			}
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes,"gbk");
		} catch (Exception ex) {
		}
		return null;
	}
	public static String toChi2(String input) {
        try {
            if (input == null) {
                input = "";
            }
            byte[] bytes = input.getBytes("ISO8859-1");
            return new String(bytes,"utf-8");
        } catch (Exception ex) {
        }
        return null;
    }
	
	public static String formatUrl(String surl,String channel){
		int index = surl.lastIndexOf("/");
		if(index == -1)
			index = surl.length();
		return surl.substring(0,index)+"/"+channel+surl.substring(index);
	}


	public static void main(String[] args){
		
	}
	
}
