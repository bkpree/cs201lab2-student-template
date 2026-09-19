import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> { 
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        if (size <= 1) {
            return;
        }

        // Store all existing nodes
        ArrayList<Node<E>> nodes = new ArrayList<>();

        Node<E> current = head;

        while (current != null) {
            nodes.add(current);
            current = current.getNext();
        }

        // Make a copy and sort it by element value
        ArrayList<Node<E>> sorted = new ArrayList<>(nodes);

        sorted.sort((a, b) ->
            ((Comparable<E>) a.getElement()).compareTo(b.getElement())
        );

        // Swap smallest with biggest,
        // second smallest with second biggest, etc.
        int left = 0;
        int right = sorted.size() - 1;

        while (left < right) {

            Node<E> smallest = sorted.get(left);
            Node<E> biggest = sorted.get(right);

            int smallestIndex = nodes.indexOf(smallest);
            int biggestIndex = nodes.indexOf(biggest);

            // Swap their positions in the nodes ArrayList
            nodes.set(smallestIndex, biggest);
            nodes.set(biggestIndex, smallest);

            left++;
            right--;
        }

        // Reconnect the nodes
        head = nodes.get(0);

        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).setNext(nodes.get(i + 1));
        }

        // Set tail
        tail = nodes.get(nodes.size() - 1);
        tail.setNext(null);

    }
   
}

