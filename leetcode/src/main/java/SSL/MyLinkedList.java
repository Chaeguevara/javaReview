package SSL;


public class MyLinkedList {
    ListNode head,tail; //sentinel

    public MyLinkedList(){
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next=tail;
    }

    public int get(int index) {
        if (index<0){
            return -1;
        }
        int counter = -1;
        ListNode curr = head;
        while(curr.next != null){
            if(counter == index){
                return curr.val;
            }
            counter += 1;
            curr = curr.next;
        }

        return -1;


    }

    public void addAtHead(int val) {
        ListNode newHead = new ListNode(val);
        ListNode prev = head, succ = head.next;
        newHead.next = succ;
        prev.next = newHead;


    }

    public void addAtTail(int val) {
        ListNode newTail = new ListNode(val);
        ListNode curr = head;
        while(curr.next != null){
            if (curr.next.next == null){
                newTail.next = tail;
                curr.next = newTail;
                break;
            }
            curr = curr.next;
        }


    }

    public void addAtIndex(int index, int val) {
        ListNode prev = head, curr = head.next, succ = head.next.next;
        if (index<0) index =0;
        ListNode toAdd = new ListNode(val);
        int counter = 0;
        while(curr.next!=null){
            if(counter == index){
                toAdd.next=succ;
                prev.next = toAdd;
                break;
            }
            ++counter;
        }
    }

    public void deleteAtIndex(int index) {
        ListNode prev = head, curr = head.next, succ = head.next.next;
        if(index<0) index=0;
        int count =0;
        while(curr.next!=null){

        }


    }

    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x){ val = x;}
    }
}
