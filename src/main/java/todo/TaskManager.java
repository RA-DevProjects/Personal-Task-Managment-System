//TaskManager class
package todo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//Handles all of the task management logic, this can include
// things like storing tasks, or adding new tasks etc
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public Task addTask(String title, String desc, LocalDate dueDate, Priority priority) {
        Task t = new Task(title, desc, dueDate, priority);
        tasks.add(t);
        return t;
    }

//remove task based on id
    public boolean removeTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

//find it based on id
    public Task findTask(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

//tasks sorted by due date
    public List<Task> getTasksSortedByDueDate() {
        return tasks.stream()
            .sorted(Comparator.comparing(t -> Optional.ofNullable(t.getDueDate()).orElse(LocalDate.MAX)))
            .collect(Collectors.toList());
    }

//Priority
    public List<Task> getTasksSortedByPriority() {
        return tasks.stream()
            .sorted(Comparator.comparing(Task::getPriority))
            .collect(Collectors.toList());
    }

//Compete
    public boolean markCompleted(int id) {
        Task t = findTask(id);
        if (t == null) return false;
        t.markCompleted();
        return true;
    }

//Task info updated
    public boolean updateTask(int id, String title, String desc, LocalDate dueDate, Priority priority) {
        Task t = findTask(id);
        if (t == null) return false;
        if (title != null && !title.isBlank()) t.setTitle(title);
        if (desc != null) t.setDescription(desc);
        t.setDueDate(dueDate);
        if (priority != null) t.setPriority(priority);
        return true;
    }
}
