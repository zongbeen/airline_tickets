package org.airlineTickets.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.airlineTickets.constant.AirplaneStatus;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Getter @Setter @ToString
@Entity
@Table(name = "airplane")
public class Airplane {
    @Id //기본키 지정
    @Column(name = "airplane_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키 자동 생성
    private Long airplaneId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Time departureTime;

    @Column(nullable = false)
    private Time destinationTime;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private int remainingSeat;

    @Enumerated(EnumType.STRING)
    private AirplaneStatus airplaneStatus;
}
