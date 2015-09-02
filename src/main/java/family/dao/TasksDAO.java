package family.dao;

import java.util.List;

import family.model.Tasks;

public interface TasksDAO {

	
    public void save(Tasks t);
    
    public List<Tasks> list(int personId);
     	
	
}
