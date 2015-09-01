package family.dao;

import java.util.List;

import family.model.Person;
 
public interface PersonDAO {
 
    public void save(Person p);
     
    public List<Person> list();
     
}