package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectService {

    Project findById(Long id) throws SQLException;

    List<Project> findAll() throws SQLException;

    int update(Project project) throws SQLException;

}
