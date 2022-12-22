import java.sql.*;
    public class DataBase {
        public static String NAME_USER = "root";
        public static String PASSWORD = "admin";
        public static String URL = "jdbc:mysql://localhost:3306/car_service";
        public static Connection connection;
        public static Statement statement;

        public void SetName(String NAME_USER){
            this.NAME_USER = NAME_USER;
        }
        public void SetPassword(String PASSWORD){
            this.PASSWORD = PASSWORD;
        }
        public void SetURL(String URL){
            this.URL = URL;
        }
        public String GetName(){
            return NAME_USER;
        }
        public String GetPassword(){
            return PASSWORD;
        }
        public String GetURL(){
            return URL;
        }

        public void EntraceToDataBase(String URL, String NAME_USER, String PASSWORD) {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();}
            catch(Exception e)
            {System.err.println("Unable to load driver:"+ e);}

            try {
                connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
            }catch(SQLException throwables){
                System.out.println("Нет соединения с БД!");
                System.exit(0);
            }
            System.out.println("Автотранспорт в автомастерской в формате: [id, марка машины, дата поступления, отремонтирована]");

            try {
                statement = connection.createStatement();
            } catch(SQLException throwables){
                throwables.printStackTrace();
                throw new RuntimeException();
            }
        }
        public void OutDataBase() throws SQLException {
            ResultSet rs = statement.executeQuery("SELECT * FROM car");
            while(rs.next()){
                System.out.println(rs.getRow() + ". " +
                        rs.getString("id_car") + "\t" + rs.getString("model") +"\t" + rs.getString("data") +"\t" + rs.getString("repaired"));
            }
            System.out.println();
            System.out.println("Мастера, работающие в автомастерской в формате: [id, Фамилия, Имя]");
            ResultSet rs1 = statement.executeQuery("SELECT * FROM masters");
            while(rs1.next()){
                System.out.println(rs1.getRow() + ". " +
                        rs1.getString("id_master") + "\t" + rs1.getString("firstname") +"\t" + rs1.getString("lastname"));
            }

            System.out.println();
            System.out.println("Связка мастеров, ремонтирующих автотранспорт в формате: [id, id автотранспорта, id мастера]");
            ResultSet rs2 = statement.executeQuery("SELECT * FROM communicate");
            while(rs2.next()){
                System.out.println(rs2.getRow() + ". " +
                        rs2.getString("id_communicate") + "\t" + rs2.getString("id_car") +"\t" + rs2.getString("id_master"));
            }
            statement.close();
        }
    }