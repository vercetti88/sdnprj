package com.sdn.prj.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "iin")
    private String iin;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String phone;

    @Column(name = "promocode")
    private String promocode;


    public Person(String iin, String name, String phone){
        this.iin = iin;
        this.name = name;
        this.phone = phone;
    }
}
