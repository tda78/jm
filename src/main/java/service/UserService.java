package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {



    public User getTempUser();

        public void setTempUser(User tempUser);

        public User login(String name, String password) throws SQLException;

        public List<User> getAllUsers() throws SQLException;

        public User getUser(long id) throws SQLException;

        public void deleteUser(String id) throws DBException, SQLException;

        public void addUser(String name, String password, String role) throws SQLException;

        public void updateUser(String id, String name, String password, String role) throws SQLException;


    }
