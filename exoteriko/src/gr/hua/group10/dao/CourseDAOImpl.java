package gr.hua.group10.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;
import gr.hua.group10.forms.CourseForm;

@Repository
public class CourseDAOImpl implements CourseDAO {
    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public List<Course> getCourses() {
            // get current hibernate session
            Session currentSession = sessionFactory.getCurrentSession();
            
            // create a query
            Query<Course> query = currentSession.createQuery("from Course", Course.class);
            
            
            // execute the query and get the results list
            List<Course> courses = query.getResultList();
                            
            //return the results
            return courses;
    }
    

    @Override
    @Transactional
	public Course createCourse(CourseForm courseForm) {
    	
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class)
                .buildSessionFactory();
			
    	
			// create session
			Session session = factory.getCurrentSession();
			Course course = new Course();
			try {
			session.beginTransaction();
			
			Users prof = session.get(Users.class, courseForm.getUserid());
			
			course = new Course(courseForm.getNewcourse());
			prof.add(course);
			
            // save the student object
            session.save(course);

            // commit transaction
            session.getTransaction().commit();
			}catch(Exception e){
			}finally {
				factory.close();
			}
			
            return course;
    }
    

    @Override
    @Transactional
	public  List<Course> getCoursesByName(String name){
    	
    	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
		// create session
					Session session = factory.getCurrentSession();
					List<Course> courselist = new ArrayList<Course>();
					try {
					session.beginTransaction();
					
					String hql = "from Course where name like :keyword";
					String keyword = name;
					 
					@SuppressWarnings({"unchecked" })
					Query<Course> query = session.createQuery(hql);
					query.setParameter("keyword", "%" + keyword + "%");
					 
					 courselist = query.list();
    }catch(Exception e){
	}finally {
		factory.close();
	}
					return courselist;
    }
    

    @Override
    @Transactional
	public Course getCourseByID(int id) {
Session currentSession = sessionFactory.getCurrentSession();
		
		Course course = currentSession.get(Course.class, id);
		
		return course;
					
    	
    }
    
    @Override
	@Transactional
	public Course updateCourse(CourseForm courseForm, int id) {
		//Create factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		Course mergedCourse = new Course();
		
		Course course = new Course();
		
		try {
		session.beginTransaction();
		
		Users prof = session.get(Users.class, courseForm.getUserid());
		
		course = new Course(courseForm.getNewcourse());
		prof.add(course);
		
		//Add ID from original entity
		course.setId(id);	
		// save the new user
		mergedCourse = (Course) session.merge(course);

		// commit transaction
		session.getTransaction().commit();

		}catch(Exception e){
			
		}finally {
			factory.close();
		}
		
		return mergedCourse;
	}
    

}
