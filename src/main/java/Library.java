import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class Library {
    private ArrayList<Book> books;
    private Map<String, ArrayList<Book>> users;
    UserManege userManege = new UserManege();

    public void start() throws IOException, SQLException {

        String userChoice = " ";
        while (!userChoice.equals("3")){
            userChoice = JOptionPane.showInputDialog(
                    new StringBuilder()
                            .append("Welcome to Library, Please choose an option:")
                            .append("\n1. Log in")
                            .append("\n2. Register new user")
                            .append("\n3. Exit")
                            .toString()
            );


            switch (userChoice) {
                case "1":
                    this.userManege.findUserByNameSurname();
                    break;
                case "2":
                   this.userManege.addNewUser();
                    break;
            }
        }
    }


    /* public static void Library() throws SQLException {
         books = new ArrayList<>();
         users = new HashMap<>();
     }*/
/// ???
   // public boolean registerUser(String nameSurname) {
   //     if (!users.containsKey(nameSurname)) {
     //       users.put(nameSurname, new ArrayList<>());
       //     return true;
       // }
       // return false;
   // }

    public void addBook(Book book) throws SQLException {

        try (Connection conn = getConnection();
                              PreparedStatement statement = conn.prepareStatement(
                                      "INSERT INTO books (author, title, yearOfRelease, language, genres, copyOfBook, availableCopyOfBook) VALUES (?, ?, ?, ?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getYearOfRelease());
            statement.setString(4, book.getLanguage());
            statement.setString(5, book.getGenres());
            statement.setInt(6, book.getCopyOfBook());
            statement.setBoolean(7,book.isAvailableCopyOfBook());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0){
                throw new SQLException("book not saved!");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    book.setBookId(generatedKeys.getString(1));
                } else {
                    throw new SQLException("Creating book failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/bookshelf_db1";
        String username = "JAVA_35_36";
        String password = "Welcome2023";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("connection is successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }


    public void removeBook(Book book) {
        books.remove(book);
    }


   /* public ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();

       try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                String BookId = rs.getString("BookId");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int yearOfRelease = rs.getInt("yearOfRelease");
                String language = rs.getString("language");
                String genres = rs.getString("genres");
                int copyOfBook = rs.getInt("copyOfBook");
                boolean availableCopyOfBook =rs.getBoolean("availableCopyOfBook");

             ;

                Book book = new Book(author, title, yearOfRelease, language, genres, copyOfBook, availableCopyOfBook);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Connection) books;
    }*/

}