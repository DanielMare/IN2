package IN2;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

public class CustomerTest {
    Customer customer = new Customer();
    @Test
    void isActiveMember(){
        assertEquals(customer.isActiveMember("2017-03-12"),false);
    }

    @Test
    void checkUserType(){
        assertEquals(customer.checkUserType("trainer"),true);
        assertEquals(customer.checkUserType("receptionist"),true);
        assertEquals(customer.checkUserType("danny"),false);

    }
    @Test
    void writeToFile(){
        Customer cs = new Customer("Greger Ganache", "1111111111","2019-03-23");
        assertEquals(customer.writeToFile(cs),"File saved successfully");
    }

}
