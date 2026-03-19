//Task java
package todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

//Represent single task in system
public class Task {

    //generates unique task ID
    private static final AtomicInteger ID_GEN = new AtomicInteger(1);

    private final int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;
    private final LocalDateTime createdAt;

    //Creates new task object
    public Task(String title, String description, LocalDate dueDate, Priority priority) {
        this.id = ID_GEN.getAndIncrement();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = (priority == null) ? Priority.MEDIUM : priority;
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    //getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }

//this section updates task title, desc, due date, and priority 
    public void setTitle(String title) {
        if (title != null) this.title = title.trim();
    }


    public void setDescription(String description) {
        this.description = description;
    }

 
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

 
    public void setPriority(Priority priority) {
        if (priority != null) this.priority = priority;
    }

    //Mark completed
    public void markCompleted() {
        this.completed = true;
    }

    //Mark incomplete
    public void markUncompleted() {
        this.completed = false;
    }

    //Returns readable task info
    @Override
    public String toString() {
        return String.format("[%d] %s (priority=%s) %s%s",
                id,
                title,
                priority,
                (dueDate != null ? "due: " + dueDate.toString() + " " : ""),
                (completed ? "[COMPLETED]" : "")
        );
    }
}
