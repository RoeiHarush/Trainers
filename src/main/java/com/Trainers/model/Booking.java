package com.Trainers.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "bookings")
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;


    private LocalDateTime bookingDateTime;


    public Booking(User user, Session session) {
        this.user = user;
        this.session = session;

    }
}
