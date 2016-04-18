package mirror.weather.bean.face;

import java.util.List;

public class PersonBean {
	
	private String person_id;
	private String tag;
	private String person_name;
	private List<GroupBean> group;
	private List<FaceBean> face;
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public List<GroupBean> getGroup() {
		return group;
	}
	public void setGroup(List<GroupBean> group) {
		this.group = group;
	}
	public List<FaceBean> getFace() {
		return face;
	}
	public void setFace(List<FaceBean> face) {
		this.face = face;
	}
	
	
}
