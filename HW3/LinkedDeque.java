/**
 * Your implementation of a LinkedDeque.
 *
 * @author Shengjun Xi
 * @version 1.0
 * @userid sxi9
 * @GTID 903518643
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class LinkedDeque<T> {

    // Do not add new instance variables or modify existing ones.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the front of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't add null values atFirst.");
        }
        LinkedNode<T> temp = new LinkedNode<>(data);
        if (size == 0) {
            tail = temp;
        } else {
            temp.setNext(head);
            head.setPrevious(temp);
        }
        head = temp;
        size++;
    }

    /**
     * Adds the element to the back of the deque.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't add null values atLast.");
        }
        LinkedNode<T> temp = new LinkedNode<>(data);
        if (size == 0) {
            head = temp;
        } else {
            temp.setPrevious(tail);
            tail.setNext(temp);
        }
        tail = temp;
        size++;
    }

    /**
     * Removes and returns the first element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Deque is empty, can't removeFirst.");
        }
        LinkedNode<T> curr = head;
        T temp = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = curr.getNext();
            head.setPrevious(null);
        }
        size--;
        return temp;
    }

    /**
     * Removes and returns the last element of the deque.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Deque is empty, can't removeLast.");
        }
        LinkedNode<T> curr = tail;
        T temp = tail.getData();
        if (size == 1) { // There's only one element in the List
            tail = null;
            head = null;
        } else {
            tail = curr.getPrevious();
            tail.setNext(null);
        }
        size--;
        return temp;
    }

    /**
     * Returns the first data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Deque is empty, can't getFirst.");
        }
        return head.getData();
    }

    /**
     * Returns the last data of the deque without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T getLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Deque is empty, can't getLast.");
        }
        return tail.getData();
    }

    /**
     * Returns the head node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the deque.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the deque
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
