package work.flipped.bst;

import org.testng.annotations.Test;

public class BSTTest {

    @Test
    public void test() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums) {
            bst.add(num);
        }
        bst.preOrder();
    }

}
