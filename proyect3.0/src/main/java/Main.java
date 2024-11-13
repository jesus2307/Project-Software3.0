
import dao.SQLiteTaskDAO;
import model.Task;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SQLiteTaskDAO dao = new SQLiteTaskDAO();
        dao.createTableIfNotExists();

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 4) {
            System.out.println("\n--- Menú de Gestión de Tareas ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar todas las tareas");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                option = Integer.parseInt(scanner.nextLine());

                if (option == 1) {
                    System.out.print("Ingrese el nombre de la tarea: ");
                    String name = scanner.nextLine();
                    System.out.print("Ingrese la descripción de la tarea: ");
                    String description = scanner.nextLine();
                    System.out.print("Ingrese la categoría de la tarea: ");
                    String category = scanner.nextLine();
                    System.out.print("Ingrese la prioridad de la tarea (1-5): ");
                    int priority = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese la fecha de vencimiento (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();

                    Task task = new Task(0, name, description, category, priority, dueDate);
                    dao.save(task);
                    System.out.println("Tarea agregada con éxito.");
                } else if (option == 2) {
                    List<Task> tasks = dao.findAll();
                    if (tasks.isEmpty()) {
                        System.out.println("No hay tareas disponibles.");
                    } else {
                        System.out.println("Tareas existentes:");
                        tasks.forEach(System.out::println);
                    }
                } else if (option == 3) {
                    System.out.print("Ingrese el ID de la tarea a eliminar: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    dao.delete(id);
                    System.out.println("Tarea eliminada con éxito.");
                } else if (option == 4) {
                    System.out.println("Saliendo del programa...");
                } else {
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }

        scanner.close();
    }
}
