package org.airlineTickets.dto;

import lombok.Getter;
import lombok.Setter;
import org.airlineTickets.constant.AirplaneStatus;
import java.sql.Date;
import java.sql.Time;

@Getter @Setter
public class AirplaneDto {
    private Long airplaneId;

    private Date date;

    private Time departureTime;

    private Time destinationTime;

    private String departure;

    private String destination;

    private int remainingSeat;

    private AirplaneStatus airplaneStatus;
}
