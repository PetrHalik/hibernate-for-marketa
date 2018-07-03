package de.irs.fopengine.web.repositories;

import de.irs.fopengine.web.model.User;

import java.sql.SQLException;

public interface UserRepository {

    User getUserByNamePassword(String aUserName, String aPassword) throws SQLException;

}
