package ca.flearning.restfulgloom.entities.accountbound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTIES")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long partyId = -1;
	
}
