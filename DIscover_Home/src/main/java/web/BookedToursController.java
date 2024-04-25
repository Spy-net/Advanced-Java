package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.TourDao;
import dao.TourDaoImpl;
import model.Tour;

@WebServlet("/Mytours")
public class BookedToursController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TourDao bookedTourDao;

    @Override
    public void init() throws ServletException {
        super.init();
        bookedTourDao = new TourDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("user");
        if (userEmail != null && !userEmail.isEmpty()) {
            displayBookedTours(userEmail, request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }


    private void displayBookedTours(String userEmail, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Displaying Tour");
        try {
            List<Tour> bookedTours = bookedTourDao.selectTourByTouristEmail(userEmail);
            request.setAttribute("bookedTours", bookedTours);
            request.getRequestDispatcher("booked-tour.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing your request");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("In Post");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "delete":
                    handleDelete(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter missing");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("Tour Deleting");
        String idString = request.getParameter("id");
        if (idString != null && !idString.isEmpty()) {
            try {
                int id = Integer.parseInt(idString);
                boolean deleted = bookedTourDao.deleteTour(id);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/Mytours");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Tour not found");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tour ID");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting tour");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tour ID parameter missing");
        }
    }
}

