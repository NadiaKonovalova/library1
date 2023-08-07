import exceptions.UserCreationCancelledException;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManege {

    ArrayList<UserData> users = new ArrayList<>();
    Library library = new Library();
    

    void findUserByNameSurname() throws SQLException {
        Library library = new Library();

        String userNameSurnameToFind = this.getUserInput("Enter your name and surname!");
        this.displayUser(this.findUserByValue(userNameSurnameToFind));
    }

    private void displayUser(ArrayList<UserData> users) {
        StringBuilder message = new StringBuilder();
        try{
            message.append("Name Surname\t" + "phoneNumber\t" + "email\t"+ "books");
            if (users.isEmpty()){
                message.append(" No user to display");
            }else {
                for (UserData userData: users){
                    message.append(userData.getNameSurname()).append("\t")
                            .append(userData.getPhoneNumber()).append("\t")
                            .append(userData.getEmail()).append("\t")
                            .append(userData.getBooks());
                }
            }

        }catch (Exception exception){
            message.append(exception.getMessage());
        }
        this.displayMessage(message.toString());
    }

    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }


    private ArrayList<UserData> findUserByValue(String userNameSurnameToFind) {
        String sql = "SELECT * FROM users ORDER BY book DESC";
        ArrayList<UserData> foundUser = new ArrayList<>();
        for(UserData currentUser : this.users){
            if (this.userMatches(currentUser,userNameSurnameToFind )){
                foundUser.add(currentUser);
            }
        }
        return foundUser;
    }

    private boolean userMatches(UserData currentUser, String userNameSurnameToFind) {
        return currentUser
                .toString()
                .toLowerCase()
                .trim()
                .replace(",", "")
                .contains(
                        userNameSurnameToFind
                                .trim()
                                .toLowerCase()
                                .replace(" ", ""));
    }

    private String  getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    public ArrayList<UserData> addNewUser(Connection conn,String nameSurname, String phoneNumber, String email, int books)throws SQLException {
        String sql = "INSERT INTO users (nameSurname, phoneNumber, email, books) VALUES(?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf_db1", "JAVA_35_36", "Welcome2023")){
            this.collectDataFromUser();
        }catch (Exception | UserCreationCancelledException e) {
            e.printStackTrace();
        } return null;
}

    private UserData collectDataFromUser()throws UserCreationCancelledException  {
        UserData userData = new UserData();
        userData.setNameSurname(this.getUserInput(" Enter your name and surname"));
        userData.setPhoneNumber(this.getUserInput("enter your phone number"));
        userData.setEmail(this.getUserInput("enter your email"));

        if (userData.getNameSurname() == null || userData.getPhoneNumber() == null || userData.getEmail() == null ||
                userData.getNameSurname().isBlank() || userData.getPhoneNumber().isBlank() || userData.getEmail().isBlank());
        int userChoice = JOptionPane.showConfirmDialog(
                null, "user is missing info, " + " Do you want to start registration again?");
            if (userChoice == JOptionPane.YES_OPTION){
                return this.collectDataFromUser();
            }
            throw new UserCreationCancelledException("add info is failed");
        }
    }

