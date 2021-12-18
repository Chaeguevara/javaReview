

public class LinkedListCycle {

     public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    public boolean hasCycle(ListNode head) {
        if(head ==null || head.next == null){
            return false;
        }
        ListNode faster,slower;
        faster = head;
        slower = head;
        while(faster.next !=null && faster.next.next!=null && slower.next !=null){
            faster = faster.next.next;
            slower = slower.next;
            if(faster == slower){
                System.out.println(faster.val);
                System.out.println(slower.val);
                return true;
            }
        }

        return false;
    }
}
