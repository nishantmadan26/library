package com.prisma.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "book")
@Getter
@Setter(value = PACKAGE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor(access = PACKAGE)
public class Book {
    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;
    @JsonProperty("Title")
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    @JsonProperty("Author")
    private String author;
    @Column(name = "genre")
    @JsonProperty("Genre")
    private String genre;
    @Column(name = "publisher")
    @JsonProperty("Publisher")
    private String publisher;

}
