package family.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
 
/**
 * TodoTasks bean
 * @author Ian-Rémi Lafleuyr
 *
 */
@Entity
@Table(name="todoTasks")
@SecondaryTable(name="tasks",
                pkJoinColumns=@PrimaryKeyJoinColumn(name="taskId"))
public class ToDoTasks {
 

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	@Column(table="tasks")
	private String summary;

	private boolean isDone;
	private boolean isChecked;
	private Date nextDate;
	private int taskId;
	
    public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getSummary() {
        return summary;
    }
 
    public void setSummary(String summary) {
        this.summary = summary;
    }

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
 
    
}

/*
id INT NOT NULL AUTO_INCREMENT,
tasksId INT NOT NULL,
nextDate DATE DEFAULT NULL,
isDone BOOLEAN DEFAULT FALSE,
isChecked BOOLEAN DEFAULT FALSE,
*/