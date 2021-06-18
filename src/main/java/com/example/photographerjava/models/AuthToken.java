package com.example.photographerjava.models;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
//    @Column(unique = true)
    @ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private User user;
}
