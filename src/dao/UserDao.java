package dao;

import core.Db;
import entity.Role;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }

    public User match(ResultSet resultSet) throws SQLException {
        User object = new User();
        object.setId(resultSet.getInt("user_id"));
        object.setUserName(resultSet.getString("user_username"));
        object.setPassWord(resultSet.getString("user_password"));
        object.setFirst_name(resultSet.getString("user_first_name"));
        object.setLast_name(resultSet.getString("user_last_name"));
        object.setMail(resultSet.getString("user_mail"));
        object.setRole(Role.valueOf(resultSet.getString("user_role")));
        return object;
    }

    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user ORDER BY user_id ASC";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                userList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String userName, String passWord){
        User object = null;
        String query = "SELECT * FROM public.user WHERE user_username = ? AND user_password = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2,passWord);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = new User();
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    public boolean save(User user){
        String query = "INSERT INTO public.user" +
                "(" +
                "user_username, " +
                "user_password, " +
                "user_first_name, " +
                "user_last_name, " +
                "user_mail, " +
                "user_role" +
                ")" +
                " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassWord());
            preparedStatement.setString(3,user.getFirst_name());
            preparedStatement.setString(4,user.getLast_name());
            preparedStatement.setString(5,user.getMail());
            preparedStatement.setString(6,user.getRole().toString());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user){
        String query = "UPDATE public.user SET " +
                "user_username = ? ," +
                "user_password = ? ," +
                "user_first_name = ? ," +
                "user_last_name = ? ," +
                "user_mail = ? ," +
                "user_role = ? " +
                "WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            preparedStatement.setString(3,user.getFirst_name());
            preparedStatement.setString(4,user.getLast_name());
            preparedStatement.setString(5,user.getMail());
            preparedStatement.setString(6,user.getRole().toString());
            preparedStatement.setInt(7,user.getId());
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int userId){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            return preparedStatement.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public User getById(int userId){
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        User object = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                object = this.match(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return object;
    }

    public ArrayList<User> selectByQuery(String query){
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(query);
            while (resultSet.next()){
                userList.add(this.match(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }


}
