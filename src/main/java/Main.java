import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Library library = new Library();
        library.getConnection();
        library.start();


        //return DriverManager.getConnection(dbURL, username, password);
    }


}



     /*   System.out.println("Welcome to 'My Library'!");
        Main main = new Main();
        main.start();
    }
    public void start(){
        }

    private void addDataToDb(Connection conn,
                             String nameSurname,
                             String phoneNumber,
                             String email,
                             int books)throws SQLException{
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf_db1", "JAVA_35_36", "Welcome2023")){
            this.registerNewUser();
        }catch (Exception e) {
            e.printStackTrace();


    }

    private UserData registerNewUser()  {

        UserData userData = new UserData();
        userData.setNameSurname(this.getUserInput("Enter the Name and Surname:"));
        userData.setPhoneNumber(this.getUserInput("Enter the phone number:"));
        userData.setEmail(this.getUserInput("Enter the email:"));

        if (userData.getNameSurname() == null || userData.getPhoneNumber()==null ||
        userData.getNameSurname().isBlank() || userData.getPhoneNumber().isBlank()
        ){
            int choice = JOptionPane.showConfirmDialog(null, "Contact is missing name or phone number, " +
                    "do you want to start again?");
            if (choice == JOptionPane.YES_OPTION) {
                return this.registerNewUser();
            }

    }return userData;
       // System.out.println("Hi user!");
        //return new String("hello user");
}

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }*/
