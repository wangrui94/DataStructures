## 数组基础

数组的概念很简单，就是把数据码成一排进行存放，如下图所示，该数组有8个空间可以存放元素，且只能存放同种元素，比如8个`int`型或者`String`类型,或者自定义类型。
![](https://blogimage.flipped.work/datastructures/array/array1.png)
可以给数组取一个名字`arr`,但更多情况是取一个有语义的名字如`scores`，数组有一个重要的概念，即索引，因为数组内存连续，就可以给每个元素做一个编号，编号从0开始，1，2，3，···，如下图所示
![](https://blogimage.flipped.work/datastructures/array/array2.png)

有了索引，就可以快速的直接访问数组第`i`元素，比如`scores[2]`，对应该数组第`3`元素。Java中声明数组很简单，如下所示：

```java
int[] arr = new int[8]
```

这样就可以对数组进行操作：

```java
for (int i = 0; i <= arr.length; i++) {
    arr[i] = i;
}
```

上面的数组可以容纳8个元素，但是现在只有3个元素，如下图所示，后续如何添加元素？如何删除元素？添加的元素超过了8个怎么办？Java提供的数组并没有这些功能。因此，制作属于我们自己的数组来提供这些功能。

![](https://blogimage.flipped.work/datastructures/array/array4.png)

### 制作自己的数组

我们自己的数组基于Java数组封装，在Java数组的基础上提供增、删、改、查，如下所示：

![](https://blogimage.flipped.work/datastructures/array/array3.png)

我们的类叫`Array`，内部封装了Java数组。由于数组本身是静态的，在创建时就必须指定他的大小，这个大小叫做容量（`capacity`），表示最多可以容纳多少元素，这和数组中实际元素个数是没有关系的，实际元素个数称为`size`。初始时数组中一个元素都没有，此时`size`应该等于`0`，这个`0`也相当于指向了第一个没有存储元素的索引，还有一些基本功能：

```java
package work.flipped.array;

public class Array {

    private int[] data;
    private int size;

    /**
     *
     * @param capacity 传入数组的容量
     */
    public Array(int capacity) {
        data = (int[]) new Object[capacity];
        size = 0;
    }

    // 默认构造函数，数组默认容量为10
    public Array() {
        this(10);
    }

    // 获取数组中元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size==0;
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

```

#### 向数组添加元素

向数组添加元素，最简单的是向数组末尾添加元素，因为`size`是指向数组中第一个没有元素的位置，向数组末尾添加元素，就是向`size`位置添加元素，并且`size`自增。

![](https://blogimage.flipped.work/datastructures/array/array5.png)

```java
public void addLast(int e) {
    if (size == data.length) {
        throw new IllegalArgumentException("AddLast failed. Array is full");
    }
    data[size] = e;
    size++;      
}
```

添加元素的时候，需要判断能否容纳更多元素，如果不能则需要抛出异常。

另外需要向指定位置添加一个元素。例如数组中现在有4个元素，需要将一个新元素插入索引为1的位置，需要吧当前索引为1及其后面的元素向后移动一个位置，且是从最后一个元素开始往后移动，否则会覆盖旧元素，同样`size`自增。

![](https://blogimage.flipped.work/datastructures/array/array6.png)

```java
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

    // 向后移动元素
    for (int i = size - 1; i >= index ; i--) {
        data[i+1] = data[i];
    }

    // 插入元素
    data[index] = e;
    size++;
}
```

同时，可以将向数组元素末尾添加元素，复用为`add(size, e)`。

```java
public void addLast(int e) {    
    add(size,e);
}
```

同样，可以构造向数组头部添加一个元素：

```java
public void addLast(int e) {    
    add(0,e);
}
```

#### 获取元素&修改元素

```java
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
```

#### 包含、搜索

判断是否包含某元素，需要从头遍历一次数组。

```java
/**
 * 查找数组中是否有元素e
 * @param e 带查找元素
 * @return 如果包含元素e，则返回true，否则，返回false
 */
public boolean contains(int e) {
    for (int i = 0; i < size; i++) {
        if (data[i].equals(e)) {
            return true;
        }
    }
    return false;
}
```

可能不仅仅是想查看是否包含某元素，而且想知道如果包含，还想知道对应的索引是多少。逻辑和查询包含某元素类似。

```java
/**
* 查找数组中元素e所在的索引
* @param e 带查找元素
* @return 如果存在则返回索引，否则返回-1
*/
public int find(int e) {
	for (int i = 0; i < size; i++) {
		if (data[i] == e) {
		    return i;
        }
    }
    return -1;
}
```

#### 删除指定位置元素

如果指定了索引，像删除指定位置的元素，其过程和添加元素相反，从删除位置后一个元素开始，向前移动一个位置。如下图，现在想删除索引为1的元素，只需要将索引2机器后面的元素向前移动，size自减。

![](https://blogimage.flipped.work/datastructures/array/array7.png)

```java
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
```

因为，在操作数据时，会检查`size`合法性，即使`size`现在指向的元素为100，也不影响。与添加元素类似，也可以创建一些快捷方法，例如删除数组第一个元素，删除最后一个元素

```java
/**
* 删除第一个元素
* @return 返回删除元素
*/
public int removeFirst() {
	return remove(0);
}
/**
* 删除最后一个元素
* @return 删除的元素
*/
public int removeLast() {
	return remove(size-1);
}
```

#### 删除元素

查看数组中是否存在某个元素，如果存在则将其删除。先查找返回其索引，然后删除。

```java
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
```

#### 使用泛型

现在这个数组的问题是只能容纳`int`型的数据，数组作为一个容器，应该能够容纳任意类型的数据，Java语言提供了泛型，解决这个问题。

```java
package work.flipped.array;

public class Array<E> {

    private E[] data;
    private int size;

    /**
     *
     * @param capacity 传入数组的容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参构造函数，数组默认容量为10
    public Array() {
        this(10);
    }

    // 获取数组中元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
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
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向所有元素后添加一个新元素
     * @param e 待添加元素
     */
    public void addLast(E e) {       
        add(size,e);
    }

    /**
     * 在第index个位置插入一个新元素e
     * @param index 待插入位置
     * @param e 待插入元素
     */
    public void add(int index, E e) {
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
    public E get(int index) {
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
    public void set(int index, E e) {
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
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取数组最后一个元素
     * @return 数组最后一个元素
     */
    public E getLast() {
        return get(size-1);
    }

    /**
     * 获取数组第一个元素
     * @return 数组第一个元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 查找数组中元素e所在的索引
     * @param e 带查找元素
     * @return 如果存在则返回索引，否则返回-1
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
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
    public E remove(int index) {
        if (index < 0 || index >=  size) {
            throw new IllegalArgumentException("Remove Failed. Index is illegal");
        }
        E ret = data[index];
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
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size-1);
    }

    /**
     * 从数组中删除元素e
     * @param e 待删除的元素
     */
    public void removeElement(E e) {
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
```

#### 动态数组

很多时候不能预估往数组添加多少元素，如果容量太大，就浪费了很多空间，太小容量就不够用。这就需要容量可伸缩，即动态数组。

##### 添加操作

![](https://blogimage.flipped.work/datastructures/array/array8.png)

那么对插入、删除操作就要进行动态的操作。对于插入元素，当空间不足时，先请求一个两倍空间的新数组，让后将元素复制到新数组中。

```java
/**
* 在第index个位置插入一个新元素e
* @param index 待插入位置
* @param e 待插入元素
*/
public void add(int index, E e) {

	if (index < 0 || index > size) {
		throw new IllegalArgumentException("Add failed. index>=0 and index <= siz");
	}
	if (size == data.length) {            
		resize(2 * data.length);
	}
	for (int i = size - 1; i >= index ; i--) {
		data[i+1] = data[i];
	}
	data[index] = e;
	size++;
}
private void resize(int newCapacity) {
    E[] newData = (E[]) new Object[newCapacity];
    for (int i = 0; i < size; i++) {
        newData[i] = data[i];
    }
    data = newData;
}
```

##### 删除操作

为了节省空间，当删除到一定程度时，缩小它的空间，这里是当元素个数是容量的四分之一时，将容量缩小为原来的一半。

```java
/**
* 删除index索引位置的元素
* @param index 索引位置
* @return 删除的元素
*/
public E remove(int index) {
	if (index < 0 || index >=  size) {
        throw new IllegalArgumentException("Remove Failed. Index is illegal");
    }
    E ret = data[index];
    for (int i = index+1; i < size; i++) {
        data[i-1] = data[i];
    }
    size--;
    data[size] = null;
    if (size == (data.length / 4 ) && (data.length / 2 != 0)) {
        resize(data.length / 2);
    }
    return ret;
}
```

关于数组的全部知识点就结束了。