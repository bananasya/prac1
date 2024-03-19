package example.Task3;

import java.util.concurrent.atomic.AtomicInteger;

class CustomerService {
    private AtomicInteger[] customerCounts;
    private AtomicInteger[] abandonedCounts;

    public CustomerService() {
        customerCounts = new AtomicInteger[CustomerSimulator.NUM_CATEGORIES];
        abandonedCounts = new AtomicInteger[CustomerSimulator.NUM_CATEGORIES];

        for (int i = 0; i < CustomerSimulator.NUM_CATEGORIES; i++) {
            customerCounts[i] = new AtomicInteger(0);
            abandonedCounts[i] = new AtomicInteger(0);
        }
    }

    public void simulateCustomers() {
        for (int i = 0; i < CustomerSimulator.NUM_CUSTOMERS; i++) {
            int category = CustomerSimulator.getRandomCategory();
            int window = CustomerSimulator.getRandomWindow(category);

            if (window != -1) {
                customerCounts[category].incrementAndGet();
            } else {
                abandonedCounts[category].incrementAndGet();
            }
        }

        for (int i = 0; i < CustomerSimulator.NUM_CATEGORIES; i++) {
            int totalCustomers = customerCounts[i].get() + abandonedCounts[i].get();
            double abandonmentRate = (double) abandonedCounts[i].get() / totalCustomers * 100;
            System.out.printf("Category %d: Abandonment Rate: %.2f%%\n", i+1, abandonmentRate);
        }
    }
}
