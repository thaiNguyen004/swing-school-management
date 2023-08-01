package service;



import java.sql.*;
import java.util.List;
import model.beans.User;

public class UserService{

    public static String url = "";

    public UserService() {
        url = "jdbc:sqlserver://localhost:1433;databaseName=db01_asm_java3;user=PH33935;password=123;encrypt=false";
    }

    public String login(User user) {
        /*
        0 => không đúng user
        1 => đúng user nhưng sai mật khẩu
        2 => thành công
        */
        ResultSet resS = null;
        PreparedStatement ps = null;
        try (
                Connection connection = DriverManager.getConnection(url);) {
            String queryUsername = "SELECT * FROM UserInformations WHERE Username = ? AND isDelete = 0";
            String queryResult = "SELECT * FROM UserInformations WHERE Username = ? AND Password = ? AND isDelete = 0";
            ps = connection.prepareStatement(queryUsername);
            ps.setString(1, user.getUsername());
            if((resS = ps.executeQuery()).next()) {
                
                ps = connection.prepareStatement(queryResult);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                
                if((resS = ps.executeQuery()).next()) {
                    User userLogin = new User(resS.getString("Username")
                            , resS.getString("Password"), resS.getInt("Role"));
                    return "2" + "," + userLogin.getRole();
                } else {
                    return "1";
                }
            } 

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resS.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "0";
    }

    public boolean register(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean delUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
