
package dao;

import model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTaskDAO implements DAO<Task> {

    private static final String DATABASE_URL = "jdbc:sqlite:todo_list.db";

    // Connect to the SQLite database
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // Create the tasks table if it doesn't exist
    public void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "name TEXT NOT NULL, " +
                     "description TEXT, " +
                     "category TEXT, " +
                     "priority INTEGER, " +
                     "due_date TEXT, " +
                     "completada BOOLEAN" +
                     ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Table creation failed: " + e.getMessage());
        }
    }

    @Override
    public void save(Task task) {
        String sql = "INSERT INTO tasks(name, description, category, priority, due_date, completada) " +
                     "VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getCategory());
            pstmt.setInt(4, task.getPriority());
            pstmt.setString(5, task.getDueDate());
            pstmt.setBoolean(6, task.isCompletada());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    @Override
    public Task findById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";
        Task task = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("priority"),
                        rs.getString("due_date")
                );
                task.setCompletada(rs.getBoolean("completada"));
            }
        } catch (SQLException e) {
            System.out.println("Find by ID failed: " + e.getMessage());
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getInt("priority"),
                        rs.getString("due_date")
                );
                task.setCompletada(rs.getBoolean("completada"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println("Find all failed: " + e.getMessage());
        }
        return tasks;
    }
}
