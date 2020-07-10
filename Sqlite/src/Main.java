import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/home/peaceseeker/test.db");
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }
}
