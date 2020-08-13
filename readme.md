# RESTful Gloom

## What is RESTful Gloom?
Side project to implement as I learn about the Java Spring Framework and it's various projects. The best way to learn is to do :).

### Learning goals/notes
* Spring's Dependency Injection (DI) and their implementation of Aspect Oriented Programming (AOP)
  * DI is (in many ways) at the heart of what makes Spring Boot so effective, but also what makes parts of it so frustrating to learn. You really need to understand the project you're working with since much is accomplished by:
    * DI and configure an existing bean
    * Create and expose the right Bean to Spring's inversion of control (IoC) container (for us that's [ApplicationContext](https://docs.spring.io/spring-framework/docs/5.2.8.RELEASE/javadoc-api/org/springframework/context/ApplicationContext.html))
  * Spring's AOP is done with proxy objects and handled alongside IoC so it can't be used on our POJOs by default. We'll use it primarily for security and logging.
* Spring's DAO, JDBC, and ORM support
  * It's mostly what you'd expect to find.
  * Transaction management is awesome. Use it and learn the gotchas to make it all efficient (to write **and** to run)
  * Spring has good native support for Hibernate (resource management, data access object (DAO) implementations, and transaction strategies). We'll get into the weeds.
* Spring Web MVC
  * Use it for our REST API. We return JSON responses instead of a view.
* Spring security
  * Learning security is no easy task. Spring (spring boot) makes it easier, but we'll be implementing a pretty involved authentication/authorisation strategy.
  * Learning OpenID and Oauth2 are the starting points here.
* Relational databases
  * There's always more to learn. The question is: how far down the rabbit hole do we go? RESTful Gloom's demands are very likely to remain fairly small here.
* Postman as API Client
  * Its got a lot of support to help develop/test APIs. Probably have a lot to learn here. I think it can be integrated into our testing suit in an automated way, but short of that it can constitute a separate set of tests.
  

### How it's put together and what it does so far:
* Backend Gloomhaven data store (H2 in memory database during development)
  * Gloomhaven schema designed to work reasonably well with ORM techniques but also be relatively (pun?) performant for queries.
  * JPA with Hibernate
* Web model view controller (MVC) for REST endpoints 
  * This kinda takes the 'V' out of MVC, but works pretty well!
  * Let the server host some static HTML alongside the API. Though realistically this README should track such a landing page. 
* Restful API to PUT/POST data and GET info (stats, etc)
  * Serialize to/from JSON with Jackson
  * Expose data for API exploration via Spring HATEOAS (More complex operations will have to be implemented differently)
* Secure endpoints with Spring Security
  * This is all about understanding the servlet filter chain and how spring security plugs itself into it. [9. Servlet Security: The Big Picture](https://docs.spring.io/spring-security/site/docs/5.4.0-RC1/reference/html5/#servlet-architecture) takes you to an overview of how that works.
* OpenID to get identification credentials from third party (Google, Guthub, Facebook, etc) and then use our own security behind that:
  * We don't require access to our user's login credentials so we never store a password.
  * Spring Security has Oauth2 support for our resource server (API), but not for an [authorization server](https://tools.ietf.org/html/rfc6749#section-1.1)
  * Spring Security Oauth2 is now deprecated but does have an authorization server implementation we can use. Currently, we have our own custom solution using JWTs.
  
  
