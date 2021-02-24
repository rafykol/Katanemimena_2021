package gr.hua.group10.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.group10.dao.CourseDAO;
import gr.hua.group10.dao.UsersDAO;
import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;
import gr.hua.group10.forms.CourseForm;

@Controller
public class CoursesController {


	@Autowired
	UsersDAO usersDAO;

	@Autowired
	CourseDAO courseDAO;
	

	@RequestMapping(value = "/admin/createcourse", method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {

		// Put current username to display
		model.put("username", getCurrentUsername());
		
        CourseForm myform = new CourseForm();
        model.put("courseForm", myform);
        
        List<Users> proflist = usersDAO.getUsersByRole("ROLE_PROF");
        
        model.put("proflist", proflist);        
        
        return "createcourse";
    }

     
	 @RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
	    public String processRegistration(@ModelAttribute("courseForm") @Valid CourseForm courseForm,
	            Model model, Model model2) {

			// Put current username to display
			model.addAttribute("username", getCurrentUsername());
	    	
	    	Course course = courseDAO.createCourse(courseForm);          

	        	// add the customers to the model
	        	model.addAttribute("courseForm", course );
				model.addAttribute("msg", " Created Successfully");
	        	
	        return "CourseSuccess";
	    }
	 
	 @RequestMapping(value = "/admin/printcourses", method = RequestMethod.GET)
		public String listCourses(Model model) {

			// Put current username to display
			model.addAttribute("username", getCurrentUsername());

			// get customers from dao
			List<Course> courses = courseDAO.getCourses();

			// add the customers to the model
			model.addAttribute("courses", courses);

			return "editcoursessearch";
		}
	 
		@RequestMapping(value = "/admin/searchcourses", method = RequestMethod.GET)
		public String searchUsersPage(Map<String, Object> model) {    
			// Put current username to display
			model.put("username", getCurrentUsername());
			
			Course myform = new Course();
		    model.put("searchCourses", myform);
			
		return "searchcourses";    
		}    
		

	@RequestMapping(value = "/admin/searchcourses", method = RequestMethod.POST)
	public String searchUsers(@ModelAttribute("searchCourses") Course courses,
	        Model model) {
		
		// Put current username to display
				model.addAttribute("username", getCurrentUsername());
				
		List<Course> courselist = courseDAO.getCoursesByName(courses.getName());
					
		// add the customers to the model
		model.addAttribute("courses", courselist);

		return "editcoursessearch";
	}
	 

	@RequestMapping(value = "/admin/editcourse", method = RequestMethod.POST, params = "EditButton2")
	public String edituserform(@ModelAttribute("editcourse") Course courses, Map<String, Object> model,
			@RequestParam String EditButton2) {

			// Put current username to display
			model.put("username", getCurrentUsername());
			
			Course course = courseDAO.getCourseByID(Integer.parseInt(EditButton2));

			model.put("course", course);
			
	        CourseForm myform = new CourseForm();
	        model.put("editcourse", myform);
	        
	        List<Users> proflist = usersDAO.getUsersByRole("ROLE_PROF");
	        
	        model.put("proflist", proflist);        
		

		return "editcourse";
	}
	
	//Save the edits
			@RequestMapping(value = "/admin/editcourse", method = RequestMethod.POST, params = "SaveButton2")
			public String saveEdits(@ModelAttribute("edituser") CourseForm courseForm, Map<String, Object> model,
					@RequestParam String SaveButton2) {

				// Put current username to display
				model.put("username", getCurrentUsername());

				Course mergedCourse = courseDAO.updateCourse(courseForm, Integer.parseInt(SaveButton2));

				//Display result
				model.put("msg", "Updated Successfully");
				model.put("courseForm", mergedCourse);

				return "CourseSuccess";

			}

	 
	 
	 
		//Get current user's username
		private String getCurrentUsername() {
			
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();

			return username;
		}
	
}
