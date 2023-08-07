public class UserData {
    private int id;
    private String nameSurname;
    private String phoneNumber;
    private String email;
    private int books;

    public UserData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }

    public UserData(int id, String nameSurname, String phoneNumber, String email, int books) {
        this.id = id;
        this.nameSurname = nameSurname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.books = books;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(id)
                .append(nameSurname)
                .append(phoneNumber)
                .append(email)
                .append(books)
                .toString();
    }

    public static void getDataFromSQL() {
    }
}
