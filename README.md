# Kotlin Demo

This repo contains a Graphql API example using Spring Boot.

## Schema
```
schema {
  query: Query
  mutation: Mutation
}

type Query {
  bookById(id: ID): Book
  authorById(id: ID): Author
}

type Mutation {
  createBook(input: BookInput!): Book
}

input BookInput {
  name: String
  pageCount: Int
  genre: Genre
}

enum Genre {
  DRAMA
  THRILLER
  HORROR
}

type Book {
  id: ID
  name: String
  pageCount: Int
  genre: Genre
  author: Author
}

type Author {
  id: ID
  firstName: String
  lastName: String
  books: [Book]
}
```

## Recommended lectures

* [GraphQL Java Kickstart](https://www.graphql-java-kickstart.com)
* [GraphQL Java Tutorial](https://www.pluralsight.com/guides/building-a-graphql-server-with-spring-boot)

