package gr.hua.group10.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table (name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username", unique = true)
    private String username;
	
	@Column(name = "password")
    private String password;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "authority")
    private String authority;
	
	@Column(name = "enabled")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;

	@OneToMany(mappedBy="proffessor",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                                   CascadeType.DETACH, CascadeType.REFRESH})
	private List<Course> courses;
	
	@OneToMany(mappedBy="proffessorID",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                                   CascadeType.DETACH, CascadeType.REFRESH})
	private List<Offer> proffessor;
	
	@OneToMany(mappedBy="studentID",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                                   CascadeType.DETACH, CascadeType.REFRESH})
	private List<Offer> student;
	
	
	
	public Users() {}
	
	public Users(String username, String password, String firstName, String lastName, String email, String authority,
			boolean enabled) {
		super();
		this.username = username;
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		this.password = encoder.encode(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.authority = authority;
		this.enabled =  enabled;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder(4);
		this.password = encoder.encode(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled =  enabled;
	}
		
	public List<Offer> getProffessor() {
		return proffessor;
	}

	public void setProffessor(List<Offer> proffessor) {
		this.proffessor = proffessor;
	}

	public List<Offer> getStudent() {
		return student;
	}

	public void setStudent(List<Offer> student) {
		this.student = student;
	}

	public List<Course> getCourses() {
        return courses;
}

public void setCourses(List<Course> courses) {
        this.courses = courses;
}

//add convenience methods for bi-directional relation
public void add(Course acourse) {
     if(courses == null) {
             courses = new ArrayList<>();
     }
     courses.add(acourse);
     acourse.setProf(this);
}

//add convenience methods for bi-directional relation
public void addP(Offer aoffer) {
	   if(proffessor == null) {
	  	 proffessor = new ArrayList<>();
	   }
	   proffessor.add(aoffer);
	   aoffer.setProffessorID(this);
	}

public void addS(Offer aoffer) {
		   if(proffessor == null) {
			  	 proffessor = new ArrayList<>();
			   }
			   student.add(aoffer);
			   aoffer.setStudentID(this);
			}


@Override
public String toString() {
	return "Users [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
			+ ", lastName=" + lastName + ", email=" + email + ", authority=" + authority + ", enabled=" + enabled
			 + "]";
}


	
	
	
}
	