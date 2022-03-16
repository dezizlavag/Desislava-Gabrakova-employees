import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        File file = new File("employees.csv");
        ArrayList<Employee> employees = new ArrayList<>();
        Scanner scanner;
        try{
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not exists!");
            e.printStackTrace();
            return;
        }
        while (scanner.hasNext()){
            String[] attributes = scanner.nextLine().split(",");
            Employee employee = addEmployee(attributes);
            employees.add(employee);
        }
        long maxWorkingDays = 0L;
        int employee1Id = 0;
        int employee2Id = 0;
        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                Employee employee1 = employees.get(i);
                Employee employee2 = employees.get(j);
                if (employees.get(i).getDateFrom().isAfter(employees.get(j).getDateFrom())) {
                    employee1 = employees.get(j);
                    employee2 = employees.get(i);
                }
                if (employee1.getProjectId() == employee2.getProjectId()) {
                    if (employee2.getDateFrom().isBefore(employee1.getDateTo())) {
                        if (employee2.getDateTo().isBefore(employee1.getDateTo())) {
                            long maxDuration = ChronoUnit.DAYS.between(employee1.getDateTo(), employee2.getDateFrom());
                            if (maxDuration > maxWorkingDays) {
                                maxWorkingDays = maxDuration;
                                employee1Id = employee1.getEmployeeId();
                                employee2Id = employee2.getEmployeeId();
                            }
                        } else {
                            long maxDuration = ChronoUnit.DAYS.between(employee2.getDateFrom(), employee1.getDateTo());
                            if (maxDuration > maxWorkingDays) {
                                maxWorkingDays = maxDuration;
                                employee1Id = employee1.getEmployeeId();
                                employee2Id = employee2.getEmployeeId();
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Sample output: " + employee1Id +", "+ employee2Id +", "+ maxWorkingDays);
    }

    private static Employee addEmployee(String[] attributes){
        Employee employee = new Employee();
        employee.setEmployeeId(Integer.parseInt(attributes[0]));
        employee.setProjectId(Integer.parseInt(attributes[1]));
        employee.setDateFrom(LocalDate.parse(attributes[2]));
        if (attributes[3].equalsIgnoreCase("null")) {
            employee.setDateTo(LocalDate.now());
        }
        else {
            employee.setDateTo(LocalDate.parse(attributes[3]));
        }
        return employee;
    }
}

