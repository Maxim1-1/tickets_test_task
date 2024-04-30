package org.tickets.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Flight implements Comparable<Flight> {
    private String origin;
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy k:m")
    private Date data;
    private Integer duration;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int compareTo(Flight otherFlight) {
        return this.getData().compareTo(otherFlight.getData());
    }
}
