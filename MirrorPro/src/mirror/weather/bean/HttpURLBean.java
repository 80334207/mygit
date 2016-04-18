package mirror.weather.bean;

import java.util.Map;

import mirror.weather.Tools.CodeUtil;

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
 * @author 刘甲
 * 套接字类
 */
public class HttpURLBean {

	private String httpUrl;
	private String httpArg;
	private int timeout;



	private String requestMethod;
	private Map<String, Object> httpHeaderMap;
	private boolean existHeader;
	
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}

	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public boolean isExistHeader() {
		return existHeader;
	}
	public void setExistHeader(boolean existHeader) {
		this.existHeader = existHeader;
	}
	public String getHttpArg() {
		return httpArg;
	}
	public void setHttpArg(Map<String, Object> httpArg) {
		this.httpArg = this.ArrayToString(httpArg);
	}


	public Map<String, Object> getHttpHeaderMap() {
		return httpHeaderMap;
	}
	public void setHttpHeaderMap(Map<String, Object> httpHeaderMap) {
		this.httpHeaderMap = httpHeaderMap;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	private String ArrayToString(Map<String, Object> arg){
		String str = "";
		if (!arg.isEmpty()) {
			for (Map.Entry<String, Object> entry : arg.entrySet()) {
				str += entry.getKey() + "=" + CodeUtil.encode(String.valueOf(entry.getValue())) + "&";
			}
		}
		return str.substring(0, str.length()-1);
	}
}
