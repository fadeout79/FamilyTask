package family.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import family.model.ActiveTasks;
import family.service.ActiveTasksService;
import family.service.ToDoTasksService;

@Controller
@RequestMapping("/todoTasks")
public class ToDoTasksRestController {

	private final Logger logger = LoggerFactory.getLogger(ToDoTasksRestController.class);
    private ActiveTasksService activeTasksService;
    private ToDoTasksService todoTasksService;
    
    @Autowired(required=true)
    @Qualifier(value="activeTasksService")
    public void setActiveTasksService(ActiveTasksService ats){
        this.activeTasksService = ats;
    } 

    @Autowired(required=true)
    @Qualifier(value="todoTasksService")
    public void setToDoTasksService(ToDoTasksService ats){
        this.todoTasksService = ats;
    } 

    @RequestMapping(value = "/taskDone", method = RequestMethod.GET)
    public @ResponseBody List<ActiveTasks> doneTasks(@RequestParam("personId")int personId,
    		@RequestParam("tasksId")int tasksId) {
    	logger.info("Setting done for PersonId = " + personId + " on taskId = " + tasksId);
    	todoTasksService.setDone(tasksId, personId);
    	return activeTasksService.listActiveTasks(personId);
    }
    
}
