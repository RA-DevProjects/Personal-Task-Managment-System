//Controller class
package todo;

import java.time.LocalDate;
import java.util.List;

//Controls interactions between view and model
public class Controller {
    private final TaskManager model;
    private final ConsoleView view;
    private boolean running = true;

//Controller constructor
    public Controller(TaskManager model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

//Main appreance loop
    public void start() {
        while (running) {
            view.showMenu();
            int choice = view.readInt();
            switch (choice) {
                case 1 -> handleAdd();
                case 2 -> handleViewAll();
                case 3 -> handleViewByDue();
                case 4 -> handleViewByPriority();
                case 5 -> handleMarkCompleted();
                case 6 -> handleEdit();
                case 7 -> handleDelete();
                case 0 -> running = false;
                default -> view.showMessage("Invalid option.");
            }
        }
        view.showMessage("Goodbye!");
    }

//adds new task
    private void handleAdd() {
        String title = view.readLine("Title: ");
        String desc = view.readLine("Description (optional): ");
        LocalDate due = view.readDate("Due date");
        Priority p = view.readPriority("Priority");
        Task t = model.addTask(title, desc, due, p);
        view.showMessage("Added: " + t);
    }

//Displays all tasks
    private void handleViewAll() {
        List<Task> all = model.getAllTasks();
        view.displayTasks(all);
    }

//Displays tasks sorted by due date
    private void handleViewByDue() {
        view.displayTasks(model.getTasksSortedByDueDate());
    }

//Displays tasks sorted by priority
    private void handleViewByPriority() {
        view.displayTasks(model.getTasksSortedByPriority());
    }

//Marks task as complete
    private void handleMarkCompleted() {
        int id = view.readInt();
        if (id <= 0) { view.showMessage("Provide a valid ID."); return; }
        boolean ok = model.markCompleted(id);
        view.showMessage(ok ? "Marked complete." : "Task not found.");
    }

//Edit existing class
    private void handleEdit() {
        view.showMessage("Enter task ID to edit:");
        int id = view.readInt();
        Task t = model.findTask(id);
        if (t == null) { view.showMessage("Not found."); return; }
        String title = view.readLine("New title (blank = keep): ");
        String desc = view.readLine("New description (blank = keep): ");
        LocalDate due = view.readDate("New due date");
        Priority p = view.readPriority("New priority");
        boolean ok = model.updateTask(id, title.isBlank() ? null : title, desc.isBlank() ? null : desc, due, p);
        view.showMessage(ok ? "Updated." : "Failed to update.");
    }

//Delete task
    private void handleDelete() {
        view.showMessage("Enter task ID to delete:");
        int id = view.readInt();
        boolean ok = model.removeTask(id);
        view.showMessage(ok ? "Deleted." : "Not found.");
    }
}
