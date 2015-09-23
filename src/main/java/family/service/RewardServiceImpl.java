package family.service;

import java.util.List;

import family.dao.RewardDAO;
import family.model.Reward;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RewardServiceImpl implements RewardService {

	private RewardDAO rewardDAO;
	
	public RewardDAO getRewardDAO() {
		return rewardDAO;
	}

	public void setRewardDAO(RewardDAO rewardDAO) {
		this.rewardDAO = rewardDAO;
	}

    @Override
    @Transactional
    public void addReward(Reward reward) {
        this.rewardDAO.addReward(reward);
    }
 
    @Override
    @Transactional
    public void updateReward(Reward reward) {
        this.rewardDAO.updateReward(reward);
    }
 
    @Override
    @Transactional
    public List<Reward> listRewards() {
        return this.rewardDAO.listRewards();
    }
 
    @Override
    @Transactional
    public Reward getRewardById(int id) {
        return this.rewardDAO.getRewardById(id);
    }
 
    @Override
    @Transactional
    public void removeReward(int id) {
        this.rewardDAO.removeReward(id);
    }

}
