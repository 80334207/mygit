package mirror.weather.bean;
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
 * ÿ������ָ��
 * @author liujia
 *
 */
public class WeatherIndexBean {
	private String name;			//ָ��ָ��1����
	private String code;			//ָ�����
	private String index;			//�ȼ�
	private String details;			//����
	private String otherName;		//������Ϣ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
}
