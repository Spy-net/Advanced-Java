package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Tour;
import utils.JDBCUtils;

public class TourDaoImpl implements TourDao {

	private static final String INSERT_TOUR_SQL = "INSERT INTO tours (email, tour_name, description, price, meeting_point, status, date_created, tour_places, arrival_time, departure_time, no_of_persons) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_TOUR_BY_ID = "DELETE FROM tours WHERE tour_id = ?";
    private static final String UPDATE_TOUR = "UPDATE tours SET price = ?, meeting_point = ?, arrival_time = ?, departure_time = ?, no_of_persons = ?, tour_places = ? WHERE tour_id = ?";
    private static final String SELECT_TOURS_BY_TOURIST_EMAIL = "SELECT * FROM tours WHERE email = ?";

    @Override
    public void insertTour(Tour tour) throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TOUR_SQL)) {
            preparedStatement.setString(1, tour.getEmail());
            preparedStatement.setString(2, tour.getTourName());
            preparedStatement.setString(3, tour.getDescription());
            preparedStatement.setBigDecimal(4, tour.getPrice());
            preparedStatement.setString(5, tour.getMeetingPoint());
            preparedStatement.setString(6, tour.getStatus());
            preparedStatement.setObject(7, tour.getDateCreated());
            preparedStatement.setString(8, String.join(",", tour.getTourPlaces()));
            preparedStatement.setObject(9, tour.getArrivalTime());
            preparedStatement.setObject(10, tour.getDepartureTime());
            preparedStatement.setInt(11, tour.getNoOfPersons());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    
    @Override
    public boolean deleteTour(int tourId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TOUR_BY_ID)) {
            statement.setInt(1, tourId);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateTour(Tour tour) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TOUR)) {
            statement.setBigDecimal(1, tour.getPrice());
            statement.setString(2, tour.getMeetingPoint());
            statement.setObject(3, tour.getArrivalTime());
            statement.setObject(4, tour.getDepartureTime());
            statement.setInt(5, tour.getNoOfPersons());
            statement.setString(6, String.join(",", tour.getTourPlaces()));
            statement.setInt(7, tour.getTourId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public List<Tour> selectTourByTouristEmail(String userEmail) {
        List<Tour> tours = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TOURS_BY_TOURIST_EMAIL)) {
            preparedStatement.setString(1, userEmail);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int tourId = rs.getInt("tour_id");
                String email = rs.getString("email");
                String tourName = rs.getString("tour_name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                String meetingPoint = rs.getString("meeting_point");
                String status = rs.getString("status");
                LocalDateTime dateCreated = rs.getObject("date_created", LocalDateTime.class);
                LocalDateTime arrivalTime = rs.getObject("arrival_time", LocalDateTime.class);
                LocalDateTime departureTime = rs.getObject("departure_time", LocalDateTime.class);
                int noOfPersons = rs.getInt("no_of_persons");
                String tourPlacesString = rs.getString("tour_places");

                List<String> tourPlaces = new ArrayList<>();
                if (tourPlacesString != null && !tourPlacesString.isEmpty()) {
                    tourPlaces = Arrays.asList(tourPlacesString.split(","));
                } else {
                    tourPlaces.add("No tour places specified");
                }

                Tour tour = new Tour();
                tour.setTourId(tourId);
                tour.setEmail(email);
                tour.setTourName(tourName);
                tour.setDescription(description);
                tour.setPrice(price);
                tour.setMeetingPoint(meetingPoint);
                tour.setStatus(status);
                tour.setDateCreated(dateCreated);
                tour.setArrivalTime(arrivalTime);
                tour.setDepartureTime(departureTime);
                tour.setNoOfPersons(noOfPersons);
                tour.setTourPlaces(tourPlaces); 
                tours.add(tour);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tours;
    }
}
