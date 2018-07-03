package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.repositories.ProjectRepository;
import de.irs.fopengine.web.repositories.ProjectRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;

    public ProjectServiceImpl() {
        this.projectRepository = new ProjectRepositoryImpl();
    }

    @Override
    public Project findById(Long id) throws SQLException {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findAll() throws SQLException {
        return projectRepository.findAll();
    }

    @Override
    public int update(Project project) throws SQLException {
        return projectRepository.update(project);
    }
}
