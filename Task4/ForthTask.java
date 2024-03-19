package example.Task4;


public class ForthTask {
    private static final int NUM_PATIENTS = 100;

    public static void main(String[] args) {
        Thread[] patients = new Thread[NUM_PATIENTS];

        for (int i = 0; i < NUM_PATIENTS; i++) {
            patients[i] = new Thread(new Forth());
            patients[i].start();
        }

        for (int i = 0; i < NUM_PATIENTS; i++) {
            try {
                patients[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Максимальная длина очереди: " + Forth.maxQueueSize);
    }
}

