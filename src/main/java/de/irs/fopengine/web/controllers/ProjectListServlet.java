package de.irs.fopengine.web.controllers;

import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.services.ProjectService;
import de.irs.fopengine.web.services.ProjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProjectListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String address;
        if (request.getParameter("btn_logout") != null) {
            address = "login.jsp";
        } else if (request.getParameter("btn_detail") != null) {
            Long projectId = Long.valueOf(request.getParameter("btn_detail"));
            Project project = null;
            try {
                project = projectService.findById(projectId);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            request.setAttribute("project", project);
            address = "projectdetail.jsp";
        } else {
            address = "error.jsp";
        }
        RequestDispatcher view = request.getRequestDispatcher(address);
        view.forward(request, response);
    }

}

