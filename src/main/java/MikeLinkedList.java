public class MikeLinkedList<T> implements LinkedList<T> {
    private Node head;
    private int size;

    private class Node {
        private T value;
        private Node next;
    }

    public MikeLinkedList(){
        head = null;
        size = 0;
    }

    public boolean isEmpty(){
        return(head == null);
    }

    public int size(){
        return size;
    };

    public T get(int index){
        if (isEmpty()) {
            throw new RuntimeException("Empty");
        }
        Node curr = head;
        int currIndex = 0;
        while(currIndex != index ){
            curr = curr.next;
            currIndex++;
        }
        if(curr == null){
            throw new RuntimeException("current node is null");
        }
        return curr.value;
    }

    public void insert(int location, T value)
    {
        if(isEmpty()) return;

        if(location == 0){
            append(value);
            return;
        }

        Node previous = null;
        Node curr = head;

        while(curr != null && curr.value != get(location))
        {
            previous = curr;
            curr = curr.next;
        }

        if(curr != null) {
            Node newNode = new Node();
            newNode.value = value;
            newNode.next = curr;
            previous.next = newNode;
            size++;
        }
    }

    public void append(T value){
        Node newNode = new Node();
        newNode.value = value;
        if (isEmpty()){
            head = newNode;
            head.next = null;
            size++;
        } else {
            Node nodeToCheck = head;
            while (nodeToCheck.next != null){
                nodeToCheck = nodeToCheck.next;
            }
            nodeToCheck.next = newNode;
            size++;
        }
    }

    public T remove(int index){
        if(index == 0){
            T valueToReturn = head.value;
            head = head.next;
            return valueToReturn;
        }

        Node curr = head;
        Node previous = null;
        while(curr.value != get(index) && curr != null){
            previous = curr;
            curr = curr.next;
        }
        if(curr == null) {
            throw new RuntimeException("Current node is null, can't delete");
        }

        previous.next = curr.next;
        size--;
        return curr.value;
    }

}