package family.service;

import java.util.List;

import family.model.ActiveTasks;
 
public interface ActiveTasksService {
 
    public List<ActiveTasks> listActiveTasks(int personId);
     
}