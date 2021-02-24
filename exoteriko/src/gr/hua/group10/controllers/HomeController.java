package gr.hua.group10.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// Pathing for /
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		// Get current log in user's info
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// check if there is a logged in user
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			// Get current user's username
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();

			// Put to display
			model.addAttribute("username", username);
			String role = loggedInUser.getAuthorities().toString();
			
			//Depending on the user role, return different home page
			if (role.contains("ROLE_ADMIN")) {
				return "admin";

			} else if (role.contains("ROLE_PROF")) {
				return "profWP";

			} else if (role.contains("ROLE_STUDENT")) {
				return "studentWP";

			} else if (role.contains("ROLE_SEC")) {
				return "secWP";

			} else if (role.contains("ROLE_MGA")) {
				return "mgaWP";

			}
			return "home";
		}

		// if it is not authenticated, then go to the log in page
		return "login";
	}

	//Extra mapping for log in page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "login";
	}

	//Base mapping for admin role 
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {

		return "admin";
	}

	//Mapping for log out 
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/logout/";
	}

	//Base mapping for proffessor role
	@RequestMapping(value = "/proffessor", method = RequestMethod.GET)
	public String proffessor() {

		return "profWP";
	}

	//Base mapping for home 
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {

		// Get current user's username
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
		
		//Depending on user role, return different page
		String role = loggedInUser.getAuthorities().toString();
		// Put to display
		model.addAttribute("username", username);
		if (role.contains("ROLE_ADMIN")) {
			return "admin";

		} else if (role.contains("ROLE_PROF")) {
			return "profWP";

		} else if (role.contains("ROLE_STUDENT")) {
			return "studentWP";

		} else if (role.contains("ROLE_SEC")) {
			return "secWP";

		} else if (role.contains("ROLE_MGA")) {
			return "mgaWP";

		}
		return "home";
	}

	//Mapping for forgotpassword page
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpassword() {

		return "forgotpassword";
	}

	//Mapping for contact page (different if the person is logged in or not)
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "contact2";
		}

		return "contact";
	}

	//Mapping for manual page (different if the person is logged in or not)
	@RequestMapping(value = "/manual", method = RequestMethod.GET)
	public String manual() {

		return "manual";
	}

	//Mapping for about page (different if the person is logged in or not)
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "about2";
		}

		return "about";
	}
}
