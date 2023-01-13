# Vinyl

### Made by Filip Nowak

Vinyl is a Spring Boot project made for storing info related to songs, albums and authors

## Profiles

spring -> JpaRepository implementation

JPA -> ORM implementation using regular repositories + services

JDBC -> JdbcTemplate implementation -> around 70% done

collections -> List implementation

## Databases

Vinyl uses H2 and postgresql depending on your profile

- {"spring","JPA"} -> postgresql database named "postgres" with user "postgres" and password "postgres"
- {"JDBC"}->H2 database named "vinyldb" with empty password and username
- {"collections"} -> local lists

## Domain

### Author

An author is a person who wrote a song or an album

- Name
- Age
- Gender
- Albums
- Songs

### Album

An album is a collection of songs

- Name
- Release year
- Songs
- Genre
- Author

### Song

A song is a piece of music

- Title
- Length
- Index
- Authors
- Album

## Relations

Author -> Album --- One to Many

Album -> Song --- One to Many

Song -> Author --- Many to Many

## Contact

To report any bugs or issues, please contact me at: filip.nowak@student.kdg.be

## License

[MIT](https://choosealicense.com/licenses/mit/)