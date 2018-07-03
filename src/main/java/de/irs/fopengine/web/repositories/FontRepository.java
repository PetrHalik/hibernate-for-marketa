package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface FontRepository {

    List<Font> findByProjectId(Long id) throws SQLException;

    int update(Font font) throws SQLException;

}
