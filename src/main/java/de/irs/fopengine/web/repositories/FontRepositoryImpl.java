package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.database.Database;
import de.irs.fopengine.web.database.DatabaseInitialiser;
import de.irs.fopengine.web.model.Font;
import de.irs.fopengine.web.model.Project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FontRepositoryImpl implements FontRepository {

    @Override
    public List<Font> findByProjectId(Long projectId) throws SQLException {
        String sql = "SELECT * FROM font WHERE project_id = ?";
        List<String> params = new ArrayList<>();
        params.add("" + projectId);

        List<Font> result = new ArrayList<>();
        ResultSet rs = Database.getInstance().select(sql, params);
        if (rs == null) {
            return result;
        }
        while (rs.next()) {
            result.add(createFont(rs));
        }
        return result;
    }

    private Font createFont(ResultSet rs) throws SQLException {
        Font font;
        font = new Font();
        font.setId(rs.getLong("id"));
        font.setFontName(rs.getString("font_name"));
        return font;
    }

    @Override
    public int update(Font font) throws SQLException {
        String sql = "UPDATE user SET font_name=?, project_id=? WHERE id=?";
        List<String> params = new ArrayList<>();
        params.add(font.getFontName());
        params.add("" + font.getProjectId());
        params.add("" + font.getId());
        return Database.getInstance().update(sql, params);
    }

}
