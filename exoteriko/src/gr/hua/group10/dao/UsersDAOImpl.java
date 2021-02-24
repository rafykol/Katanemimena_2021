package gr.hua.group10.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import gr.hua.group10.entities.Course;
import gr.hua.group10.entities.Offer;
import gr.hua.group10.entities.Users;

@Repository
public class UsersDAOImpl implements UsersDAO {
	// inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Users> getUsers() {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Users> query = currentSession.createQuery("from Users", Users.class);

		// execute the query and get the results list
		List<Users> users = query.getResultList();

		// return the results
		return users;
	}

	@Override
	@Transactional
	public Users getUser(String username) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		String hql = "from Users where username like :keyword";
		String keyword = username;

		@SuppressWarnings({ "unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");

		List<Users> user = query.list();
		factory.close();
		return user.get(0);

	}

	@Override
	@Transactional
	public int createUser(Users user) {

		//Error Flag
		int flag =1;
		
		// New factory Session
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {
			session.beginTransaction();

			// save the new user
			session.save(user);

			// commit transaction
			session.getTransaction().commit();
			
			

		}catch(Exception e){
			System.out.println("Duplicate username or email");
			flag=0;
			
		}finally {
			factory.close();
		}
			

		//If transaction was done successfully, return 1
			return flag;			
	}


	@Override
	@Transactional
	public List<Users> getUsersByUsername(String username){
		

		//Create factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		List<Users> userslist = new ArrayList<Users>();
		
		try {
		session.beginTransaction();

		//Query to find usernames that contain the search keyword 
		String hql = "from Users where username like :keyword";
		String keyword = username;

		
		@SuppressWarnings({ "unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");

		//Execute query
		userslist = query.list();
		}catch(Exception e){
			
		}finally {
			factory.close();
		}
		
		return userslist;
	}
	
	@Override
	@Transactional
	public Users getUserByID(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Users user = currentSession.get(Users.class, id);
		
		return user;
	}
	

	@Override
	@Transactional
	public Users updateUser(Users users, int id) {
		//Create factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		Users mergedUser = new Users();
		
		try {
		session.beginTransaction();
		//Get the selected user before edits 
		Users user = session.get(Users.class, id);
		
		//Add user ID and password from original entity
		users.setId(id);
		users.setPassword(user.getPassword());		
		// save the new user
		mergedUser = (Users) session.merge(users);

		// commit transaction
		session.getTransaction().commit();

		}catch(Exception e){
			
		}finally {
			factory.close();
		}
		
		return mergedUser;
	}

	@Override
	@Transactional
	public Users deleteUser(int id){
		//Create factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.addAnnotatedClass(Offer.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		
		Users user = new Users();
		
		try {
		session.beginTransaction();

		//Get user to be deleted by id
		user = (Users) session.load(Users.class, id);

		//Delete user
		session.delete(user);

		//Commit 
		session.flush();
		session.getTransaction().commit();

		}catch(Exception e){
		}finally {
			factory.close();
		}
		
		return user;
	}
	
	@Override
	@Transactional
	public List<Users> getUsersByRole(String role){
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).addAnnotatedClass(Course.class).addAnnotatedClass(Offer.class).buildSessionFactory();
        
        Session session = factory.getCurrentSession();
        
        List<Users> userslist = new ArrayList<Users>();
        
        try {
        session.beginTransaction();
		
		String hql = "from Users where authority like :keyword";
		String keyword = role;
		 
		@SuppressWarnings({"unchecked" })
		Query<Users> query = session.createQuery(hql);
		query.setParameter("keyword", "%" + keyword + "%");
		 
		userslist = query.list();
        
        }catch(Exception e){
		}finally {
			factory.close();
		}
		
		return userslist;
	}
	
}
