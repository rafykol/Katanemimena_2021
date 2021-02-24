package gr.hua.group10.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gr.hua.group10.dao.UsersDAO;
import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;

@Controller
public class UsersController {

	@Autowired
	UsersDAO usersDAO;
	
	// Admin - GET create user page
	@RequestMapping(value = "/admin/createuser", method = RequestMethod.GET)
	public String getCreateUser(Map<String, Object> model) {
		
		// Put current username to display
		model.put("username", getCurrentUsername());

		//Connect Users to form
		Users myform = new Users();
		model.put("userForm", myform);

		// Put options to select in form
		// Role select
		List<String> roleList = new ArrayList<>();
		roleList.add("ROLE_ADMIN");
		roleList.add("ROLE_PROF");
		roleList.add("ROLE_STUDENT");
		roleList.add("ROLE_SEC");
		roleList.add("ROLE_MGA");
		model.put("roleList", roleList);

		// enabled select
		List<Integer> enabledList = new ArrayList<>();
		enabledList.add(1);
		enabledList.add(0);
		model.put("enabledList", enabledList);
		
		model.put("error", 0);

		return "create_user";
	}
	
	// Admin - Create user page POST
		@RequestMapping(value = "/admin/createuser", method = RequestMethod.POST)
		public String postCreateUser(@ModelAttribute("userForm") Users user, Map<String, Object> model, RedirectAttributes redirectAttributes)  {

			// Put current username to display
			model.put("username", getCurrentUsername());

			//Create user
			int result = usersDAO.createUser(user);
			
			String msg;
			
			//Check for errors
			if(result == 1) {
				model.put("user", user);
				model.put("msg", " Created Successfully");
				return "userSuccess";
			
			}else {
				redirectAttributes.addFlashAttribute("errorp", 1);
				return"redirect:/admin/createuser";
			}
			
		}
		
		//Print Users
		@RequestMapping(value = "/admin/printusers", method = RequestMethod.GET)
		public String printUsers(Model model) {

			// Put current username to display
			model.addAttribute("username", getCurrentUsername());
	    	
			// get users from dao
			List<Users> users = usersDAO.getUsers();

			// add the users to the model
			model.addAttribute("users", users);

			return "edituserssearch";
		}

		// Admin - Search users
		@RequestMapping(value = "/admin/searchusers", method = RequestMethod.GET)
		public String searchUsersPage(Map<String, Object> model) {

			// Put current username to display
			model.put("username", getCurrentUsername());
			
			Users myform2 = new Users();
			model.put("searchUsersForm", myform2);

			return "searchusers";
		}
		
		@RequestMapping(value = "/admin/searchusers", method = RequestMethod.POST)
		public String searchUsers(@ModelAttribute("searchUsersForm") Users users, Model model) {

			/// Put current username to display
			model.addAttribute("username", getCurrentUsername());
			
			List<Users> userslist = usersDAO.getUsersByUsername(users.getUsername());

			// add the users to the model
			model.addAttribute("users", userslist);
			
			return "edituserssearch";
		}
		
		@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "EditButton")
		public String edituserform(@ModelAttribute("edituser") Users users, Map<String, Object> model,
				@RequestParam String EditButton) {

			// Put current username to display
			model.put("username", getCurrentUsername());

			Users user = usersDAO.getUserByID(Integer.parseInt(EditButton));

			// add the user to the model
			model.put("user", user);

			//Add role select options
			List<String> roleList = new ArrayList<>();
			roleList.add("ROLE_ADMIN");
			roleList.add("ROLE_PROF");
			roleList.add("ROLE_STUDENT");
			roleList.add("ROLE_SEC");
			roleList.add("ROLE_MGA");
			model.put("roleList", roleList);

			//Add enabled select options
			List<Integer> enabledList = new ArrayList<>();
			enabledList.add(1);
			enabledList.add(0);
			model.put("enabledList", enabledList);

			return "edituser";
		}
		
		//Save the edits
		@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "SaveButton")
		public String saveEdits(@ModelAttribute("edituser") Users users, Map<String, Object> model,
				@RequestParam String SaveButton) {

			// Put current username to display
			model.put("username", getCurrentUsername());

			Users mergedUser = usersDAO.updateUser(users, Integer.parseInt(SaveButton));

			//Display result
			model.put("msg", "Updated Successfully");
			model.put("user", mergedUser);

			return "userSuccess";

		}

		//Delete User
		@RequestMapping(value = "/admin/edituser", method = RequestMethod.POST, params = "DeleteButton")
		public String deleteUser(@ModelAttribute("edituser") Users users, Model model, Map<String, Object> model2,
				@RequestParam String DeleteButton) {

			// Put current username to display
			model.addAttribute("username", getCurrentUsername());

			Users user = usersDAO.deleteUser(Integer.parseInt(DeleteButton));

			//Print results
			model2.put("user", user);

			return "deletedpage";
		}
		
	//Get current user's username
	private String getCurrentUsername() {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		return username;
	}
	
}
