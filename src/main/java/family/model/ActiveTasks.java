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
	 
	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private int id;
	    
	    private String summary;
	    private int points;
	    private boolean done;
	    private String image;
	    
	    
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
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

	    public boolean getDone() {
			return done;
		}
		public void setDone(boolean done) {
			this.done = done;
		}

	    
	    
}
