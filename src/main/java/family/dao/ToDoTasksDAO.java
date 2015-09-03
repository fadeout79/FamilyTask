package family.dao;

import java.util.List;

import family.model.ToDoTasks;
 
public interface ToDoTasksDAO {
 
    public void save(ToDoTasks t);
     
    public List<ToDoTasks> list();
     
}