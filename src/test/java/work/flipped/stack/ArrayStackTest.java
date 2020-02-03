package work.flipped.stack;

import org.testng.annotations.Test;

public class ArrayStackTest {

    @Test
    public void test() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }

    /**
     * 判断是否是有效括号
     * @param s 带判断字符串
     * @return 如果是有效括号返回true，否则返回false
     */
    public boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[' ) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void judge() {
        assert isValid("{{}}()");
        assert isValid("{{}}(}"); // 无效括号
    }
}
