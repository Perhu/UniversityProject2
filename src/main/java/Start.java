import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Start {

    public static void ClearDB(){
        DataBaseConnection ThisToConnect= new DataBaseConnection();
        Connection connectDB= ThisToConnect.GetConnection();

        String TheDBQuery= "DROP TABLE IF EXISTS `DiceRolls`";
        String TheDBQuery2="create table DiceRolls(" +
                "theID int auto_increment," +
                "FirstDice int," +
                "    SecondDice int," +
                "    Combined int," +
                "    PRIMARY KEY (`theID`)" +
                "    )";
        try {
            Statement TheStatement=connectDB.createStatement();
            TheStatement.executeUpdate(TheDBQuery);
            TheStatement.executeUpdate(TheDBQuery2);
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClearDB();
                for(int i=0;i<300;i++) Rolls.InsertRollIntoDB(Rolls.RollADice(),Rolls.RollADice());
                Histogram TheHistogram = new Histogram();
            }
        });
    }
}
