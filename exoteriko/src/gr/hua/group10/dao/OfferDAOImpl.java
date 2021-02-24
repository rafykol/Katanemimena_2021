package gr.hua.group10.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.group10.entities.Offer;

@Repository
public class OfferDAOImpl implements OfferDAO {
    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public List<Offer> getOffers() {
            // get current hibernate session
            Session currentSession = sessionFactory.getCurrentSession();
            
            // create a query
            Query<Offer> query = currentSession.createQuery("from Offer", Offer.class);
            
            
            // execute the query and get the results list
            List<Offer> offers = query.getResultList();
                            
            //return the results
            return offers;
    }

}
