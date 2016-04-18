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
 * 每日天气信息
 * @author liujia
 *
 */
public class DayBean {
	private String date;						//今天日期
	private String week;						//今日星期
	private String curTemp;						//当前温度	
	private String aqi;							//pm值
	private String fengxiang;					//风向
	private String fengli;						//风力
	private String hightemp;					//最高温度
	private String lowtemp;						//最低温度
	private String type;						//天气状态
	private List<WeatherIndexBean> index;		//指标列表
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getCurTemp() {
		return curTemp;
	}
	public void setCurTemp(String curTemp) {
		this.curTemp = curTemp;
	}
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getFengxiang() {
		return fengxiang;
	}
	public void setFengxiang(String fengxiang) {
		this.fengxiang = fengxiang;
	}
	public String getFengli() {
		return fengli;
	}
	public void setFengli(String fengli) {
		this.fengli = fengli;
	}
	public String getHightemp() {
		return hightemp;
	}
	public void setHightemp(String hightemp) {
		this.hightemp = hightemp;
	}
	public String getLowtemp() {
		return lowtemp;
	}
	public void setLowtemp(String lowtemp) {
		this.lowtemp = lowtemp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<WeatherIndexBean> getIndex() {
		return index;
	}
	public void setIndex(List<WeatherIndexBean> index) {
		this.index = index;
	}
}
