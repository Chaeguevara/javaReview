package Easy;

public class mergeTwoLists {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        //base case
        if (list1 == null && list2 == null){
            return  null;
        }

        if (list2 == null){
            return  list1;
        }

        if (list1 == null){
            return  list2;
        }

        ListNode sentinel = new ListNode(-101,null);
        ListNode curr=sentinel,curr1=list1,curr2=list2,succ=curr.next,succ1=curr1.next,succ2=curr2.next;
        if (curr1.val <= curr2.val){
            curr.next = curr1;
            curr1.next = mergeTwoLists(succ1,curr2);
        }else{
            curr.next = curr2;
            curr2.next = mergeTwoLists(curr1,succ2);


        }

        return sentinel.next;

    }
}
