package de.irs.fopengine.web.services;

import de.irs.fopengine.web.model.User;
import de.irs.fopengine.web.repositories.UserRepository;
import de.irs.fopengine.web.repositories.UserRepositoryImpl;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();

    }

    @Override
    public User getUserByNamePassword(String aUserName, String aPassword) throws SQLException {
        return userRepository.getUserByNamePassword(aUserName, aPassword);
    }
}
