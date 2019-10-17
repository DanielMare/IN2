package IN2;
import static javax.swing.JOptionPane.*;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        //to authenticate user type...
        System.out.print("Enter user type (trainer || receptionist: ");
        String user = scanner.nextLine();

        //prnCustomerName is a value to be searched from the file
        System.out.print("Enter customer prn || full name: ");
        String prnCustomerName = scanner.nextLine();

        Customer cs = customer.searchCustomer(prnCustomerName);
        if (cs != null){
            if (user.equals(User.TRAINER.name().toLowerCase())){
                System.out.println("Welcome you logged in as personal trainer...");
                System.out.println("Writing customer detailes to file...");
                customer.writeToFile(cs);
            }else if (user.equals(User.RECEPTIONIST.name().toLowerCase())){

                System.out.println("Welcome you logged in as receptionist...");
                System.out.println("Let the user in...");
                customer.writeToFile(cs);
            }else {
                System.out.println("Invalid user");
            }
        }else {
            System.out.println("Customer is not available or has not paid");
        }

        //System.out.println("Printing valid customers: ");
        //  customer.printValidCustomers();
    }
}

