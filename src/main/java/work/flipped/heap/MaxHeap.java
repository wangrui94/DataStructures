package work.flipped.heap;

import work.flipped.array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * @return 堆中元素个数
     */
    public int size() {
        return data.getSize();
    }

    /**
     * @return 堆空返回true，非空返回false
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * @param index
     * @return 该索引的父亲节点索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not hace parent");
        }
        return (index - 1) / 2;
    }

    /**
     * @param index
     * @return 该索引的左孩子节点的索引
     */
    private int left(int index) {
        return index * 2 + 1;
    }

    /**
     * @param index
     * @return 该索引的右孩子节点的索引
     */
    private int right(int index) {
        return index * 2 + 2;
    }

    /**
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1); // 上浮
    }

    /**
     * 上浮
     *
     * @param i
     */
    private void siftUp(int i) {
        while (i > 0 && data.get(i).compareTo(data.get(i)) < 0) {
            data.swap(i, (parent(i)));
            i = parent(i);
        }
    }

    /**
     * 查看最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    /**
     * 取出最大元素
     */
    public E remove() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 下沉
     *
     * @param i
     */
    private void siftDown(int i) {
        while (left(i) < data.getSize()) {
            int j = left(i);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = right(i);
            }
            if (data.get(i).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(i, j);
            i = j;
        }
    }

}
