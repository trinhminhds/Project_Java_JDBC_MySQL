import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String host = "localhost";

        int port = 3306;

        String database = "quanlysinhvien";

        String username = "root";

        String password = "123456";

        String jdbcUrd = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try{

            Connection connection = DriverManager.getConnection(jdbcUrd, username, password);
            System.out.println("Connection database successfully...");

            Statement statement = connection.createStatement();

            String sql = "SELECT * FROM SINHVIEN";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("maSinhVien");
                String lastname = resultSet.getString("hoDem");
                String name = resultSet.getString("ten");
                Date ngsinh = resultSet.getDate("ngaySinh");
                int namNhapHoc = resultSet.getInt("namNhapHoc");
                String machuongtrinh = resultSet.getString("maChuongTrinh");
                String sex = resultSet.getString("gioiTinh");
                System.out.println("ID: " + id + ", LastName: " + lastname + ", NAME: " + name + ", Brith:" + ngsinh + 
                ", YearSchool:" + namNhapHoc + ", IDOJECT: " + machuongtrinh + ", SEX:"+ sex);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
