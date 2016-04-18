package mirror.weather.bean.face;

import java.util.List;

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
* 
* 
* 组数据映射
* @author liujia
*
*/

public class GroupBean {
	
	private String group_id;
	private String tag;
	private String group_name;
	private int added_person;
	private List<PersonBean> person;
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	public int getAdded_person() {
		return added_person;
	}
	public void setAdded_person(int added_person) {
		this.added_person = added_person;
	}
	public List<PersonBean> getPerson() {
		return person;
	}
	public void setPerson(List<PersonBean> person) {
		this.person = person;
	}
	
}
