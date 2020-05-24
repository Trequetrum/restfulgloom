package ca.flearning.restfulgloom.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import ca.flearning.restfulgloom.entities.repassets.Item;

@Configuration
public class RepositoryRestConfig implements RepositoryRestConfigurer {

	/*
	@Override
	public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
		RepositoryRestConfigurer.super.configureJacksonObjectMapper(objectMapper);
		
		SimpleFilterProvider filters = new SimpleFilterProvider();
		filters.addFilter("JacksonIgnoreNullFalseZeroFilter", new JacksonIgnoreNullFalseNegOrZeroFilter(true));
		filters.addFilter("JacksonIgnoreNullFalseNegFilter", new JacksonIgnoreNullFalseNegOrZeroFilter(false));
		objectMapper.setFilterProvider(filters);
	}
	*/
	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Item.class);
    }

}
