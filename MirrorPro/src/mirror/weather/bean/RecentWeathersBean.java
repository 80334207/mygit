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
 * ������Ϣ���
 * @author liujia
 *
 */
public class RecentWeathersBean {
	private String city;					//��������
	private String cityid;					//���б���
	private DayBean today;					//����������Ϣ
	private List<DayBean> forecast;			//δ��Ԥ���б�
	private List<DayBean> history;			//��ʷ�����б�
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
