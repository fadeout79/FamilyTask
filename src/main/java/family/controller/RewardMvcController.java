package family.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import family.model.Person;
import family.model.Reward;
import family.service.RewardService;

@Controller
@RequestMapping("/reward")
public class RewardMvcController {

	private final Logger logger = LoggerFactory.getLogger(RewardMvcController.class);
    private RewardService rewardService;
	
    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String addReward(Model model){
    	model.addAttribute("reward", new Reward());
        return "rewardAdd";
    }    

    //For add and update person both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("reward") Reward reward){
         
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
}
