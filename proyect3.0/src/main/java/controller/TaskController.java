
package controller;

import dao.DAO;
import model.Task;

import java.util.List;

public class TaskController {
    private DAO<Task> taskDAO;

    public TaskController(DAO<Task> taskDAO) {
        this.taskDAO = taskDAO;
    }

    public void addTask(Task task) {
        taskDAO.save(task);
    }

    public void removeTask(int id) {
        taskDAO.delete(id);
    }

    public Task getTaskById(int id) {
        return taskDAO.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }
}
