package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;

import java.sql.SQLException;
import java.util.List;

public interface FontService {

    /**
     * @param id
     * @return
     */
    Font findById(Long id) throws SQLException;

    /**
     *
     * @return
     */
    List<Font> findAll();

    /**
     *
     * @param projectId
     * @return
     */
    List<Font> findByProjectId(Long projectId) throws SQLException;
}
