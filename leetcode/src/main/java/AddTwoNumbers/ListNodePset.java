package AddTwoNumbers;

import java.util.List;

public class ListNodePset {
    public static void main(String[] args) {
        System.out.println("Hi");
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1cur = l1;
        ListNode l2cur = l2;
        ListNode listNode = new ListNode(0,null);
        ListNode curNode = listNode;
        ListNode nextNode = new ListNode(0,null);
        while (l1cur.next!=null ||l2cur.next!=null){
            int sub = 0;
            if (l1cur != null){
                sub += l1cur.val;
            }
            if(l2cur != null){
                sub += l2cur.val;
            }
            curNode.val += sub % 10;
            nextNode.val = sub / 10;
            curNode.next = nextNode;


        }


        return listNode;
    }
}
