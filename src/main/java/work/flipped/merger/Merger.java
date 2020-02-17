package work.flipped.merger;

public interface Merger<E> {
    /**
     * 将a和b转换为一个元素返回
     * @param a
     * @param b
     * @return
     */
    E merge(E a, E b);
}
