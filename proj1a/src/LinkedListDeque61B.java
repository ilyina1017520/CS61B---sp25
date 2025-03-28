import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    int size;
    Node sentinel = new Node();
    public LinkedListDeque61B() {
        size = 0;
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    private class Node{
        T value;
        Node pre;
        Node next;
        private Node(){
            this.value = null;
            this.pre = null;
            this.next = null;
        }

        private Node(T value, Node pre, Node next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }


    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x,sentinel,sentinel.next);
        sentinel.next = newNode;
        newNode.next.pre = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x,sentinel.pre,sentinel);
        newNode.pre.next = newNode;
        sentinel.pre = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while(current!= sentinel){
            returnList.add(current.value);
            current = current.next;
        }
        return returnList;
        //return List.of();
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
        if(isEmpty()){
            return null;
        }
        T value = sentinel.next.value;
        sentinel.next.next.pre = sentinel;
        sentinel.next  = sentinel.next.next;
        return value;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        T value = sentinel.pre.value;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        return value;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >=size) {
            return null;
        }
        Node current = sentinel.next;
        while(index > 0){
            current = current.next;
            index--;
        }
        return current.value;
    }

    @Override
    public T getRecursive(int index) {
        if(index < 0 || index >=size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    private T getRecursive(Node current, int index) {
        if(index == 0) {
            return current.value;
        }
        return getRecursive(current.next,index - 1);
    }
}
