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
@Table (name = "offers")
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "lab_hours")
	private int labHours;
	
	@Column(name = "super_hours")
	private int superHours;

	@Column(name = "tests")
	private int tests;

	@Column(name = "status")
	private String status;
	
	@Column(name = "comments")
	private String comments;

	 @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
             CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name="proffessor_id")
private Users proffessorID;
	 
	 @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
             CascadeType.DETACH, CascadeType.REFRESH})
@JoinColumn(name="student_id")
private Users studentID;
	
	public Offer() {
	}

	public Offer(int labHours, int superHours, int tests, String status, String comments, Users proffessorID,
			Users studentID) {
		super();
		this.labHours = labHours;
		this.superHours = superHours;
		this.tests = tests;
		this.status = status;
		this.comments = comments;
		this.proffessorID = proffessorID;
		this.studentID = studentID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLabHours() {
		return labHours;
	}

	public void setLabHours(int labHours) {
		this.labHours = labHours;
	}

	public int getSuperHours() {
		return superHours;
	}

	public void setSuperHours(int superHours) {
		this.superHours = superHours;
	}

	public int getTests() {
		return tests;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	
	public Users getProffessorID() {
		return proffessorID;
	}

	public void setProffessorID(Users proffessorID) {
		this.proffessorID = proffessorID;
	}

	public Users getStudentID() {
		return studentID;
	}

	public void setStudentID(Users studentID) {
		this.studentID = studentID;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", labHours=" + labHours + ", superHours=" + superHours + ", tests=" + tests
				+ ", status=" + status + ", comments=" + comments + "]";
	}

	
	
}
