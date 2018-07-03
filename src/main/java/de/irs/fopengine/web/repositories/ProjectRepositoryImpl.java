package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.database.Database;
import de.irs.fopengine.web.database.DatabaseInitialiser;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements ProjectRepository {

    private FontRepository fontRepository;

    public ProjectRepositoryImpl() {
        fontRepository = new FontRepositoryImpl();
    }

    @Override
    public Project findById(Long id) throws SQLException {
        String sql = "SELECT * FROM project WHERE id = ?";
        List<String> params = new ArrayList<>();
        params.add("" + id);
        ResultSet rs = Database.getInstance().select(sql, params);
        if (rs == null) {
            return null;
        }
        int count = 0;
        Project project = null;
        while (rs.next()) {
            if (count == 0) {
                project = createProject(rs);
            }
            ++count;
        }

        if (count == 0 || count > 1) {
            throw new SQLException("only 1 result expected");
        }
        return project;
    }

    private Project createProject(ResultSet rs) throws SQLException {
        Project project;
        project = new Project();
        project.setId(rs.getLong("id"));
        project.setName(rs.getString("name"));
        project.setGitUrl(rs.getString("url"));
        project.setDescription(rs.getString("description"));
        List<Font> fonts = fontRepository.findByProjectId(project.getId());
        fonts.forEach(font -> {
           // font.setProject(project);
            project.getFonts().add(font);
        });
        return project;
    }


    @Override
    public List<Project> findAll() throws SQLException {
        String sql = "SELECT * FROM project";
        List<Project> projects = new ArrayList<>();
        ResultSet rs = Database.getInstance().select(sql, new ArrayList<>());
        if (rs == null) {
            return projects;
        }
        while (rs.next()) {
            projects.add(createProject(rs));
        }
        return projects;
    }

    @Override
    public int update(Project project) throws SQLException {
        String sql = "UPDATE user SET name=?, description=?, url=? WHERE id=?";
        List<String> params = new ArrayList<>();
        params.add(project.getName());
        params.add(project.getDescription());
        params.add(project.getGitUrl());
        params.add("" + project.getId());
        Savepoint save1 = Database.getInstance().getConnection().setSavepoint();
        int countOfUpdatedRows = Database.getInstance().update(sql, params);
        if (countOfUpdatedRows != 1) {
            Database.getInstance().getConnection().rollback(save1);
            throw new SQLException("Trying to update one project but mode project would by updated.");
        }
        try {
            for (Font font : project.getFonts()) {
                fontRepository.update(font);
            }
        } catch (SQLException e) {
            Database.getInstance().getConnection().rollback(save1);
            throw e;
        }
        Database.getInstance().getConnection().setAutoCommit(true);
        return countOfUpdatedRows;
    }

}
