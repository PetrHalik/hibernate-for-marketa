package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;
import de.irs.fopengine.web.repositories.FontRepository;
import de.irs.fopengine.web.repositories.FontRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FontServiceImpl implements FontService {

    FontRepository fontRepository;

    public FontServiceImpl() {
        fontRepository = new FontRepositoryImpl();
    }

    @Override
    public Font findById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List<Font> findAll() {
        return null;
    }

    @Override
    public List<Font> findByProjectId(Long projectId) throws SQLException {
        return fontRepository.findByProjectId(projectId);
    }
}
