package Solution;

import java.util.ArrayList;
import java.util.Scanner;

public class PayrollSystem {

    public static void main(String[] args) {
        ArrayList<Employee> arrEmp = new ArrayList<Employee>();
        byte menuOption = 0;
        do {
            menuOption = showMenu();
            switch (menuOption) {
                case 1:
                    FullTime ft = readNewFullTime();
                    addEmployee(arrEmp, ft);
                    break;
                case 2:
                    PartTime pt = readNewPartTime();
                    addEmployee(arrEmp, pt);
                    break;
                case 3:
                    calcPayroll(arrEmp);
                    break;
                default:
                    break;
            }
        } while (menuOption != 4);
    }

    private static EmployeeBasicInfo readEmployeeBasicInfo() {
        Scanner kbd = new Scanner(System.in);
        System.out.print("Enter Id: ");
        int id = kbd.nextInt();
        System.out.print("\nEnter Name: ");
        String name = kbd.next();
        return new EmployeeBasicInfo(id, name, kbd);
    }

    private static class EmployeeBasicInfo {
        final int id;
        final String name;
        final Scanner scanner;
        
        EmployeeBasicInfo(int id, String name, Scanner scanner) {
            this.id = id;
            this.name = name;
            this.scanner = scanner;
        }
    }
    
    public static FullTime readNewFullTime() {
        EmployeeBasicInfo info = readEmployeeBasicInfo();
        System.out.print("\nEnter Salary: ");
        double salary = info.scanner.nextDouble();
        System.out.print("\nEnter Bonus: ");
        double bonus = info.scanner.nextDouble();
        return new FullTime(info.id, info.name, salary, bonus, getVehicle());
    }

    public static PartTime readNewPartTime() {
        EmployeeBasicInfo info = readEmployeeBasicInfo();
        System.out.print("\nEnter Hourly Rate: ");
        double rate = info.scanner.nextDouble();
        System.out.print("\nEnter Number of Hours Worked: ");
        double hoursWorked = info.scanner.nextDouble();
        return new PartTime(info.id, info.name, rate, hoursWorked, getVehicle());
    }

    public static byte showMenu() {
        byte menuOption = 0;
        Scanner kbd = new Scanner(System.in);
        System.out.println(""
            + "/* *************************************************/"
            + "\n/* 1. Add FullTime                               */"
            + "\n/* 2. Add PartTime                               */"
            + "\n/* 3. Calculate Payroll                          */" 
            + "\n/* 4. Exit                                       */"   
            + "\n/* *************************************************/");
        System.out.print("Input: "); menuOption = kbd.nextByte();
        return menuOption;
    }

    public static Vehicle getVehicle() {
        Scanner kbd = new Scanner(System.in);
        String hasVehicle = "N";
        System.out.println("\nDoes this employee have a vehicle? Y/N : ");
        hasVehicle = kbd.next();
        if (hasVehicle.equalsIgnoreCase("Y")) {
            System.out.println("\nEnter plate number: "); String auxPlate = kbd.next();
            System.out.println("\nEnter vehicle colour: "); String auxColour = kbd.next();
            return new Vehicle(auxPlate, auxColour);
        } else {
            return null;
        }
    }

    public static void addEmployee(ArrayList<Employee> pArrEmp, Employee pEmp) {
        pArrEmp.add(pEmp);
    }

    public static void calcPayroll(ArrayList<Employee> pArrEmp) {
        double totalCompanyPay = 0.0;
        System.out.print("\n**********************\n");
        for (Employee employee : pArrEmp) {
            double individualPay = calculateIndividualPay(employee);
            printEmployeeDetails(employee, individualPay);
            totalCompanyPay += individualPay;
        }
        printTotalPayroll(totalCompanyPay);
    }

    private static double calculateIndividualPay(Employee employee) {
        return employee.calculatePay();
    }

    private static void printEmployeeDetails(Employee employee, double individualPay) {
        String hasVehicle = employee.getVehicle() == null ? "No" : "Yes";
        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Has Vehicle: " + hasVehicle);
        printVehicleInfo(employee.getVehicle());
        System.out.println("Take Home Pay: " + individualPay);
    }

    private static void printVehicleInfo(Vehicle vehicle) {
        if (vehicle != null) {
            System.out.println("Plate Number: " + vehicle.getPlateNumber());
            System.out.println("Colour: " + vehicle.getColour());
        }
    }

    private static void printTotalPayroll(double totalCompanyPay) {
        System.out.println("------------\nTotal payroll of the company: " + totalCompanyPay + "\n----");
    }
}
