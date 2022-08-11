import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection DataBaseLink;

    public Connection GetConnection() {
        String DataBaseUser="root";
        String DataBaseName="perhustore";
        String DataBasePassword="150699";
        String DataBaseURL="jdbc:mysql://localhost:3306/"+DataBaseName;

        try{
            //Class.forName("com.mysql.jdbc.Driver");
            DataBaseLink=DriverManager.getConnection(DataBaseURL, DataBaseUser, DataBasePassword);


        }catch(Exception e){
            e.printStackTrace();

        }
        return DataBaseLink;
    }
}