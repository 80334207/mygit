package mirror.weather.bean;

import java.util.List;
/**
 *         ������       ������
*       �������� �ة��������������� �ة�����
*       ��                 ��			��
*       ��       ������       ��
*       ��  ���Щ�       ���Щ�  ��			��
*       ��                 ��
*       ��       ���ة�       ��			��
*       ��                 ��
*       ����������         ����������
*           ��         ��
*           ��         ��
*           ��         ��
*           ��         ��������������������������������
*           ��                        ��
*           ��                        ������
*           ��                        ������    
*           ��                        ��
*           ������  ��  �����������������Щ�����  ��������         
*             �� ���� ����       �� ���� ����         
*             �������ة�����       �������ة�����
*              	      ���ޱ��� 
*                ������BUG! 
 * ÿ��������Ϣ
 * @author liujia
 *
 */
public class DayBean {
	private String date;						//��������
	private String week;						//��������
	private String curTemp;						//��ǰ�¶�	
	private String aqi;							//pmֵ
	private String fengxiang;					//����
	private String fengli;						//����
	private String hightemp;					//����¶�
	private String lowtemp;						//����¶�
	private String type;						//����״̬
	private List<WeatherIndexBean> index;		//ָ���б�
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
