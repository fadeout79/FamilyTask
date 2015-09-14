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
 * 
 * @author Ian-Rémi Lafleur
 *
 */
@Entity
@Table(name = "todoTasks")
public class ToDoTasks {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private boolean done;
	private boolean checked;
	private Date nextDate;
	private int tasksId;
	private int doneByPersonId;

	public int getDoneByPersonId() {
		return doneByPersonId;
	}

	public void setDoneByPersonId(int personId) {
		this.doneByPersonId = personId;
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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

	public int getTasksId() {
		return tasksId;
	}

	public void setTasksId(int tasksId) {
		this.tasksId = tasksId;
	}

}
