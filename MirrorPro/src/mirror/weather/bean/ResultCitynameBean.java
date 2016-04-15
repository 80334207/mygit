package mirror.weather.bean;

public class ResultCitynameBean {
	private int errNum;
	private String errMsg;
	private CityWeatherBean retData;
	public int getErrNum() {
		return errNum;
	}
	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public CityWeatherBean getRetData() {
		return retData;
	}
	public void setRetData(CityWeatherBean retData) {
		this.retData = retData;
	}
}
