package family.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import family.model.Reward;
import family.service.RewardService;

@Controller
@RequestMapping("/reward")
public class RewardMvcController {

	private final Logger logger = LoggerFactory.getLogger(RewardMvcController.class);
    private RewardService rewardService;
	
    @Autowired(required=true)
    @Qualifier(value="rewardService")
    public void setRewardService(RewardService rewardService){
        this.rewardService = rewardService;
    }
    
    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String addReward(ModelMap model){
    	model.addAttribute("newReward", new Reward());
        return "rewardAdd";
    }    

    //For add and update person both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addReward(Reward reward){
         
        if(reward.getId() == 0){
            //new person, add it
            this.rewardService.addReward(reward);
            logger.debug("Adding reward :" + reward.toString());
        }else{
            //existing person, call update
            this.rewardService.updateReward(reward);
        }
         
        return "redirect:/reward/list";
         
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listReward(Model model) {
        List<Reward> list = this.rewardService.listRewards();
        model.addAttribute("listRewards", list);
        return "rewardList";
    }

}

