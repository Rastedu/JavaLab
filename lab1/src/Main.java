import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataBase dataBase = new DataBase();
        dataBase.SetURL("jdbc:mysql://localhost:3306/car_service");
        dataBase.SetName("root");
        dataBase.SetPassword("admin");

        dataBase.EntraceToDataBase(dataBase.GetURL(),dataBase.GetName(),dataBase.GetPassword());
        dataBase.OutDataBase();
    }
}