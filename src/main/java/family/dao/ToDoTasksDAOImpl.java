package family.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
 
import family.model.ToDoTasks;
import family.model.ActiveTasks;
 
public class ToDoTasksDAOImpl implements ToDoTasksDAO {
 
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    @Override
    public void save(ToDoTasks t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<ActiveTasks> list(int personId) {
        Session session = this.sessionFactory.openSession();
        List<ActiveTasks> toDoTasksList = session.createQuery("from ActiveTasks where personId = " + personId).list();
        session.close();
        return toDoTasksList;
    }
 
}