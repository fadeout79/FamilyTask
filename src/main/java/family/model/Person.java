package family.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
 
/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity
@Table(name="person")
public class Person {
 
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
     
    private String name;
     
    private boolean isParent;
 
    private Date dateOfBirth;
    
    public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Transient
    private List<ActiveTasks> todoTasks;
    
    public List<ActiveTasks> getTodoTasks() {
		return todoTasks;
	}

	public void setTodoTasks(List<ActiveTasks> todoTasks) {
		this.todoTasks = todoTasks;
	}

	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public boolean isParent() {
        return isParent;
    }
 
    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }
     
    @Override
    public String toString(){
        return "id="+id+", name="+name+", isParent="+isParent;
    }
}