package mirror.weather.bean;

import java.util.List;
/**
 *         ┌─┐       ┌─┐
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
 * 天气信息结果
 * @author liujia
 *
 */
public class RecentWeathersBean {
	private String city;					//城市名称
	private String cityid;					//城市编码
	private DayBean today;					//当天天气信息
	private List<DayBean> forecast;			//未来预报列表
	private List<DayBean> history;			//历史天气列表
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public DayBean getToday() {
		return today;
	}
	public void setToday(DayBean today) {
		this.today = today;
	}
	public List<DayBean> getForecast() {
		return forecast;
	}
	public void setForecast(List<DayBean> forecast) {
		this.forecast = forecast;
	}
	public List<DayBean> getHistory() {
		return history;
	}
	public void setHistory(List<DayBean> history) {
		this.history = history;
	}
}
