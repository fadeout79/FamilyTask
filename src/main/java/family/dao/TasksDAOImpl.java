package family.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
 
import family.model.Tasks;

public class TasksDAOImpl implements TasksDAO {
 
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    @Override
    public void save(Tasks t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Tasks> list(int personId) {
        Session session = this.sessionFactory.openSession();
        List<Tasks> tasksList = session.createQuery("from Person WHERE personId =" + personId).list();
        session.close();
        return tasksList;
    }
	 
}
