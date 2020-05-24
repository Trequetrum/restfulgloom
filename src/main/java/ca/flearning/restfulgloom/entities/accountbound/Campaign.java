package ca.flearning.restfulgloom.entities.accountbound;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAMPAIGNS")
public class Campaign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long campaignId = -1;
	
}
