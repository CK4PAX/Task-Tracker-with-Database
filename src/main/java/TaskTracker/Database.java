package TaskTracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
    
    public static int updateTaskStatus(int id, String status){
        try (
                Connection conn = openOrCreateDatabase();
                PreparedStatement ps = conn.prepareStatement(""
                        + "UPDATE task SET status = ?,"
                        + "updateAt = datetime() WHERE id = ?")
                ){
                ps.setString(1,status);
                ps.setInt(2, id);
                ps.execute();
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    public static int updateTaskDescription(int id,String task){
        try (
                Connection conn = openOrCreateDatabase();
                PreparedStatement ps = conn.prepareStatement(""
                        + "UPDATE task SET description = ?,"
                        + "updateAt = datetime() WHERE id = ?")
                ){
                ps.setString(1,task);
                ps.setInt(2, id);
                ps.execute();
                return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    
    public static List<List<String>> getListWithOption(String option){
        List<List<String>> rows = new ArrayList<>();
        try 
        (
            Connection conn = openOrCreateDatabase();
            PreparedStatement ps = conn.prepareStatement(""
                    + "SELECT id, description FROM task WHERE status = ?");
        ){
            ps.setString(1, option);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                rows.add(new ArrayList<>());
                rows.getLast().add(Integer.toString(rs.getInt(1)));
                rows.getLast().add(rs.getString(2));
            }
            rs.close();
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<List<String>> getList(){
        List<List<String>> rows = new ArrayList<>();
        try 
        (
            Connection conn = openOrCreateDatabase();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, description FROM task");
        ){
            while(rs.next()){
                rows.add(new ArrayList<>());
                rows.getLast().add(Integer.toString(rs.getInt(1)));
                rows.getLast().add(rs.getString(2));
            }
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Integer> getAllId(){
        List<Integer> ids = new ArrayList<>();
        try 
        (
            Connection conn = openOrCreateDatabase();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM task");
        ){
            while(rs.next()){
                ids.add(rs.getInt(1));
            }
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static int deleteTask(int id){
        try (
                Connection conn = openOrCreateDatabase();
                PreparedStatement ps = conn.prepareStatement(""
                        + "DELETE FROM task WHERE id = ?")
                ){
                ps.setInt(1, id);
                ps.execute();
                return 0;
        } catch (SQLException e) {
            System.out.println("Error trying to delete the task");
            e.printStackTrace();
            return 1;
        }
    }
    
    public static int getIdOfLastInsert(Connection conn){
        try 
        (
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid();");
        ){
            int id = rs.getInt(1);
            if (id == 0) {
                System.out.println("Error, addTask and getIdOfLastInsert"
                        + "have to be atomic.");
                return id;
            }
            return id;
        } catch (SQLException e) {
            System.out.println("Error trying to get the id of the task");
            e.printStackTrace();
            return 0;
        }
    }
    public static int addTask(String task){
        int id = 0;
        try (
                Connection conn = openOrCreateDatabase();
                PreparedStatement ps = conn.prepareStatement(""
                        + "INSERT INTO task (description,status,createdAt)"
                        + "VALUES(?,?,datetime())")
                ){
                ps.setString(1, task);
                ps.setString(2, "todo");
                ps.execute();
                
                id = getIdOfLastInsert(conn);
                return id;
        } catch (SQLException e) {
            System.out.println("Error trying to save the task");
            e.printStackTrace();
            return 0;
        }
    }
    public static Connection openOrCreateDatabase() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:sqlite:src/main/resources/db/database.db");
            if(!checkIfExistTables(conn))createTableTask(conn);
            return conn;
        } catch (SQLException e) {
            System.out.println("Error trying to get connection");
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean checkIfExistTables (Connection conn)throws SQLException{
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("PRAGMA table_list");
        while(rs.next()){
            if(rs.getString(2).equals("task")){
                stmt.close();
                rs.close();
                return true;
            }
        }
        return false;
    }
    
    public static void createTableTask(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS task ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "description TEXT NOT NULL,"
                    + "status TEXT "
                    + "CHECK(status IN ('todo','in-progress','done')),"
                    + "createdAt TEXT,"
                    + "updateAt TEXT)");
        stmt.close();
    }

    
    
    public static void main(String[] args) {        
      
    }
    
}
