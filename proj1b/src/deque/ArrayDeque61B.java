package deque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>, Iterable<T>{
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque61B() {
        items = (T []) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public ArrayDeque61B(int capacity) {
        items = (T []) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        if(size == items.length) {
            resizing();
        }
        items[Math.floorMod(nextFirst, items.length)] = x;
        size++;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        if(size == items.length) {
            resizing();
        }
        items[Math.floorMod(nextLast, items.length)] = x;
        size++;
        nextLast++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<T>(size);
        for(int i = 0; i < size; i++) {
            list.add(get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        int index = Math.floorMod(nextFirst + 1, items.length);
        T x = items[index];
        items[index] = null;
        size--;
        nextFirst++;
        return x;
    }

    @Override
    public T removeLast() {
        if(size == 0) {
            return null;
        }
        int index = Math.floorMod(nextLast - 1, items.length);
        T x = items[index];
        items[index] = null;
        size--;
        nextLast--;
        return x;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            return null;
        }
        return items[Math.floorMod(nextFirst + 1 + index, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override//神经，给我搞了两个方法
    public boolean[] resizeing() {
        return new boolean[0];
    }

    @Override
    public boolean[] resizing() {

        //如果数组被装满了，就把数组大小变为原来的两倍
        if(size == items.length) {
            T[] new_items = (T []) new Object[size * 2];
            for(int i = 0; i < size; i++) {
                new_items[i] = items[Math.floorMod(++nextFirst, items.length)];
            }
            items = new_items;
            nextFirst = items.length - 1;
            nextLast = size;
        }

        //如果大小因子(元素个数 / 数组长度) <= 0.25，那么数组长度减半
        if((double)size / items.length <= 0.25){
            T[] new_items = (T []) new Object[items.length / 2];
            for(int i = 0; i < size; i++) {
                new_items[i] = items[Math.floorMod(++nextFirst, new_items.length)];
            }
            items = new_items;
            nextFirst = items.length - 1;
            nextLast = size;
        }
        return null;
    }

    /**Just for test.*/
    public Object[] getItems() {
        return items;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T>{
        private int index;

        public ArrayDequeIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index != size;
        }

        @Override
        public T next() {
            T returnItem = items[Math.floorMod(index + nextFirst + 1,items.length)];
            index++;
            return returnItem;
        }


    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayDeque61B otherDeque) {
            if(size != otherDeque.size) {
                return false;
            }
            int index = 0;
            for(T x : this){
                T y = get(index);
                if(!x.equals(y)){
                    return false;
                }
                index++;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("[");
        for(int i = 2; i < size + 1; i++) {
            returnSB.append(get(i - 2)).append(", ");
        }
        returnSB.append(get(size - 1).toString());
        returnSB.append("]");
        return returnSB.toString();
    }
}
