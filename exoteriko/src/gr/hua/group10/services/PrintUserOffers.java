package gr.hua.group10.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.hua.group10.dao.OfferDAO;
import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;

@Controller
public class PrintUserOffers {

	// inject the customer dao
	@Autowired
	private OfferDAO offerDAO;

	@RequestMapping(value = "/proffessor/printoffers", method = RequestMethod.GET)
	public String listOffers(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		// Put to display
		model.addAttribute("username", username);

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		String hql = "from Users where username like :keyword";
		String keyword = username;
		 
		@SuppressWarnings({"unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
    	
    	List<Users> user = query.list();
    	Users prof = user.get(0);

    	hql = "from Offer where proffessorID like :keyword";
    	Users keyword2 = prof;
    	 
		@SuppressWarnings({"unchecked" })
		Query<Offer> query2 = session.createQuery(hql);
		query2.setParameter("keyword", keyword2);
    	
		List<Offer> offers = query2.list();
    	
		// add the customers to the model
		model.addAttribute("offers", offers);

		return "printoffers";
	}

	@RequestMapping(value = "/proffessor/printsubmittedoffers", method = RequestMethod.GET)
	public String listsubmittedOffers(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		// Put to display
		model.addAttribute("username", username);

		String keyword = "Pen";
		
		List<Offer> offers = searchDBfunction(username, keyword);

		// add the customers to the model
		model.addAttribute("offers", offers);

		return "printoffers";
	}
	
	@RequestMapping(value = "/proffessor/printaccepteddoffers", method = RequestMethod.GET)
	public String listacceptedOffers(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		// Put to display
		model.addAttribute("username", username);

		String keyword = "Accepted";
		
		List<Offer> offers = searchDBfunction(username, keyword);

		// add the customers to the model
		model.addAttribute("offers", offers);

		return "printoffers";
	}
	
	@RequestMapping(value = "/proffessor/printrejectedoffers", method = RequestMethod.GET)
	public String listrejectedOffers(Model model) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();

		// Put to display
		model.addAttribute("username", username);

		String keyword = "Rejected";
		
		List<Offer> offers = searchDBfunction(username, keyword);

		// add the customers to the model
		model.addAttribute("offers", offers);

		return "printoffers";
	}
	
	private List<Offer> searchDBfunction(String keyword, String seckeyword){
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		String hql = "from Users where username like :keyword";
		
		 
		@SuppressWarnings({"unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", keyword);
    	
    	List<Users> user = query.list();
    	Users prof = user.get(0);

    	hql = "from Offer where proffessorID like :keywordtwo and status like :seckeyword";
    	Users keywordtwo = prof;
    	 
		@SuppressWarnings({"unchecked" })
		Query<Offer> query2 = session.createQuery(hql);
		query2.setParameter("keywordtwo", keywordtwo);
		query2.setParameter("seckeyword", "%" + seckeyword + "%");
    	
		List<Offer> offers = query2.list();
		return offers;
	} 
}