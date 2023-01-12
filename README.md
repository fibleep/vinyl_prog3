# Vinyl

### Made by Filip Nowak

Vinyl is a Spring Boot project made for storing info related to songs, albums and authors

## Profiles

spring -> JpaRepository implementation

JPA -> ORM implementation using regular repositories + services

JDBC -> JdbcTemplate implementation

collections -> List implementation

## Databases

Vinyl uses H2 and postgresql depending on your profile

- {"spring","JPA"} -> postgresql database named "postgres" with user "postgres" and password "postgres"
- {"collections","JDBC"}->H2 database named "vinyldb" with empty password and username

## Entities

Author -> Album --- One to Many

Album -> Song --- One to Many

Song -> Author --- Many to Many

## License

[MIT](https://choosealicense.com/licenses/mit/)