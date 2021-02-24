package gr.hua.group10.forms;

import javax.persistence.Column;

public class CourseForm {
	
	@Column
	private int userid;
	
	@Column
	private String newcourse;
	
	public CourseForm() {}

	public CourseForm(int user, String course) {
		super();
		this.userid = user;
		this.newcourse = course;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getNewcourse() {
		return newcourse;
	}

	public void setNewcourse(String newcourse) {
		this.newcourse = newcourse;
	}



	
}
