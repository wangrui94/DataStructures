package work.flipped.array;

public class Array {

    private int data[];
    private int size;

    /**
     *
     * @param capacity 传入数组的容量
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    // 无参构造函数，数组默认容量为10
    public Array() {
        this(10);
    }

    // 获取数组中元素个数
    int getSize() {
        return size;
    }

    // 获取数组容量
    int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size==0;
    }

    /**
     * 在所有元素前添加一个元素
     * @param e 待添加元素
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e 待添加元素
     */
    public void addLast(int e) {

        /*
        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }

        data[size] = e;
        size++;

         */
        add(size,e);
    }

    /**
     * 在第index个位置插入一个新元素e
     * @param index 待插入位置
     * @param e 待插入元素
     */
    public void add(int index, int e) {

        if (size == data.length) {
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. index>=0 and index <= siz");
        }

        for (int i = size - 1; i >= index ; i--) {
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素
     * @param index 索引
     * @return index索引位置的元素
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     * @param index 索引
     * @param e 新值
     */
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素e
     * @param e 带查找元素
     * @return 如果包含元素e，则返回true，否则，返回false
     */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i]==e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引
     * @param e 带查找元素
     * @return 如果存在则返回索引，否则返回-1
     */
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i]==e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除index索引位置的元素
     * @param index 索引位置
     * @return 删除的元素
     */
    public int remove(int index) {
        if (index < 0 || index >=  size) {
            throw new IllegalArgumentException("Remove Failed. Index is illegal");
        }
        int ret = data[index];
        for (int i = index+1; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        return ret;
    }

    /**
     * 删除第一个元素
     * @return 返回删除的第一个元素
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public int removeLast() {
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * @param e 待删除的元素
     */
    public void removeElement(int e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
