package web;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TourDao;
import dao.TourDaoImpl;
import model.Tour;

@WebServlet("/tourBooking")
public class TourBookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourDao tourDao;

    public void init() {
        tourDao = new TourDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        bookTour(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("booking-form.jsp").forward(request, response);
    }

    private void bookTour(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Tour Booking");
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("user");

        if (userEmail != null) {
            String tourName = request.getParameter("tourName");
            String description = request.getParameter("description");
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String meetingPoint = request.getParameter("meetingPoint");
            String status = request.getParameter("status");
            LocalDateTime dateCreated = LocalDateTime.now();

            String tourPlacesString = request.getParameter("tourPlacesHidden");
            List<String> tourPlaces = new ArrayList<>();
            if (tourPlacesString != null && !tourPlacesString.isEmpty()) {
                tourPlaces = Arrays.asList(tourPlacesString.split(","));
            }

            LocalDateTime arrivalTime = LocalDateTime.parse(request.getParameter("arrivalTime"));
            LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
            int noOfPersons = Integer.parseInt(request.getParameter("noOfPersons"));

            Tour tour = new Tour();
            tour.setEmail(userEmail);
            tour.setTourName(tourName);
            tour.setDescription(description);
            tour.setPrice(price);
            tour.setMeetingPoint(meetingPoint);
            tour.setStatus(status);
            tour.setDateCreated(dateCreated);
            tour.setTourPlaces(tourPlaces);
            tour.setArrivalTime(arrivalTime);
            tour.setDepartureTime(departureTime);
            tour.setNoOfPersons(noOfPersons);

            try {
                tourDao.insertTour(tour);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect(request.getContextPath() + "/Mytours");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
