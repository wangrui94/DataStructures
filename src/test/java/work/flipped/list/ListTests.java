package work.flipped.list;

import org.testng.annotations.Test;

public class ListTests {

    @Test
    public void listTest() {
        LinkedListWithHead<Integer> linkedList = new LinkedListWithHead<>();
        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2,666);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
    }
}
