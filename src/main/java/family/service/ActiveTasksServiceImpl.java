package family.service;

import java.util.List;

import family.dao.ToDoTasksDAO;
import family.model.ActiveTasks;
import family.model.Person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ActiveTasksServiceImpl implements ActiveTasksService {

	private ToDoTasksDAO toDoTasksDAO;
	
	public ToDoTasksDAO getToDoTasksDAO() {
		return toDoTasksDAO;
	}

	public void setToDoTasksDAO(ToDoTasksDAO toDoTasksDAO) {
		this.toDoTasksDAO = toDoTasksDAO;
    }

    @Override
    @Transactional
    public List<ActiveTasks> listActiveTasks(int personId) {
        return this.toDoTasksDAO.list(personId);
    }
 
}
