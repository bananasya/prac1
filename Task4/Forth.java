package example.Task4;

import java.util.concurrent.Semaphore;

class Forth implements Runnable {
    private static final int NUM_THERAPIST_ROOMS = 2;
    private static final int NUM_MRI_ROOMS = 1;

    private static Semaphore therapistSemaphore = new Semaphore(NUM_THERAPIST_ROOMS);
    private static Semaphore mriSemaphore = new Semaphore(NUM_MRI_ROOMS);
    private static Semaphore queueSemaphore = new Semaphore(1);

    private static int queueSize = 0;
    public static int maxQueueSize = 0;

    @Override
    public void run() {
        try {
            queueSemaphore.acquire();
            queueSize++;
            maxQueueSize = Math.max(maxQueueSize, queueSize);
            System.out.println("Пациент вошел в очередь. Размер очереди: " + queueSize);
            queueSemaphore.release();

            therapistSemaphore.acquire();
            System.out.println("Осмотр терапевтом");

            mriSemaphore.acquire();
            System.out.println("Обследование на МРТ");

            Thread.sleep(500);
            mriSemaphore.release();
            therapistSemaphore.release();

            queueSemaphore.acquire();
            queueSize--;
            System.out.println("Пациент покинул очередь. Размер очереди: " + queueSize);
            queueSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

