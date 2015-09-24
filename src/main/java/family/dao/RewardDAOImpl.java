package family.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import family.model.Reward;

@Repository
public class RewardDAOImpl implements RewardDAO {
 
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    @Override
    public void addReward(Reward t) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(t);
        tx.commit();
        session.close();
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Reward> listRewards() {
        Session session = this.sessionFactory.openSession();
        List<Reward> rewardsList = session.createQuery("from Reward").list();
        session.close();
        return rewardsList;
    }

	@Override
	public void updateReward(Reward reward) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reward getRewardById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeReward(int id) {
		// TODO Auto-generated method stub
		
	}
	 
}
