package ca.flearning.restfulgloom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyStoreException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.flearning.restfulgloom.entities.Note;
import ca.flearning.restfulgloom.entities.accountbound.Equip;
import ca.flearning.restfulgloom.entities.accountbound.GCharacter;
import ca.flearning.restfulgloom.entities.accountbound.Wallet;
import ca.flearning.restfulgloom.entities.repassets.AbilityActionLine;
import ca.flearning.restfulgloom.entities.repassets.AbilityCard;
import ca.flearning.restfulgloom.entities.repassets.GClass;
import ca.flearning.restfulgloom.entities.repassets.Item;
import ca.flearning.restfulgloom.entities.repassets.Perk;
import ca.flearning.restfulgloom.security.JWTToken;

// This is not a production level class, so warnings for unused functions and such 
// can be ignored without a heavy heart.
@SuppressWarnings("unused")
@Component
public class RestfulgloomRunner implements ApplicationRunner{

	@Autowired // Tell the application-context to inject our EMF
	private EntityManagerFactory entityManagerFactory;
	
	@Autowired
	private ApplicationContext ac;
	
	@Value("${ca.flearning.restfulgloom.data.items}")
	private String itemInfoFile;
	
	/****
	 * Not sure where this kinda thing really belongs. 
	 * Going to throw some data in the H2 database so that I have something to look at/ test with
	 * It's all nonsense data, but JPA should keep it all in check for us.
	 * 
	 * Also, I'm not using any Java logging functionality here. This entire class is not meant to be
	 * production code.
	 ****/
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("    >> CommandLineRunner start");
		
		//ItemInfoConverter.hardCodedConversion();
		
		addDataToH2Database();
		//printAllBeanNames();
		
        System.out.println("    >> CommandLineRunner done");
    }
	
	private void printAllBeanNames() {
		System.out.println("    >> -----------------------------------------------------------------");
		
		String[] names = ac.getBeanNamesForType(Object.class);
		Arrays.stream(names).forEach(o	-> System.out.println("    >> " + o));
		
		System.out.println("    >> -----------------------------------------------------------------");
	}
	
	private void addDataToH2Database() {

        Random rand = new Random();
        
        // Start a DB transaction
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        
        // Generate and persist items in the DB
        Item[] gloomItems = generateItemsFromJSON();
        Arrays.stream(gloomItems).forEach(o -> em.persist(o));
        
        // Generate some characters, give them a name, note, and some random items
        List<GCharacter> lc = new ArrayList<GCharacter>();
        lc.add(genRandomChar("Miles", "This Character is going places", gloomItems));
        lc.add(genRandomChar("Golum", "My precious?", gloomItems));
        lc.add(genRandomChar("3mily", "ZAP ZAP ZAP!", gloomItems));
        lc.add(genRandomChar("Yap", "YapYapYapYapYapYapYapYapYapYapYapYapYapYapYapYap", gloomItems));
        lc.add(genRandomChar("McHammer", "Can't touch this", gloomItems));
        lc.add(genRandomChar("Adrian", "Give me all dem itamz plz!", gloomItems));
        lc.add(genRandomChar("Mike", "Nice try Mike", gloomItems));
        lc.add(genRandomChar("Kiss", "Out of love, there's nobody around, all I hear is the sound of a broken heart", gloomItems));
        lc.add(genRandomChar("Hoobastank", "I'm not a perfect person", gloomItems));
        lc.add(genRandomChar("Verbose", prettyLongString(), gloomItems));
       
     	// Persist these characters in the DB
        lc.forEach(o -> em.persist(o));
        
        // Generate some Ability Action lines (Not sure if I'm into these yet, but meh, we'll see where it goes.)
        List<AbilityActionLine> laal = new ArrayList<AbilityActionLine>();
        for(GCharacter c : lc) {
        	for(AbilityCard ac : c.getAbilityCards()) {
        		AbilityActionLine newline1 = new AbilityActionLine();
        		newline1.setAbilityCard(ac);
        		newline1.setMaxEnhancements(rand.nextInt(3));
        		newline1.setOrder(0);
        		newline1.setDescription(ac.getName() + ": top of card line 1");
        		newline1.setTop(true);
        		AbilityActionLine newline2 = new AbilityActionLine();
        		newline2.setAbilityCard(ac);
        		newline2.setMaxEnhancements(rand.nextInt(3));
        		newline2.setOrder(0);
        		newline2.setDescription(ac.getName() + ": buttom of card line 1");
        		newline2.setTop(false);
        		AbilityActionLine newline3 = new AbilityActionLine();
        		newline3.setAbilityCard(ac);
        		newline3.setMaxEnhancements(rand.nextInt(3));
        		newline3.setOrder(1);
        		newline3.setDescription(ac.getName() + ": buttom of card line 2");
        		newline3.setTop(false);
        		laal.add(newline1);
        		laal.add(newline2);
        		laal.add(newline3);
        	}
        }
        
        // Persist these ability action lines in the DB
        laal.forEach(o -> em.persist(o));
        
        // Commit the persisted changes and close our DB connection
        em.getTransaction().commit();
        em.close();
	}
	
	private Item[] generateItemsFromJSON() {
		
		File itemInfoFile = new File(this.itemInfoFile);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(itemInfoFile, Item[].class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private GCharacter genRandomChar(String name, String note, Item[] li) {
		
		Random rand = new Random(); 
		 
		GCharacter rtn = new GCharacter();
		rtn.setName(name);
		rtn.setExp(50 + rand.nextInt(150));
		rtn.setWallet(new Wallet(
				10 + rand.nextInt(100), 
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10),
				rand.nextInt(10)));
		rtn.setCheckMarks(rand.nextInt(18));
		
		rtn.setCharClass(new GClass());
	
		// Give the character 3 different unique items. 
		// Equip them all, even if that doesn't make any sense
		List<Item> gloomItems = Arrays.asList(li);
		Collections.shuffle(gloomItems);	
		if (gloomItems.size() > 2) {
			rtn.getEquiped().add(new Equip(rtn, true, gloomItems.get(0)));
			rtn.getEquiped().add(new Equip(rtn, true, gloomItems.get(1)));
			rtn.getEquiped().add(new Equip(rtn, true, gloomItems.get(2)));
		}
		
		AbilityCard ac1 = new AbilityCard();
		ac1.setName(name + "'s First Ability");
		ac1.setLevel(rand.nextInt(8) + 1);
		ac1.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac1);
		
		AbilityCard ac2 = new AbilityCard();
		ac2.setName(name + "'s Second Ability");
		ac2.setLevel(rand.nextInt(8) + 1);
		ac2.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac2);
		
		AbilityCard ac3 = new AbilityCard();
		ac3.setName(name + "'s Third Ability");
		ac3.setLevel(rand.nextInt(8) + 1);
		ac3.setInitiative(rand.nextInt(98) + 1);
		rtn.getAbilityCards().add(ac3);
		
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		rtn.getPerks().add(new Perk());
		
		rtn.getNotes().add(new Note(note));
		
		return rtn;
	}
	
	private String prettyLongString() {
		StringBuilder contentBuilder = new StringBuilder();
		
	    try (Stream<String> stream = Files.lines( Paths.get("src/main/resources/data/prettyLongString.txt"), StandardCharsets.UTF_8)) {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return contentBuilder.toString();
	}

}
