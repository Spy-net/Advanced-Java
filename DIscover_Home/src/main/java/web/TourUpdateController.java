package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.TourDao;
import dao.TourDaoImpl;
import model.Tour;

@WebServlet("/tour-update")
public class TourUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourDao tourDao;

    @Override
    public void init() throws ServletException {
        super.init();
        tourDao = new TourDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("user");
        if (userEmail != null && !userEmail.isEmpty()) {
            handleTourUpdate(request, response, userEmail);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    private void handleTourUpdate(HttpServletRequest request, HttpServletResponse response, String userEmail)
            throws ServletException, IOException {
    	System.out.println("Tour Updating");
        try {
            int tourId = Integer.parseInt(request.getParameter("id"));

            String meetingPoint = request.getParameter("meetingPoint");
            String tourPlacesString = request.getParameter("tourPlacesHidden");
            List<String> tourPlaces = new ArrayList<>();
            if (tourPlacesString != null && !tourPlacesString.isEmpty()) {
                tourPlaces = Arrays.asList(tourPlacesString.split(","));
            }
            LocalDateTime arrivalTime = LocalDateTime.parse(request.getParameter("arrivalTime"));
            LocalDateTime departureTime = LocalDateTime.parse(request.getParameter("departureTime"));
            int noOfPersons = Integer.parseInt(request.getParameter("noOfPersons"));
            BigDecimal price = new BigDecimal(request.getParameter("price"));

            Tour updatedTour = new Tour();
            updatedTour.setTourId(tourId);
            updatedTour.setMeetingPoint(meetingPoint);
            updatedTour.setTourPlaces(tourPlaces);
            updatedTour.setArrivalTime(arrivalTime);
            updatedTour.setDepartureTime(departureTime);
            updatedTour.setNoOfPersons(noOfPersons);
            updatedTour.setPrice(price);

            boolean updated = tourDao.updateTour(updatedTour);
            if (updated) {
                response.sendRedirect(request.getContextPath() + "/Mytours");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error updating tour");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input or database error");
        }
    }
}
