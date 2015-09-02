package family.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;



//import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import family.dao.*;
import family.model.Person;
import family.model.Tasks;

@Controller
public class FamilyTasksController {
	
    @RequestMapping("/list")
    public String person(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
         
        List<Person> list = personDAO.list();
        model.addAttribute("liste", list);
        
        //close resources
        context.close();  
        
        return "list";
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/addtasks")
    public String addTasksStep1(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
         
        List<Person> list = personDAO.list();
        model.addAttribute("liste", list);
        
        //close resources
        context.close();      	
    
    	return "addtasks";
    }

    @RequestMapping(method = RequestMethod.POST, value="/addtasks")
    public String addTastsStep2(@RequestParam("summary")String summary, 
    		@RequestParam("description")String description,
    		@RequestParam("personId")int personId,
    		@RequestParam("points")int points,
    		@RequestParam("day")int day,
    		Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    	Tasks task = new Tasks();
    	task.setSummary(summary);
    	task.setDescription(description);
    	task.setPersonId(personId);
    	task.setPoints(points);
    	if (day != 0) {
    		task.setDay(day);
    		task.setRecurrent(true);
    	}
    	TasksDAO tasksDAO = context.getBean(TasksDAO.class);
    	tasksDAO.save(task);
    	
    	return "list";
    }
}
