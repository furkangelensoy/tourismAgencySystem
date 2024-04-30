package business;

import core.Helper;
import dao.UserDao;
import entity.Role;
import entity.User;

import java.util.ArrayList;

// This class performs the necessary operations on the view layer using the methods in the DAO layer.
public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public User findByLogin(String userName, String password) {
        return this.userDao.findByLogin(userName, password);
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public boolean save(User user) {
        if (user.getId() != 0) {
            Helper.showMessage("There is an another user with this id", "Error!");
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMessage("User not found", "Error!");
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage("User not found", "Error!");
            return false;
        }
        return this.userDao.delete(id);
    }

    // This method returns user list for fill the table in view layer.
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();

        for (User object : users) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = object.getId();
            rowObject[i++] = object.getUserName();
            rowObject[i++] = object.getPassWord();
            rowObject[i++] = object.getFirst_name();
            rowObject[i++] = object.getLast_name();
            rowObject[i++] = object.getMail();
            rowObject[i++] = object.getRole();
            userList.add(rowObject);
        }

        return userList;
    }

    public ArrayList<User> searchForUser(Role role) {
        if (role != null) {
            String query = "SELECT * FROM public.user WHERE user_role = '" + role.toString() + "' ORDER BY user_id ASC";
            return this.userDao.selectByQuery(query);
        } else {
            return this.userDao.findAll();
        }

    }
}
