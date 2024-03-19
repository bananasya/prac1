package example.Task3;

import java.util.Random;

public class CustomerSimulator {
    public static final int NUM_CATEGORIES = 3;
    public static final int NUM_CUSTOMERS = 1000;
    private static final Random random = new Random();

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.simulateCustomers();
    }

    public static int getRandomCategory() {
        return random.nextInt(NUM_CATEGORIES);
    }

    public static int getRandomWindow(int category) {
        int window = random.nextInt(NUM_CATEGORIES);

        if ((window == 0 || window == 1) && window != category) {
            return window;
        }

        return -1;
    }
}
