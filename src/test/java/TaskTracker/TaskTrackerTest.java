package TaskTracker;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yumerth-mijail
 */
public class TaskTrackerTest {
    
    public TaskTrackerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddExistSecondArgument() {
        String[] args = {"first"};
        assertEquals(1, TaskTracker.ValidateQuantityOfArguments(args,2));
    }
    
    @Test
    public void testAddOverTwoArgument() {
        String[] args = {"first","second","third"};
        assertEquals(1, TaskTracker.ValidateQuantityOfArguments(args,2));
    }
    @Test
    public void testAddTask(){
        String[] args = {"add","Brush teeth"};
        int id = TaskTracker.addTask(args);
        assertNotEquals(0, id);
        Database.deleteTask(id);
    }
    @Test
    public void testDeleteTask(){
        String[] add_args = {"add","Brush teeth"};
        int id = TaskTracker.addTask(add_args);
        String[] delete_args = {"delete",Integer.toString(id)};
        assertEquals(0, TaskTracker.deleteTask(delete_args));
    }
    @Test
    public void testIsIntSecondArgumentOnDelete(){
        String[] args = {"delete","2"};
        assertTrue(TaskTracker.isIntSecondArgument(args));
    }
    @Test
    public void testIsNotIntSecondArgumentOnDelete(){
        String[] args = {"delete","uno1"};
        assertFalse(TaskTracker.isIntSecondArgument(args));
    }
     @Test
     public void testValidateAmountOfArgsOfListTaskWithThreeArgs(){
         String[] args = {"list","todo","5"};
         assertEquals(1,TaskTracker.validateAmountOfArgsOflistTask(args));
     }
     @Test
     public void testValidateAmountOfArgsOfListTaskWithTwoArgs(){
         String[] args = {"list","todo"};
         assertEquals(0,TaskTracker.validateAmountOfArgsOflistTask(args));
     }
     @Test
     public void testValidateAmountOfArgsOfListTaskWithOneArgs(){
         String[] args = {"list"};
         assertEquals(0,TaskTracker.validateAmountOfArgsOflistTask(args));
     }
     @Test
     public void testCorrectSecondArgOfListTask(){
         String[] args = {"list","todo"};
         assertEquals(0,TaskTracker.validateSecondArgOfListTask(args));
     }
     @Test
     public void testIncorrectSecondArgOfListTask(){
         String[] args = {"list","to do"};
         assertEquals(1,TaskTracker.validateSecondArgOfListTask(args));
     }
     @Test
     public void testShowList(){
         List<List<String>> list = new ArrayList<>();
         for (int i = 0; i < 5; i++) {
             list.add(new ArrayList<>());
             for (int j = 0; j < 5; j++) {
                list.getLast().add("AAAA");
             }
         }
         assertDoesNotThrow(()->{TaskTracker.showList(list);});
     }
     @Test
     public void testListCasetWithMoreThanTwoArgs(){
         String[] args = {"list","done","23"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     @Test
     public void testLisCasetWithFailSecondArgs(){
         String[] args = {"list","donee"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     @Test
     public void testListCaseWithOneArgs(){
         String[] args = {"list"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     @Test
     public void testListCaseWithCorrectTwoArgs(){
         String[] args = {"list","done"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     /*
     @Test
     public void testValidateAmountofArgsOfUpdateTaskWithFourArgs(){
         String[] args = {"update","4","Buy a phone","todo"};
         assertEquals(1,TaskTracker.validateAmountOfArgsOfUpdateTask(args));
     }
     public void testValidateAmountofArgsOfUpdateTaskWithTwoArgs(){
         String[] args = {"update","4"};
         assertEquals(1,TaskTracker.validateAmountOfArgsOfUpdateTask(args));
     }
     public void testValidateAmountofArgsOfUpdateTaskWithThreeArgs(){
         String[] args = {"update","4","Buy a phone"};
         assertEquals(0,TaskTracker.validateAmountOfArgsOfUpdateTask(args));
     }
     */
     public void testUpdateCaseCorrectAmountArgs(){
         String[] args = {"update","4","Buy a phone"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testUpdateCaseWithTwoArgs(){
         String[] args = {"update","4"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testUpdateCaseWithFourArgs(){
         String[] args = {"update","4","Buy a phone","todo"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testUpdateCaseIncorrectSecondArgument(){
         String[] args = {"update","uno","Buy a phone"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testUpdateCaseCorrectArguments(){
         String task = "Buy a phone";
         int id = Database.addTask(task);
         String[] args = {"update",Integer.toString(id),"Buy a laptop"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
         Database.deleteTask(id);
     }
     // Tests for Mark-in-progress case
     public void testMarkInProgressCaseCorrectAmountArgs(){
         String[] args = {"mark-in-progress","4"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkInProgressCaseWithOneArg(){
         String[] args = {"mark-in-progress"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkInProgressCaseWithThreeArgs(){
         String[] args = {"mark-in-progress","4","in-progress"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testIsIntSecondArgInMarkInProgressCase(){
         String[] args = {"mark-in-progress","cuatro"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkInProgressCaseCorrectArguments(){
         String task = "Read a book";
         int id = Database.addTask(task);
         String[] args = {"mark-in-progress",Integer.toString(id)};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
         Database.deleteTask(id);
     }
     // Test for mark-done
     public void testMarkDoneCaseCorrectAmountArgs(){
         String[] args = {"mark-done","4"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkDoneCaseWithOneArg(){
         String[] args = {"mark-done"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkDoneCaseWithThreeArgs(){
         String[] args = {"mark-done","50","in-progress"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testIsIntSecondArgInMarkDoneCase(){
         String[] args = {"mark-done","uno"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
     public void testMarkDoneCaseCorrectArguments(){
         String task = "Read a book";
         int id = Database.addTask(task);
         String[] args = {"mark-done",Integer.toString(id)};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
         Database.deleteTask(id);
     }
     public void testDefaultCaseWithRandomArguments(){
         String[] args = {"1?dsd32·","sdfs di","212.51"};
         assertDoesNotThrow(()->{TaskTracker.main(args);});
     }
}
