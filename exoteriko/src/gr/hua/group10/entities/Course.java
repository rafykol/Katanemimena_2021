package gr.hua.group10.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "courses")
public class Course {

	 @Id
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	   @Column(name="id")
	   private int id;

	   @Column(name="name")
	   private String name;
	   
	   @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
	                                           CascadeType.DETACH, CascadeType.REFRESH})
	   @JoinColumn(name="proffessor_id")
	   private Users proffessor;
	   
	   // define constructors
	   public Course() {
	           
	   }

	   public Course(String name) {
	           this.name = name;
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

	public Users getProffessor() {
		return proffessor;
	}

	public void setProffessor(Users proffessor) {
		this.proffessor = proffessor;
	}
	
	 public Users getProf() {
         return proffessor;
 }

 public void setProf(Users proffessor) {
         this.proffessor = proffessor;
 }

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", proffessor=" + proffessor + "]";
	}
	   
	
}
