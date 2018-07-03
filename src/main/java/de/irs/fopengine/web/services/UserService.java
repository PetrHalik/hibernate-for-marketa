package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.User;

import java.sql.SQLException;

public interface UserService {

    User getUserByNamePassword(String aUserName, String aPassword) throws SQLException;

}
