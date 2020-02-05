package work.flipped.set;

/**
 * 基于链表实现Set
 */

import work.flipped.list.LinkedListWithHead;

public class LinkedListSet<E> implements Set<E> {

    private LinkedListWithHead<E> list;

    public LinkedListSet() {
        list = new LinkedListWithHead<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
