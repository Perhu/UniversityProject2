import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Rolls {

    public static int RollADice(){
        return (int)(Math.random()*6+1);
    }

    public static void InsertRollIntoDB(int First,int Second){
        DataBaseConnection ThisToConnect= new DataBaseConnection();
        Connection connectDB= ThisToConnect.GetConnection();

        String TheDBQuery= "insert into DiceRolls (FirstDice, SecondDice, Combined) values (?, ?, ?)";

        try {

            PreparedStatement preparedStmt = connectDB.prepareStatement(TheDBQuery);
            preparedStmt.setInt (1, First);
            preparedStmt.setInt (2, Second);
            preparedStmt.setInt (3, First+Second);

            preparedStmt.execute();
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static List<Integer> GetListOfRolls(){
        DataBaseConnection ThisToConnect= new DataBaseConnection();
        Connection connectDB= ThisToConnect.GetConnection();

        List<Integer> ListOfRolls=new ArrayList<Integer>();
        try {
            Statement TheStatement = connectDB.createStatement();
            String TheDBQuery= "select Combined from dicerolls";
            TheStatement.executeQuery(TheDBQuery);
            ResultSet QuerryOutput= TheStatement.executeQuery(TheDBQuery);
            while(QuerryOutput.next()){
                int Output=QuerryOutput.getInt("Combined");
                ListOfRolls.add(Output);
            }
            connectDB.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListOfRolls;
    }

    public static String RollsAsString(){
        String ResultString= "";
        for(int ARoll: Rolls.GetListOfRolls()) ResultString=ResultString+ARoll+" ";
        return ResultString;
    }
    public static int[] RollsAsArray(){
        int[] myNum = {0,0,0,0,0,0,0,0,0,0,0};
        for(int ARoll:Rolls.GetListOfRolls()) myNum[ARoll-2]++;
        return myNum;
    }
}
