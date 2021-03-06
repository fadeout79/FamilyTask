package family.dao;

import java.util.List;

import family.model.ToDoTasks;
import family.model.ActiveTasks;
 
public interface ToDoTasksDAO {
 
    public void save(ToDoTasks t);
     
    public List<ActiveTasks> list(int personId);
    
    public void setDone(int tasksId, int personId);
     
    public ToDoTasks getToDoTasksById(int id);
}