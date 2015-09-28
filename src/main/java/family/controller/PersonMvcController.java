package family.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import family.dao.*;
import family.model.Person;
import family.model.Tasks;
import family.model.ToDoTasks;
import family.service.ActiveTasksService;
import family.service.PersonService;
import family.utilities.ImageProcessing;

@Controller
@RequestMapping("/person")
public class PersonMvcController {
	
	private final Logger logger = LoggerFactory.getLogger(PersonMvcController.class);
    private PersonService personService;
    private ActiveTasksService activeTasksService;

    
	private String imagePath;
	
	public PersonMvcController() {
		imagePath = System.getenv().get("family_image_path");
		//imagePath = "/home/fadeout79/development/workspace/FamilyTask/images";
	}

    @Autowired(required=true)
    @Qualifier(value="personService")
    public void setPersonService(PersonService ps){
        this.personService = ps;
    }

    @Autowired(required=true)
    @Qualifier(value="activeTasksService")
    public void setActiveTasksService(ActiveTasksService ats){
        this.activeTasksService = ats;
    }    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPersons(Model model) {
        List<Person> list = this.personService.listPersons();
        for (Person p : list) {
        	p.setTodoTasks(activeTasksService.listActiveTasks(p.getId()));
        }
        model.addAttribute("listPersons", list);
        return "personList";
    }


    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String addPerson(Model model){
    	model.addAttribute("newPerson", new Person());
        return "personAdd";
    }    
    
    //For add and update person both
    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addPerson(Person p){
    	logger.info("ici");
    	ImageProcessing ip = new ImageProcessing();
    	ip.createImage(p.getImages(), p.getImages().getOriginalFilename(), imagePath);
    	p.setImagePath(ip.getFileName());
    	this.personService.addPerson(p);
    	return "redirect:/person/list";
         
    }

    @RequestMapping("/{id}")
    public String removePerson(@PathVariable("id") int id, Model model){
        Person p =  this.personService.getPersonById(id);
        p.setTodoTasks(activeTasksService.listActiveTasks(p.getId()));
        model.addAttribute("person", p);
        return "personShow";
    }
    
    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
         
        this.personService.removePerson(id);
        return "redirect:/person/list";
    }
  
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "redirect:/person/list";
    }
    
    
    /*@RequestMapping(method = RequestMethod.GET, value="/addtasks")
    public String addTasksStep1(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
         
        List<Person> list = personDAO.list();
        model.addAttribute("liste", list);
        
        //close resources
        context.close();      	
    
    	return "addtasks";
    }*/

    @RequestMapping(method = RequestMethod.POST, value="/addtasks")
    public String addTasksStep2(@RequestParam("summary")String summary, 
    		@RequestParam("description")String description,
    		@RequestParam("personId")int personId,
    		@RequestParam("points")int points,
    		@RequestParam("startDate")Date startDate,
    		@RequestParam("day")int recurrence,
    		Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

    	Tasks task = new Tasks();
    	task.setSummary(summary);
    	task.setDescription(description);
    	task.setPersonId(personId);
    	task.setPoints(points);
    	task.setRecurrence(recurrence);
    	task.setStartDate(startDate);
    	TasksDAO tasksDAO = context.getBean(TasksDAO.class);
    	tasksDAO.save(task);
    	ToDoTasks todoTask = new ToDoTasks();
    	todoTask.setNextDate(task.getStartDate());
    	todoTask.setTasksId(task.getId());
    	ToDoTasksDAO toDoTasksDAO = context.getBean(ToDoTasksDAO.class);
    	toDoTasksDAO.save(todoTask);
    	
    	context.close();
    	return "list";
    }
}
