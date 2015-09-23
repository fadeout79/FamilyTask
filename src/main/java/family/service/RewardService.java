package family.service;

import java.util.List;

import family.model.Reward;
 
public interface RewardService {
 
    public void addReward(Reward reward);
    public void updateReward(Reward reward);
    public List<Reward> listRewards();
    public Reward getRewardById(int id);
    public void removeReward(int id);
     
}