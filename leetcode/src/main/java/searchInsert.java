public class searchInsert {

    public static int solution(int[] nums, int target){
        //BC1. Out of range
//        if (target > nums[nums.length-1] || target < nums[0]){
//            return 0;
//        }
        return searchHelper(0, nums.length-1, target,nums);
    }

    public static int searchHelper(int start, int end, int target, int[] nums){
        System.out.println("start: "+start);
        System.out.println("end: "+end);
        System.out.println("------------------------");
        if(start==end){
            if(nums[start]<target){
                return ++start;
            }
            return start;
        }
        if (nums[start]==target){
            return start;
        }
        if(nums[end]==target){
            return end;
        }

        if(nums[(start+end)/2] == target){
            System.out.println("middle equals");
            System.out.println(start+end);

            return (start+end)/2;
        }

        if(nums[(start+end)/2]>target){
            System.out.println("leftside search");

            return searchHelper(start,(start+end)/2, target, nums);
        }
        if(nums[(start+end)/2]<target){
            System.out.println("rightside search");

            return searchHelper((start+end)/2+1,end, target, nums);
        }


        return 0;
    }
}
