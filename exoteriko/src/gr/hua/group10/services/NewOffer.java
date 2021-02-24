package gr.hua.group10.services;

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

import gr.hua.group10.dao.UsersDAO;
import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;
import gr.hua.group10.forms.OfferForm;


@Controller
@RequestMapping(value = "/proffessor/newoffer")
public class NewOffer {
		
	@Autowired 
	UsersDAO usersDAO;
	
		@RequestMapping(method = RequestMethod.GET)
	    public String viewRegistration(Map<String, Object> model, Model model2) {
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
	    	
			String role = loggedInUser.getAuthorities().toString();
			if(role.contains("ROLE_ADMIN")) {
				return "redirect:/admin/createoffer";
			}
			
			//Put to display
			model2.addAttribute("username", username);
			OfferForm myform = new OfferForm();
			model.put("newoffer", myform);
	        
	        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
	        
	        Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			String hql = "from Users where authority like :keyword";
			String keyword = "ROLE_STUDENT";
			 
			@SuppressWarnings({"unchecked" })
			Query<Users> query = session.createQuery(hql);
			query.setParameter("keyword", "%" + keyword + "%");
			 
			List<Users> userslist = query.list();
	       
	        List<Users> studlist = new ArrayList<>();
	        
	        
	        for(int i=0;i<userslist.size();i++) {
	            	studlist.add(userslist.get(i));
	        }

	        
	        
	        factory.close();
 
	        model.put("studlist", studlist);        
	        
	        return "newoffer";
	    }
	    
		 @RequestMapping(method = RequestMethod.POST)
		    public String processRegistration(@ModelAttribute("newoffer")  @Valid OfferForm offerForm, 
		            Model model, Model model2) {
				Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
				String username = loggedInUser.getName();
		    	
				//Put to display
				model2.addAttribute("username", username);
		    	
		    	
		    		Offer offer = offerForm.getNewoffer();

					offer.setStatus("Pen_Submitted");
					
					SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class)
			                .buildSessionFactory();
						
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

					Users student = session.get(Users.class, offerForm.getStdID());

					offer.setProffessorID(prof);
					offer.setStudentID(student);
					
					prof.addP(offer);
					student.addS(offer);
					
		            // save the student object
		            session.save(offer);

		            // commit transaction
		            session.getTransaction().commit();

		            System.out.println("Done!");
		          

		            
		        	// add the customers to the model
		        	model.addAttribute("offer", offer );
		        	
		        factory.close();
		        return "OfferSuccess";
		    }
	
}
	
