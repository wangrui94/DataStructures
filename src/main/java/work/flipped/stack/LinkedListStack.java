package work.flipped.stack;

import work.flipped.list.LinkedListWithHead;

public class LinkedListStack<E> implements Stack<E> {

    private LinkedListWithHead<E> list;

    public LinkedListStack() {
        list = new LinkedListWithHead<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: pop ");
        res.append(list);
        return res.toString();
    }
}
