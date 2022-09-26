/**
 * Your implementation of a non-circular DoublyLinkedList with a tail pointer.
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
public class DoublyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private DoublyLinkedListNode<T> head;
    private DoublyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index. Don't forget to consider whether
     * traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if ((index < 0) || (index > size)) {
            throw new java.lang.IndexOutOfBoundsException("Can't at data at invalid index.");
        }
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't add null to LinkedList.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
            DoublyLinkedListNode<T> curr;
            if (index < (size / 2)) {
                curr = head;
                for (int i = 1; i < index; i++) {
                    curr = curr.getNext(); //curr would be the node index-1
                }
            } else {
                curr = tail;
                for (int i = size; i > index; i--) {
                    curr = curr.getPrevious(); //curr would be the node index-1
                }
            }
            DoublyLinkedListNode<T> preNode = curr.getNext(); // previous node at index;
            preNode.setPrevious(newNode);
            newNode.setNext(preNode);

            curr.setNext(newNode);
            newNode.setPrevious(curr);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't add null to front.");
        }
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, null, head);
        if (isEmpty()) {
            head = newNode;
            tail = head;
        } else {
            DoublyLinkedListNode<T> temp = head;
            temp.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't add null to back.");
        }
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(data, tail, null);
        if (isEmpty()) {
            tail = newNode;
            head = tail;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the element at the specified index. Don't forget to
     * consider whether traversing the list from the head or tail is more
     * efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if ((index < 0) || (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException("Can't remove element at invalid index");
        }

        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            DoublyLinkedListNode<T> curr;
            if (index < (size / 2)) {
                curr = head;
                for (int i = 0; i < index; i++) {
                    curr = curr.getNext(); //curr would be the node index
                }
            } else {
                curr = tail;
                for (int i = size - 1; i > index; i--) {
                    curr = curr.getPrevious(); //curr would be the node index
                }
            }
            DoublyLinkedListNode<T> preNode = curr.getPrevious();
            DoublyLinkedListNode<T> nextNode = curr.getNext();
            preNode.setNext(nextNode);
            nextNode.setPrevious(preNode);
            size--;
            return curr.getData();
        }
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Can't perform removeFromFront on an Empty list.");
        }
        T data = head.getData();
        if (size > 1) {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        } else {
            clear();
        }
        return data;
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Can't perform removeFromBack on an empty list.");
        }
        T data = tail.getData();
        if (size == 1) {
            clear();
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }
        return data;
    }

    /**
     * Returns the element at the specified index. Don't forget to consider
     * whether traversing the list from the head or tail is more efficient!
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new java.lang.IndexOutOfBoundsException("Can't get data at invalid index");
        }
        DoublyLinkedListNode<T> curr;
        if (index < (size / 2)) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext(); //curr would be the node index
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.getPrevious(); //curr would be the node index
            }
        }
        return curr.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(1) if data is in the tail and O(n) for all other cases.
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Can't remove null at removeLastOccurrence.");
        }
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Can't remove elements in an empty list.");
        } else {
            if ((tail.getData()).equals(data)) {
                return removeFromBack();
            } else {
                DoublyLinkedListNode<T> curr = tail;
                int i = size - 1;
                while (i > 0) {
                    i--;
                    curr = curr.getPrevious();
                    if ((curr.getData()).equals(data)) {
                        //return removeAtIndex(i); //change it to O(1)
                        DoublyLinkedListNode<T> preNode = curr.getPrevious();
                        DoublyLinkedListNode<T> nextNode = curr.getNext();
                        if (i == 0) {
                            head = nextNode;
                        } else {
                            preNode.setNext(nextNode);
                        }
                        nextNode.setPrevious(preNode);
                        size--;
                        return curr.getData();
                    }
                }
                throw new java.util.NoSuchElementException("Can't remove element not in the list.");
            }
        }

    }

    /**
     * Returns an array representation of the linked list. If the list is
     * size 0, return an empty array.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length size holding all of the objects in the
     * list in the same order
     */
    public Object[] toArray() {
        if (isEmpty()) {
            return new Object[0];
        } else {
            Object[] arrayReturn = new Object[size];
            DoublyLinkedListNode<T> curr = head;
            for (int i = 0; i < size; i++) {
                arrayReturn[i] = curr.getData();
                curr = curr.getNext();
            }
            return arrayReturn;
        }

    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public DoublyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public DoublyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
