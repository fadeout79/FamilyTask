package family.service;

import java.util.List;

import family.dao.ToDoTasksDAO;
import family.model.ActiveTasks;
import family.model.Person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ToDoTasksServiceImpl implements ToDoTasksService {

	private ToDoTasksDAO toDoTasksDAO;
	
	public ToDoTasksDAO getToDoTasksDAO() {
		return toDoTasksDAO;
	}

	public void setToDoTasksDAO(ToDoTasksDAO toDoTasksDAO) {
		this.toDoTasksDAO = toDoTasksDAO;
    }

    @Override
    @Transactional
    public void setDone(int tasksId, int personId) {
        this.toDoTasksDAO.setDone(tasksId, personId);
    }
 
}
