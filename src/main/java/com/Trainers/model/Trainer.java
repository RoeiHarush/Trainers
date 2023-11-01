package com.Trainers.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "trainer")
public class Trainer

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Session> sessions;

    public String first_Name;
    public String last_Name;
    public String email;
    public String user_Name;
    public String password;
    public String category;
    public String bio;
    public String location;
    public String availability;



}
