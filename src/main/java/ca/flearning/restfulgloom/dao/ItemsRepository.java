package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.flearning.restfulgloom.entities.repassets.Item;

public interface ItemsRepository extends JpaRepository<Item, Long>{
}
