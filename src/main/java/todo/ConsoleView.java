//ConsoleView class
package todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

//Handles all user input and outputs
public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

//Displays menu options
    public void showMenu() {
        System.out.println("\n=== Personal Task Manager ===");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. View Tasks (by due date)");
        System.out.println("4. View Tasks (by priority)");
        System.out.println("5. Mark Task Completed");
        System.out.println("6. Edit Task");
        System.out.println("7. Delete Task");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

//reads the integer input
    public int readInt() {
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException e) { return -1; }
    }

//reads text input
    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

//reads date inpuit
    public LocalDate readDate(String prompt) {
        System.out.print(prompt + " (YYYY-MM-DD or blank for none): ");
        String line = scanner.nextLine().trim();
        if (line.isBlank()) return null;
        try {
            return LocalDate.parse(line);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
            return null;
        }
    }

//reads priority value
    public Priority readPriority(String prompt) {
        System.out.print(prompt + " (LOW, MEDIUM, HIGH) [default MEDIUM]: ");
        String p = scanner.nextLine().trim().toUpperCase();
        if (p.isBlank()) return Priority.MEDIUM;
        try { return Priority.valueOf(p); }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid priority; using MEDIUM.");
            return Priority.MEDIUM;
        }
    }

//displays tasks
    public void displayTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("[no tasks]");
            return;
        }
        tasks.forEach(t -> System.out.println(t.toString()));
    }

//displays system message
    public void showMessage(String msg) { System.out.println(msg); }
}
