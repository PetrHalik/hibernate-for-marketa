package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface ProjectRepository {

    int update(Project project) throws SQLException;

    List<Project> findAll() throws SQLException;

    Project findById(Long id) throws SQLException;
}
