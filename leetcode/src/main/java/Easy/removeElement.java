package Easy;

public class removeElement {
    public static int solution(int[] nums, int val) {
//        if (nums.length ==0){
//            return  0;
//        }

//        if (nums.length==1 && nums[0] ==1){
//            nums[0] = -1;
//            return  0;
//        }


        int tailIndex = nums.length -1;
        for(int i = 0; i <= tailIndex; i++){
            if(nums[i]!=val){
                continue;
            }
            //swap
            nums[i] = nums[tailIndex];
            //turn tail into invalid num
            nums[tailIndex] = -1;
            //decrease tailIndex
            tailIndex--;
            //decrease headindex
            i--;
        }


        return ++tailIndex;
    }
}
