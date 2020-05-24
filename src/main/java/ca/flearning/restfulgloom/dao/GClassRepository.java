package ca.flearning.restfulgloom.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.flearning.restfulgloom.entities.repassets.GClass;

@RepositoryRestResource(collectionResourceRel = "classes", path = "classes")
public interface GClassRepository extends JpaRepository<GClass, Long>{
}
