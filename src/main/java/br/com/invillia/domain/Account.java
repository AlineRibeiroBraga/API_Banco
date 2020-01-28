package br.com.invillia.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    public static final double BOUND = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long account;

    @Column(nullable = false)
    private long agency;

    @Column(nullable = false)
    private double balance;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Person person;
}