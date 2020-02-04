package work.flipped.stack;

import org.testng.annotations.Test;

public class LinkedListStackTest {

    @Test
    public void linkedListStackTest() {

        LinkedListStack stack = new LinkedListStack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
