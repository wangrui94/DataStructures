package work.flipped.queue;

import org.testng.annotations.Test;

public class LinkedListQueueTest {

    @Test
    public void testLinkedListQueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
