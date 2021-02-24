package gr.hua.group10.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.group10.dao.CourseDAO;
import gr.hua.group10.entities.Course;


@Controller
@RequestMapping(value = "/classes")
public class ClassesController {
	
	
@Autowired CourseDAO coursesDAO;

@RequestMapping(method = RequestMethod.GET)
	public String listClasses(Model model) {

		// get customers from dao
		List<Course> courses = coursesDAO.getCourses();

		// add the customers to the model
		model.addAttribute("courses", courses);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		return "classes2";
    	}
		return "classes";
	}
}
