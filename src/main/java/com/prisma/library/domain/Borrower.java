package com.prisma.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PACKAGE;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "borrower")
@Getter
@Setter(value = PACKAGE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor(access = PACKAGE)
public class Borrower {
    @Id
    @GeneratedValue(strategy = AUTO)
    public UUID id;
    @JsonProperty("Borrower")
    @Column(name = "borrower")
    private String borrower;
    @JsonProperty("Book")
    @Column(name = "book")
    private String book;

    @Column(name = "borrowed_from")
    @JsonProperty("borrowed from")
    @JsonFormat(shape = Shape.STRING, pattern = "MM/dd/yyyy")
    private Date borrowedFrom;

    @Column(name = "borrowed_to")
    @JsonProperty("borrowed to")
    @JsonFormat(shape = Shape.STRING, pattern = "MM/dd/yyyy")
    private Date borrowedTo;
}
