package com.perustars.events.domain.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(updatable = true, nullable = false, length = 250)
    private String eventTitle;

    @Column(updatable = true, nullable = false, length = 255)
    private String eventDescription;

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateStart;

    @Column(updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEnd;

}
