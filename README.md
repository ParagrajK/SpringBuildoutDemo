# SpringBuildoutDemo

This project is created to learn Spring-boot concepts. In this project, there are two user repositories. One repository is using static data where as other one is using data from MySQL database.

### List of topics which are covered in this project:

1. [RestController](https://github.com/ParagrajK/SpingDemo#restcontroller)
2. [Repository (Spring-Data-JPA Repository)](https://github.com/ParagrajK/SpingDemo#repository-spring-data-jpa-repository)
3. [REST exception handling with custom response entity](https://github.com/ParagrajK/SpingDemo#rest-exception-handling-with-custom-response-entity)
4. [API Versioning with using HandlerMethodArgumentResolver](https://github.com/ParagrajK/SpingDemo#api-versioning-with-using-handlermethodargumentresolver)
5. [Spring Qualified Bean and access it using Bean name](https://github.com/ParagrajK/SpingDemo#spring-qualified-bean-and-access-it-using-bean-name)

## Prerequisite

1. SpringToolSuite4
2. MySQL Server (Version 8.0+)
3. MySQL Workbench
4. Postman (REST API testing)

### Create a database with the table:

- Open MySQL Workbench and connect to the database by entering the password.
- Create a database with the name **SpringDemoDb** using following command and select the database for further actions.
```sql
create database SpringDemoDb
use SpringDemoDb
```
- Create a table with the name **user** using following command and insert some initial values in the table.
```sql
create table user (id int auto_increment, username varchar(255), occupation varchar(255), primary key (id));
insert into user (username, occupation) values ('A', 'B');
insert into user (username, occupation) values  ('AA', 'BB');
insert into user (username, occupation) values  ('CCC', 'CCC');
insert into user (username, occupation) values  ('DDDD', 'DDDD');
```

### Create a Spring Maven Project in SpringToolSuite:

- Create a new Spring Maven Project in SpringToolSuite and add following dependencies in the **pom.xml** file.
```xml
    		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
    
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.validation/validation-api -->
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator -->
		<dependency>
			<groupId>org.hibernate.validator</groupId>
			<artifactId>hibernate-validator</artifactId>
		</dependency>
    
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
    
		<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
```

## RestController

- The `UserController` is created using **@RestController** annotation. `UserController` can handle `GET`, `POST`, `PUT` & `DELETE` requests.
1. `GET` - `/users`: Returns the `List<Users>`
2. `POST` - `/users`: Add `User` to the database
3. `DELETE` - `/users/{id}`: Delete the particular `User` from the database
4. `GET` - `/users/{id}`: Return the particular `User` from the database
5. `PUT` - `/users/{id}`: Updated the particular `User` in the database

## Repository (Spring-Data-JPA Repository)

- The `UserDBRepository` is created using **@Repository** annotation. The `UserDBRepository` extends `JpaRepository`, the `JpaRepository` provides additional functionality like Sorting, find data by `Example`, etc. 
```java
@Repository
public interface UserDBRepository extends JpaRepository<User, Integer> {

}
```
- The `UserRepository` can also be extended from `CrudRepository` as we are doing CRUD operations only.
```java
@Repository
public interface UserDBRepository extends CrudRepository<User, Integer> {

}
```

## REST exception handling with custom response entity

- The `ErrorResponse` is used to send the error message and date as REST response.
- The `CustomExceptionHandler` is used to handle the exception of specific types or generic using the following syntax.
```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ErrorResponse> handleUserNotFoundException(@NonNull Exception ex, @NonNull WebRequest request) throws Exception {}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleUserNotFoundException(@NonNull Exception ex, @NonNull WebRequest request) throws Exception {}
```
- The class `CustomExceptionHandler` is extends from `ResponseEntityExceptionHandler` which we can use as centralise exception handler. `CustomExceptionHandler` should be annotated with **@ControllerAdvice** and **@Controller**.
- The methods declared in the classes which are marked as **@ControllerAdvice** annotation are apply globally to all controllers. However, we can use selectors if we need to filter controllers.

## API Versioning with using HandlerMethodArgumentResolver

- The new annotation **@APIVersion** has been created to detect API version from url and initialise the method parameter which are annotated with `@APIVersion`.
- The annotation is working like other annotations e.g. `@ResponseBody`, `@RequestParam`, etc.
- To resolve the API version, created a `@Component` as `APIVersionParameterResolver` which implements `HandlerMethodArgumentResolver`. Refer below sample code to resolve API version from url.
```java
@Component
public class APIVersionParameterResolver implements HandlerMethodArgumentResolver {

	private static final String VERSION = "version";

	@Override
	public boolean supportsParameter(@NonNull MethodParameter parameter) {
		return parameter.getParameterAnnotation(APIVersion.class) != null;
	}

	@Override
	public Object resolveArgument(@NonNull MethodParameter parameter, 
			@NonNull ModelAndViewContainer mavContainer,
			@NonNull NativeWebRequest webRequest, 
			@NonNull WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
		String version = httpServletRequest.getParameter(VERSION);

		Version apiVersion = new Version();
		if (StringUtils.isEmpty(version)) {
			APIVersion parameterAnnotation = parameter.getParameterAnnotation(APIVersion.class);
			if (parameterAnnotation != null) {
				apiVersion.setApiVersion(parameterAnnotation.value());
			}
			return apiVersion;
		}
		apiVersion.setApiVersion(Integer.parseInt(version));
		return apiVersion;
	}
}
```
- The `APIVersionParameterResolver` has been registered with `WebMvcConfigurer` to resolve the arguments.
```java
@Configuration
public class APIVersionResolverConfiguration implements WebMvcConfigurer {

	@Autowired
	APIVersionParameterResolver apiVersionParameterResolver;

	@Override
	public void addArgumentResolvers(@NonNull List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(apiVersionParameterResolver);
	}
}
```

## Spring Qualified Bean and access it using Bean name

- In this project, two services has been exposed.
1. `UserStaticDBService` - Uses `UserRepository` which has static list of User.
2. `UserMySQLDBService` - Uses `UserDBRepository` which works on MySQL database.
- The Beans for both the services has been created with the name. These types of Beans are called as qualified beans:
```java
@Service("Version1")
public class UserStaticDBService implements BaseUserService {}

@Service("Version2")
public class UserMySQLDBService implements BaseUserService {}
```
- When the request url contains *version=1*, then the `UserStaticDBService` is being invoked whereas for *version=2*, `UserMySQLDBService` is being invoked.
- The `UserController` is used to handle the request and invoke respective service by using version.
- As both the services implements BaseUserService, so Injected the services into the `UserController` in a following way:
```java
@Autowired
Map<String, BaseUserService> baseUserControllerImpl;
```
- Accessed the respective service by passing the Bean name i.e. version name and performed actions on it.
```java
BaseUserService = baseUserControllerImpl.get(version.toString());
```
