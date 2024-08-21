import java.util.Scanner;
class Employee {
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public void displayEmployee() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Position: " + position);
        System.out.println("Salary: " + salary);
    }
}

public class EmployeeManagement {
    Employee[] employees;
    int size;

    public EmployeeManagement(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

     void addEmployee(Employee employee) {
        if (size < employees.length) {
            employees[size] = employee;
            size++;
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Array is full. Cannot add more employees.");
        }
    }

    public void searchEmployee(int employeeId) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                employees[i].displayEmployee();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found.");
        }
    }

    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("No employees in the array.");
        } else {
            for (int i = 0; i < size; i++) {
                employees[i].displayEmployee();
                System.out.println();
            }
        }
    }

    public void deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].employeeId == employeeId) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                employees[i] = employees[i + 1];
            }
            size--;
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found.");
        }
    }

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement(10);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("1. Add Employee");
                System.out.println("2. Search Employee");
                System.out.println("3. Traverse Employees");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter employee ID: ");
                        int employeeId = scanner.nextInt();
                        System.out.print("Enter employee name: ");
                        String name = scanner.next();
                        System.out.print("Enter employee position: ");
                        String position = scanner.next();
                        System.out.print("Enter employee salary: ");
                        double salary = scanner.nextDouble();
                        employeeManagement.addEmployee(new Employee(employeeId, name, position, salary));
                    }
                    case 2 -> {
                        System.out.print("Enter employee ID to search: ");
                        int employeeId = scanner.nextInt();
                        employeeManagement.searchEmployee(employeeId);
                    }
                    case 3 -> employeeManagement.traverseEmployees();
                    case 4 -> {
                        System.out.print("Enter employee ID to delete: ");
                        int employeeId = scanner.nextInt();
                        employeeManagement.deleteEmployee(employeeId);
                    }
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}