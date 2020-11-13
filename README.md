# SpringDemo

This project is created to learn Spring-boot concepts. In this project, there are two user repositories. One repository is using static data where as other one is using data from MySQL database.

### List of topics which are covered in this project:

1. RestController
2. Repository (Spring-Data-JPA Repository)
3. REST exception handling with custom response entity
4. API Versioning with using HandlerMethodArgumentResolver
5. Spring Qualified Bean and access it using Bean name

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

- Created a `UserController` using **@RestController** annotation. `UserController` is capable to handle `GET`, `POST`, `PUT` & `DELETE` requests.
1. `GET` - `/users`: Returns the `List<Users>`
2. `POST` - `/users`: Add `User` to the database
3. `DELETE` - `/users/{id}`: Delete the particular `User` from the database
4. `GET` - `/users/{id}`: Return the particular `User` from the database
5. `PUT` - `/users/{id}`: Updated the particular `User` in the database
