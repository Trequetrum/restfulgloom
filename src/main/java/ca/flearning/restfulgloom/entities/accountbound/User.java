package ca.flearning.restfulgloom.entities.accountbound;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ElementCollection
	@CollectionTable(
		name="authorities",
		joinColumns=@JoinColumn(name="username")
	)
	@Column(name="authority")
	private List<String> roles = new ArrayList<String>();
	
	
	
}
