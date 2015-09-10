package family.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="activeTasks")
public class ActiveTasks {
	 

	    private String summary;
	    private int points;
	    private boolean isDone;
	    
	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    
		public boolean isDone() {
			return isDone;
		}
		public void setDone(boolean isDone) {
			this.isDone = isDone;
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
		public int getPoints() {
			return points;
		}
		public void setPoints(int points) {
			this.points = points;
		}
	    
	    
	    
}
