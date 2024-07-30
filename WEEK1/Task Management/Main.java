
class Task {
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}
 class LinkedList {
    Node head;

    // Method to add a new task to the linked list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to search for a task by task ID
    public Task searchTask(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.taskId == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    // Method to traverse the linked list and print all tasks
    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    // Method to delete a task by task ID
    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.task.taskId == taskId) {
            head = head.next;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.task.taskId == taskId) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Task not found");
    }
}
public class Main {
    public static void main(String[] args) {
        LinkedList taskList = new LinkedList();

        // Create tasks
        Task task1 = new Task(1, "Task 1", "In Progress");
        Task task2 = new Task(2, "Task 2", "Done");
        Task task3 = new Task(3, "Task 3", "To Do");

        // Add tasks to the linked list
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        // Traverse the linked list and print all tasks
        System.out.println("All Tasks:");
        taskList.traverse();

        // Search for a task by task ID
        Task searchedTask = taskList.searchTask(2);
        System.out.println("Searched Task:");
        System.out.println(searchedTask);

        // Delete a task by task ID
        taskList.deleteTask(2);

        // Traverse the linked list and print all tasks after deletion
        System.out.println("Tasks after deletion:");
        taskList.traverse();
    }
}