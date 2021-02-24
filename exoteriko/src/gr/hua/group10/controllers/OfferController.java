package gr.hua.group10.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.group10.dao.OfferDAO;
import gr.hua.group10.entities.Offer;

@Controller

public class OfferController {

	// inject the customer dao
	@Autowired
	private OfferDAO offerDAO;
	
	@RequestMapping(value = "/admin/printoffers", method = RequestMethod.GET)
	public String listOffers(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
    	
		//Put to display
		model.addAttribute("username", username);

		// get customers from dao
		List<Offer> offers = offerDAO.getOffers();

		// add the customers to the model
		model.addAttribute("offers", offers);

		return "printoffers";
	}
	

	
}

