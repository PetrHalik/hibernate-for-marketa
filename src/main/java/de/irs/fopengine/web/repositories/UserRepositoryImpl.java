package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.database.Database;
import de.irs.fopengine.web.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User getUserByNamePassword(String username, String password) throws SQLException {
        String sql = "SELECT username FROM user WHERE username=? and password=?";
        List<String> params = new ArrayList<>();
        params.add(username);
        params.add(password);
        ResultSet rs = Database.getInstance().select(sql, params);
        if (rs == null) {
            return null;
        }
        int count = 0;
        User user=null;
        while (rs.next()) {
            if(count == 0) {
                user = new User();
                user.setUsername(rs.getString("username"));
            }
            ++count;
        }
        if (count == 0 || count>1) {
            return null;
        }
        return user;
    }
}
