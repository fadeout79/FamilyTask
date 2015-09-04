package family.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import family.dao.*;
import family.model.Person;
import family.model.Tasks;
import family.model.ToDoTasks;

@Controller
public class FamilyTasksController {
	
    @RequestMapping("/list")
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
