package service;
import dao.UserDAO;
import dao.UserDaoFactory;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;


public class UserService {

    private static UserDAO dao;
    private User tempUser;

    public User getTempUser() {
        return tempUser;
    }

    public void setTempUser(User tempUser) {
        this.tempUser = tempUser;
    }

    static {
        try {
            dao = UserDaoFactory.getDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static UserService userService;

    private UserService() throws Exception {
    }

    public static UserService getInstance() throws Exception {
        if(userService==null) userService = new UserService();
        return userService;
    }

    public User login(String name, String password) throws SQLException {
        User user = dao.getUserByName(name);
        if (!user.getPassword().equals(password)){
            throw new SecurityException("incorrect password");
        }
        tempUser = user;
        return user;
    }

    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    public User getUser(long id) throws SQLException {
        return dao.getUser(id);
    }

    public void deleteUser(String id) throws DBException, SQLException {

        dao.deleteUser(id);

    }

    public void addUser(String name, String password, String role) throws SQLException {
        try {
            dao.getUserByName(name).getId();
        } catch (Exception e) {
            dao.addUser(name, password, role);
        }
    }

    public void updateUser(String id, String name, String password, String role) throws SQLException {
        dao.updateUser(id, name, password, role);
    }

      public static void main(String[] args) throws Exception {
         UserService service = new UserService();
    //       service.addUser("qwe","jkjhhj");
    //       service.addUser("q76867we","jkjh9789hj");
    // service.deleteUser("3");
    //  service.updateUser("6", "Vasya", "Pupkin");
        List<User> users = service.getAllUsers();
        for (User user:users
             ) {
            System.out.println(user.getId() +" "+user.getName() + " "+user.getPassword());
        }

    }


}

