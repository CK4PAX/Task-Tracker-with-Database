package TaskTracker;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    
    public DatabaseTest() {
    }
    @Test
    public void testAddTask(){
        String task = "Clean the house";
        int id = Database.addTask(task);
        Database.deleteTask(id);
        assertInstanceOf(Integer.class,id);
    }
    @Test
    public void testDeleteTask(){
        int id = Database.addTask("Do exercises");
        assertEquals(0,Database.deleteTask(id));
    }
    @Test
    public void testGetAllId(){
        assertNotNull(Database.getAllId());
    }
    @Test
    public void testGetList(){
        assertNotNull(Database.getList());
    }
    @Test
    public void testGetListWithOpcion(){
        String option = "todo";
        assertNotNull(Database.getListWithOption(option));
    }
    @Test
    public void testUpdateTaskDescription(){
        int id = Database.addTask("Wash the dishes");
        assertEquals(0,Database.updateTaskDescription(id,"wash the car"));
        Database.deleteTask(id);
    }
    @Test
    public void testUpdateTaskStatusInProgress(){
        int id = Database.addTask("Wash the dishes");
        assertEquals(0,Database.updateTaskStatus(id,"in-progress"));
        Database.deleteTask(id);
    }
    @Test
    public void testcheckIfExistTables() throws SQLException{
        Connection conn = Database.openOrCreateDatabase();
        assertDoesNotThrow(()->{Database.checkIfExistTables(conn);});
        conn.close();
    }
    @Test
    public void testCreateTableTask() throws SQLException{
        Connection conn = Database.openOrCreateDatabase();
        assertDoesNotThrow(()->{Database.createTableTask(conn);});
        conn.close();
    }

}
