package service;
import dao.UserDAO;
import dao.UserDaoFactory;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService{

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

    private static UserServiceImpl userServiceImpl;

    UserServiceImpl() throws Exception {
    }

    public static UserServiceImpl getInstance() throws Exception {
        if(userServiceImpl ==null) userServiceImpl = new UserServiceImpl();
        return userServiceImpl;
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

    public void addUser(User user) throws SQLException {
        try {
            dao.getUserByName(user.getName()).getId();
        } catch (Exception e) {
            dao.addUser(user);
        }
    }

    public void updateUser(User user) throws SQLException {
        dao.updateUser(user);
    }

 /*     public static void main(String[] args) throws Exception {
         UserServiceImpl service = new UserServiceImpl();
    //       service.addUser("qwe","jkjhhj");
    //       service.addUser("q76867we","jkjh9789hj");
    // service.deleteUser("3");
    //  service.updateUser("6", "Vasya", "Pupkin");
        List<User> users = service.getAllUsers();
        for (User user:users
             ) {
            System.out.println(user.getId() +" "+user.getName() + " "+user.getPassword());
        }*/

   // }


}

