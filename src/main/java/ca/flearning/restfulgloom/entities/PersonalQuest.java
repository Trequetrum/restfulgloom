package ca.flearning.restfulgloom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PERSONAL_QUESTS")
public class PersonalQuest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long personalQuestId = -1;
	
	
}