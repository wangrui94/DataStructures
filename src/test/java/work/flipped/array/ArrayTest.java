package work.flipped.array;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ArrayTest {

    private Array<Integer> arr;

    @BeforeTest
    public void before() {
        arr = new Array<Integer>();
    }

    @Test
    public void testAdd() {
        arr.add(1,100);
    }

    @Test
    public void testAddFirst() {

    }

    @Test
    public void addLast() {
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        for (int i = 0; i < 6; i++) {
            arr.remove(0);
        }
        testToString();
    }

    @Test
    public void testToString() {
        System.out.println(arr);
    }

}