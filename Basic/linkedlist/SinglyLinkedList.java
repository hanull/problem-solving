package Basic.linkedlist;

public class SinglyLinkedList {

    private Node head;

    public void addFirstNode(String data) {
        Node newNode = new Node(data, head);
        head = newNode;
    }

    public Node getLastNode() {
        Node currNode = head;
        while (currNode.link != null) {
            currNode = currNode.link;
        }
        return currNode;
    }

    public void addLastNode(String data) {
        if (head == null) {
            addFirstNode(data);
            return;
        }
        Node lastNode = getLastNode();
        Node newNode = new Node(data);
        lastNode.link = newNode;
    }

    public void insertAfterNode(Node preNode, String data) {
        if (preNode == null) {
            System.err.println("선행 노드가 없어서 삽입이 불가능!");
        }
        Node newNode = new Node(data, preNode.link);
        preNode.link = newNode;
    }

    public Node getNode(String data) {
        for (Node curNode = head; curNode != null; curNode = curNode.link) {
            if (curNode.data.equals(data)) {
                return curNode;
            }
        }
        return null;
    }

    public Node getPreviousNode (Node target) {
        Node currNode = head;
        if (currNode != null) {
            while (currNode.link != null) {
                if (currNode.link == target) {
                    return currNode.link;
                }
                currNode = currNode.link;
            }
        }
        return null;
    }

    public void deleteNode(String data) {
        if (head == null) {
            System.err.println("빈 리스트여서 삭제가 불가능!");
            return;
        }
        Node targetNode = getNode(data);
        if (targetNode == null) return;

        Node previousNode = getPreviousNode(targetNode);
        if (previousNode == null && targetNode == head) {
            head = targetNode.link;
        } else {
            previousNode.link = targetNode.link;
        }
        targetNode.link = null;
    }

    public void printList() {
        Node currNode = head;
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.link;
        }
    }

}
