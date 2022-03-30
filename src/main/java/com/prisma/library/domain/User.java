package com.prisma.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PACKAGE;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "user")
@Getter
@Setter(value = PACKAGE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PACKAGE)
@NoArgsConstructor(access = PACKAGE)
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("First name")
    private String firstName;

    @JsonProperty("Member since")
    @Column(name = "member_since")
    @JsonFormat(shape = STRING, pattern = "MM/dd/yyyy")
    private Date memberSince;

    @JsonProperty("Member till")
    @JsonFormat(shape = STRING, pattern = "MM/dd/yyyy")
    @Column(name = "member_till")
    private Date memberTill;

    @JsonProperty("Gender")
    @Column(name = "gender")
    private String gender;
}
