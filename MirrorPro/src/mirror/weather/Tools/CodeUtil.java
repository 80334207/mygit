package mirror.weather.Tools;

/**
 * 		   ������       ������
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
*                
*  ���빤��
 * @author liujia
 *
 */
public class CodeUtil {

	
	public static String encode(String value){
		String str = "";
		try {
			if(value.equals("")){
				str = "";
			}else{
				str = java.net.URLEncoder.encode(value,"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}
	
	
	public static String decode(String value){
		String str = "";
		try {
			if(value.equals("")){
				str = "";
			}else{
				str = java.net.URLDecoder.decode(value,"UTF-8");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}
}
