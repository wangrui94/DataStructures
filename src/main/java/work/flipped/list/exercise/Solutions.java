package work.flipped.list.exercise;

public class Solutions {

    /**
     * leetcode 203
     * 删除链表中等于给定值 val 的所有节点。
     * <p>
     * 示例:
     * <p>
     * 输入: 1->2->6->3->4->5->6, val = 6
     * 输出: 1->2->3->4->5
     */
    public ListNode solution203_1(ListNode head, int val) {

        /**
         * 删除第一个节点
         */
        while ((head != null && head.val == val)) {
            ListNode del = head;
            head = head.next;
            del.next = null;
        }

        if (head == null) {
            return null;
        }

        /**
         * 删除中间节点
         */
        ListNode prev = head;
        if (prev != null) {
            while (prev.next != null) {
                if (prev.next.val == val) {
                    ListNode del = prev.next;
                    prev.next = del.next;
                    del.next = null;
                } else {
                    prev = prev.next;
                }
            }
        }
        return head;
    }

    public ListNode solution203_2(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        new Solutions().solution203_2(head, 6);
        System.out.println(head);
    }

}
