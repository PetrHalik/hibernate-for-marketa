package de.irs.fopengine.web.controllers;

import de.irs.fopengine.web.model.User;
import de.irs.fopengine.web.services.ProjectService;
import de.irs.fopengine.web.services.ProjectServiceImpl;
import de.irs.fopengine.web.services.UserService;
import de.irs.fopengine.web.services.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();
    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("inputPassword");
        User user = null;
        String address=null;
        try {
            user = userService.getUserByNamePassword(userName, password);
            if (Optional.ofNullable(user).isPresent()) {
                request.getSession().setAttribute("user", user);
                request.setAttribute("projects", projectService.findAll());
                address = "projectlist.jsp";
            } else {
                address = "error.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        RequestDispatcher view = request.getRequestDispatcher(address);
        view.forward(request, response);
    }

}

