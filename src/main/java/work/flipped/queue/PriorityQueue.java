package work.flipped.queue;

import work.flipped.heap.MaxHeap;

/**
 * 基于最大堆实现优先级队列
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.remove();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
