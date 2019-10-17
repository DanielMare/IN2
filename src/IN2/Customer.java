package IN2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private String personNumber;
    private LocalDate datePayment;
    private List<Customer> customers;
    /*
    the first constructor loads the file from the disk by using getCustomers() method
    which indirectly calls the second constructor
     */
    public Customer(){
        customers = getCustomers();
    }
    public Customer(String name, String personNumber, String datePayment){
        this.name = name;
        this.personNumber = personNumber;
        this.datePayment = parseDate(datePayment);
    }

    public LocalDate getDatePayment() {
        return datePayment;
    }

    public String getName(){
        return name;
    }

    public String getPersonNumber(){
        return personNumber;
    }

    public LocalDate parseDate(String date){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return LocalDate.parse(date, dateTimeFormatter);
    }

    public List<Customer> getCustomers(){
        String filePath = "/src/customers.txt";
        BufferedReader bufferedReader = null;
        String line1, line2;
        List<Customer> customers = new ArrayList<>();
        try {
            String dir = System.getProperty("user.dir");
            bufferedReader = new BufferedReader(new FileReader(dir + filePath));
            //reading two lines at the same time to handle the two new lines
            //7603021234, Alhambra Aromes => line 1
            //2018-07-01 => line 2

            while ((line1 = bufferedReader.readLine()) != null && (line2 = bufferedReader.readLine()) != null) {
                if (isActiveMember(line2)){
                    customers.add(new Customer(line1.split("\\,")[1].trim(), line1.split("\\,")[0].trim(), line2));
                }
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return customers;
    }
    // kollar av om kunden  är fortfarande medlem
    public  boolean isActiveMember(String lastPayment) {
        boolean active = false;
        LocalDate currentDate  = LocalDate.now();
        int currentYear = currentDate.getYear()-1;
        String stringDate = currentYear+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth();
        currentDate = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate lastPaymentDate = LocalDate.parse(lastPayment, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (lastPaymentDate.isAfter(currentDate)){
            active = true;
        }
        return active;
    }
    public Customer searchCustomer(String userInput){
        for (Customer cs: getCustomers()){
            if (cs.getName().trim().equals(userInput.trim())|| cs.getPersonNumber().equals(userInput.trim())){
                return cs;
            }
        }
        return null;
    }
    public String writeToFile(Customer customer){
        String message = "";
        String filepath = "/src/trainers.txt";
        String dir = System.getProperty("user.dir");
        FileWriter fileWriter = null;
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd")); // dagens datum.
        try {
            fileWriter = new FileWriter(dir+filepath, true);
            fileWriter.write(customer.getPersonNumber()+"\t"+customer.getName()+"\t"+ "har varit på gymet den :" +formattedDate+"\n");
            fileWriter.flush();
            fileWriter.close();
            message = "File saved successfully!";
        } catch (IOException e) {
            message = "Unable to save the file!";
            e.printStackTrace();
        }
        return message;
    }
    /*public void printValidCustomers(){
        System.out.println("Persona number \t Full Name \t Date");
        for (Customer c: getCustomers()){
            System.out.printf("%s\t, %s\t, %s\n", c.getPersonNumber(), c.getName(), c.getDatePayment());
        }
    }

     */
    public boolean checkUserType(String user){
        return user.equals(User.TRAINER.name().toLowerCase())|| user.equals(User.RECEPTIONIST.name().toLowerCase());
    }
}


