
package test;

import model.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskCompletion() {
        Task task = new Task(1, "Test Task", "Description", "Work", 1, "2024-12-01");
        task.setCompletada(true);
        assertTrue(task.isCompletada());
    }
}
