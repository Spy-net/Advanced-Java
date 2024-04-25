package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Tour implements Serializable {
    private static final long serialVersionUID = 1L;
    private int tourId;
    private String email;
    private String tourName;
    private String description;
    private BigDecimal price;
    private String meetingPoint;
    private String status;
    private List<String> tourPlaces;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int noOfPersons;
    private LocalDateTime dateCreated;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMeetingPoint() {
        return meetingPoint;
    }

    public void setMeetingPoint(String meetingPoint) {
        this.meetingPoint = meetingPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getTourPlaces() {
        return tourPlaces;
    }

    public void setTourPlaces(List<String> tourPlaces) {
        this.tourPlaces = tourPlaces;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getNoOfPersons() {
        return noOfPersons;
    }

    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons = noOfPersons;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", email='" + email + '\'' +
                ", tourName='" + tourName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", meetingPoint='" + meetingPoint + '\'' +
                ", status='" + status + '\'' +
                ", tourPlaces='" + tourPlaces + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", noOfPersons=" + noOfPersons +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
