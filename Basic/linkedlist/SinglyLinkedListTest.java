package Basic.linkedlist;

public class SinglyLinkedListTest {

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirstNode("홍길동");
        list.addLastNode("박지성");

        Node preNode = list.getNode("박지성");
        list.insertAfterNode(preNode, "손흥민");
        list.printList();
    }
}
