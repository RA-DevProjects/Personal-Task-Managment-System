//Main class for the Personal Task Management System
package todo;

//Program entry point
public class Main {
    public static void main(String[] args) {
        System.out.println("DEBUG TEST: Program has started!");
        TaskManager model = new TaskManager();
        ConsoleView view = new ConsoleView();
        Controller ctrl = new Controller(model, view);
        ctrl.start();
    }
}
