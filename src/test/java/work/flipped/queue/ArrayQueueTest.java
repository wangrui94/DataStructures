package work.flipped.queue;

import org.testng.annotations.Test;

public class ArrayQueueTest {

    @Test
    public void test() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 0) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
