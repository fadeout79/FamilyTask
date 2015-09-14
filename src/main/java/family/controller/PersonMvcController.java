package family.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;


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

@Controller
@RequestMapping("/")
public class FamilyTasksController {
	
	private final Logger logger = LoggerFactory.getLogger(FamilyTasksController.class);
    private PersonService personService;
    private ActiveTasksService activeTasksService;

    
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
    
   /* @RequestMapping("/list")
    public String person(Model model) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
        ToDoTasksDAO todoTasks = context.getBean(ToDoTasksDAO.class);
        
        List<Person> list = personDAO.list();
        for (Person p : list) {
        	p.setTodoTasks(todoTasks.list(p.getId()));
        }
        model.addAttribute("liste", list);
        
        //close resources
        context.close();  
        
        return "list";
    }*/
	
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new Person());
        List<Person> list = this.personService.listPersons();
        for (Person p : list) {
        	p.setTodoTasks(activeTasksService.listActiveTasks(p.getId()));
        }
        model.addAttribute("listPersons", list);
        return "person";
    }

    @RequestMapping(value = "/doneTasks", method = RequestMethod.GET)
    public List<ToDoTasks> doneTasks(@RequestParam("personId")int personId,
    		@RequestParam("tasksId")int tasksId) {
    	

    	return null;
    }
    
    
    
    //For add and update person both
    @RequestMapping(value= "/persons/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p){
         
        if(p.getId() == 0){
            //new person, add it
            this.personService.addPerson(p);
        }else{
            //existing person, call update
            this.personService.updatePerson(p);
        }
         
        return "redirect:/persons";
         
    }
    
    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
         
        this.personService.removePerson(id);
        return "redirect:/persons";
    }
  
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }
    
    
   /* @RequestMapping("/list")
    public @ResponseBody List<Person> person() {
    	logger.debug("List is called");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        
        PersonDAO personDAO = context.getBean(PersonDAO.class);
        ToDoTasksDAO todoTasks = context.getBean(ToDoTasksDAO.class);
        
        List<Person> list = personDAO.list();
        for (Person p : list) {
        	p.setTodoTasks(todoTasks.list(p.getId()));
        }
       
        //close resources
        context.close();  
        
        return list;
    }*/
    
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
    		//@RequestParam("fixDay")int day,
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
    	//task.setFixDay(day);
    	task.setStartDate(startDate);
    	//if (day != 0) {
    		//task.setFixDay(day);
    		//task.setRecurrent(true);
    	//}
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
