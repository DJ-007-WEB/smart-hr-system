import java.util.*;

public class Main {

    static Map<Integer, Employee> empMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n<---- Smart HR System ---->");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee Role");
            System.out.println("5. Delete Employee");
            System.out.println("6. Show Unique Roles");
            System.out.println("7. Exit");

            int choice = getValidInt("Enter choice: ");

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: displayEmployees(); break;
                case 3: searchEmployee(); break;
                case 4: updateEmployee(); break;
                case 5: deleteEmployee(); break;
                case 6: showRoles(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    public static int getValidInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.next());
            } catch (Exception e) {
                System.out.println("Invalid number!");
            }
        }
    }

    public static double getValidDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                double val = Double.parseDouble(sc.next());
                if (val < 0) {
                    System.out.println("Value cannot be negative!");
                } else return val;
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public static String getValidString(String msg) {
        while (true) {
            System.out.print(msg);
            String val = sc.next();
            if (val.trim().isEmpty()) {
                System.out.println("Cannot be empty!");
            } else return val;
        }
    }


    public static void addEmployee() {

        System.out.println("Type: 1-Permanent 2-Contract 3-Intern");
        int type = getValidInt("Enter type: ");

        Employee e;

        if (type == 1) {
            e = new PermanentEmployee();
        } else if (type == 2) {
            e = new ContractEmployee();
        } else {
            e = new InternEmployee();
        }

        int id = getValidInt("Enter ID: ");

        if (empMap.containsKey(id)) {
            System.out.println("ID already exists!");
            return;
        }

        e.setEmpId(id);
        e.setName(getValidString("Enter Name: "));
        e.setEmail(getValidString("Enter Email: "));
        e.setRole(getValidString("Enter Role: "));

        if (e instanceof PermanentEmployee) {
            ((PermanentEmployee) e).setSalary(getValidDouble("Enter Salary: "));
        } else if (e instanceof ContractEmployee) {
            ((ContractEmployee) e).setHourlyRate(getValidDouble("Enter Hourly Rate: "));
            ((ContractEmployee) e).setHoursWorked(getValidInt("Enter Hours Worked: "));
        } else {
            ((InternEmployee) e).setStipend(getValidDouble("Enter Stipend: "));
        }

        empMap.put(id, e);
        System.out.println("Employee Added!");
    }

    public static void displayEmployees() {

        if (empMap.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        List<Employee> empList = new ArrayList<>(empMap.values());

        System.out.println("\n--------------------------------------------------------------------------");
        System.out.printf("%-5s %-10s %-15s %-15s %-10s\n",
                "ID", "Name", "Email", "Role", "Salary");
        System.out.println("--------------------------------------------------------------------------");

        for (Employee e : empList) {
            System.out.printf("%-5d %-10s %-15s %-15s %-10.2f\n",
                    e.getEmpId(),
                    e.getName(),
                    e.getEmail(),
                    e.getRole(),
                    e.calculateSalary());
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    public static void searchEmployee() {
        int id = getValidInt("Enter ID: ");

        Employee e = empMap.get(id);

        if (e != null) {
            System.out.println("Name: " + e.getName());
            System.out.println("Role: " + e.getRole());
            System.out.println("Salary: " + e.calculateSalary());
        } else {
            System.out.println("Not found!");
        }
    }

    public static void updateEmployee() {
        int id = getValidInt("Enter ID: ");

        Employee e = empMap.get(id);

        if (e != null) {
            e.setRole(getValidString("Enter new role: "));
            System.out.println("Updated!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public static void deleteEmployee() {
        int id = getValidInt("Enter ID: ");

        if (empMap.remove(id) != null) {
            System.out.println("Deleted!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    public static void showRoles() {

        Set<String> roles = new HashSet<>();

        for (Employee e : empMap.values()) {
            roles.add(e.getRole());
        }

        System.out.println("Unique Roles:");
        for (String r : roles) {
            System.out.println(r);
        }
    }
}