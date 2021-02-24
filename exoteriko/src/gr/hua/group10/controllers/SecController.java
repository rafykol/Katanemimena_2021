package gr.hua.group10.controllers;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;

@Controller
public class SecController {

	//Print all pending offers
	@RequestMapping(value="/sec/printoffers", method = RequestMethod.GET)
	public String printPendingOffers(Model model) {
		
		//Get current user
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
    	
		//Put to display
		model.addAttribute("username", username);

		//Create Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		//Create search query
		String hql = "from Offer where status like :keyword";
		String keyword = "Pen_Acc";
		 
		@SuppressWarnings({"unchecked" })
		Query<Offer> query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
    	
		List<Offer> offers  = query.list();
				
		
		// get customers from dao
		

		// add the customers to the model
		model.addAttribute("offers", offers);

		return "mgaprintoffers";
	}
	

	//MGA accept offer
		@RequestMapping(value="/sec/ajoffer", method = RequestMethod.POST, params = "MGAAcceptButton")
		public String mgaacceptoffer(@ModelAttribute("ajoffer") Offer offers, Model model, Map<String, Object> model2,
				@RequestParam String MGAAcceptButton) {

			//Get current user
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();

			// Put to display
			model.addAttribute("username", username);

			//Create factory 
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
					.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

			// create session
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			//Get the selected offer
			Offer offer = session.get(Offer.class, Integer.parseInt(MGAAcceptButton));
			
			//Add offer ID and new status 
			offer.setId(offer.getId());
			offer.setStatus("Accepted");
			
			// save the new status
			Offer mergedOffer = (Offer) session.merge(offer);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
			factory.close();

			//Display result
			model2.put("msg", "Offer Accepted");

			return "home";

		}

	
		//MGA accept offer
				@RequestMapping(value="/sec/ajoffer", method = RequestMethod.POST, params = "MGARejectButton")
				public String mgarejectoffer(@ModelAttribute("ajoffer") Offer offers, Model model, Map<String, Object> model2,
						@RequestParam String MGARejectButton) {

					//Get current user
					Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
					String username = loggedInUser.getName();

					// Put to display
					model.addAttribute("username", username);

					//Create factory 
					SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
							.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

					// create session
					Session session = factory.getCurrentSession();
					session.beginTransaction();

					//Get the selected offer
					Offer offer = session.get(Offer.class, Integer.parseInt(MGARejectButton));
					
					//Add offer ID and new status 
					offer.setId(offer.getId());
					offer.setStatus("Rejected");
					
					// save the new status
					Offer mergedOffer = (Offer) session.merge(offer);

					// commit transaction
					session.getTransaction().commit();

					System.out.println("Done!");
					factory.close();

					//Display result
					model2.put("msg", "Offer Rejected");

					return "home";

				}
	
	
	
	
	
	
	
	
	
	
	
}