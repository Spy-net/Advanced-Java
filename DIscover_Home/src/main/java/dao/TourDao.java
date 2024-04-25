package dao;

import java.sql.SQLException;
import java.util.List;
import model.Tour;

public interface TourDao {

    void insertTour(Tour tour) throws SQLException;

    boolean deleteTour(int tourId) throws SQLException;

    boolean updateTour(Tour tour) throws SQLException;

    List<Tour> selectTourByTouristEmail(String userEmail);
}
