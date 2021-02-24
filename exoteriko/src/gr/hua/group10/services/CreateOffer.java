package gr.hua.group10.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;
import gr.hua.group10.forms.OfferForm;

@Controller
@RequestMapping(value = "/admin/createoffer")
public class CreateOffer{
	
	@RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model, Model model2) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String username = loggedInUser.getName();
    	
		//Put to display
		model2.addAttribute("username", username);
		
		OfferForm myform = new OfferForm();
        model.put("createoffer", myform);
        
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
        
        Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Users where authority like :keyword or authority like :seckeyword";
		String keyword = "ROLE_STUDENT";
		String seckeyword = "ROLE_PROF";
		 
		@SuppressWarnings({"unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("seckeyword", "%" + seckeyword + "%");
		 
		List<Users> userslist = query.list();
       
        List<Users> studlist = new ArrayList<>();

        List<Users> proflist = new ArrayList<>();
        
        
        for(int i=0;i<userslist.size();i++) {
        	if(userslist.get(i).getAuthority().equals("ROLE_STUDENT")){

            	studlist.add(userslist.get(i));
        	}else{

            	proflist.add(userslist.get(i)); 		
        	}     	
        }

        
        
        factory.close();

        model.put("proflist", proflist); 
        model.put("studlist", studlist);        
        
        return "createoffer";
    }
	

    
	 @RequestMapping(method = RequestMethod.POST)
	    public String processRegistration(@ModelAttribute("createoffer")  @Valid OfferForm offerForm,
	            Model model, Model model2) {
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
	    	
			//Put to display
			model2.addAttribute("username", username);
	    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class)
	                .buildSessionFactory();
				
	    	
	    		Offer offer = offerForm.getNewoffer();
	    	
				// create session
				Session session = factory.getCurrentSession();
				session.beginTransaction();

				offer.setStatus("Pen_Submitted");
				
				Users prof = session.get(Users.class, offerForm.getProfID());
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