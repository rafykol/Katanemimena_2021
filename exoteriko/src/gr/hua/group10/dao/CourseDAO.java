package gr.hua.group10.dao;

import java.util.List;

import gr.hua.group10.entities.Course;
import gr.hua.group10.forms.CourseForm;

public interface CourseDAO {

	public List<Course> getCourses();
	
	public Course createCourse(CourseForm courseForm);
	
	public  List<Course> getCoursesByName(String name);
	
	public Course getCourseByID(int id);
	
	public Course updateCourse(CourseForm courseForm, int id);
}
