package ca.flearning.restfulgloom.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="CHARACTERS")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long characterId;
	
	@Column(name="health", nullable=false)
	private int health = 0;
	@Column(name="exp", nullable=false)
	private int exp = 0;
	@Column(name="gold", nullable=false)
	private int gold = 0;
	@Column(name="check_marks", nullable=false)
	private int checkMarks = 0;
	@Column(name="name", nullable=false)
	private String name = "No Name";
	
	@ManyToOne(optional=false, cascade=CascadeType.PERSIST)
	@JoinColumn(name="class_id")
	private GClass charClass = new GClass();
	
	@OneToMany(mappedBy = "equipedTo", cascade = CascadeType.ALL)
	private List<Equip> equiped = new ArrayList<Equip>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTERS_ABILITY_CARDS", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "ability_card_id") })
	private List<AbilityCard> abilityCards = new ArrayList<AbilityCard>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTERS_PERKS", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "perk_id") })
	private List<Perk> perks = new ArrayList<Perk>();
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CHARACTERS_NOTES", 
             joinColumns = { @JoinColumn(name = "character_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "note_id") })
	private List<Note> notes = new ArrayList<Note>();
	
	public Character() {}

	public long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(long characterId) {
		this.characterId = characterId;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getCheckMarks() {
		return checkMarks;
	}

	public void setCheckMarks(int checkMarks) {
		this.checkMarks = checkMarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GClass getCharClass() {
		return charClass;
	}

	public void setCharClass(GClass charClass) {
		this.charClass = charClass;
	}

	public List<Equip> getEquiped() {
		return equiped;
	}

	public void setEquiped(List<Equip> equiped) {
		this.equiped = equiped;
	}

	public List<AbilityCard> getAbilityCards() {
		return abilityCards;
	}

	public void setAbilityCards(List<AbilityCard> abilityCards) {
		this.abilityCards = abilityCards;
	}

	public List<Perk> getPerks() {
		return perks;
	}

	public void setPerks(List<Perk> perks) {
		this.perks = perks;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}
