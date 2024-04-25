package web;

import dao.SignupDao;
import model.Tourist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SignupDao signupDao;

    public void init() {
        signupDao = new SignupDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerTourist(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signup.jsp");
    }

    private void registerTourist(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("Username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("PhoneNumber");

        Tourist tourist = new Tourist();
        tourist.setUsername(username);
        tourist.setEmail(email);
        tourist.setPassword(password);
        tourist.setPhoneNumber(phoneNumber);

        try {
            int result = signupDao.registerTourist(tourist);
            if (result == 1) {
                request.setAttribute("NOTIFICATION", "Tourist Registered Successfully!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request, response);
    }
}
