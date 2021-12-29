package Easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        System.out.println("Hi");
    }

    public static void dupTest() {
        Map<Integer,Integer> test = new HashMap<>();
        test.put(1,1);
        System.out.println(test.get(1));
        test.put(1,2);
        System.out.println(test.get(1));
        test.put(1,3);
        System.out.println(test.get(1));
        test.get(1);

    }

    public static int[] addTwo(int[] nums, int target) {
        int[] result = {0,0};
        Map <Integer,int[]> numIndex = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int[] values = {-1,-1};
            if(numIndex.containsKey(nums[i])){
                values = numIndex.get(nums[i]);
                values[1] = i;
                numIndex.put(nums[i],values);
                continue;
            }
            values[0] = i;
            numIndex.put(nums[i],values);
        }
        for (Integer value : numIndex.keySet()){
            result[0] = numIndex.get(value)[0];
            if(numIndex.containsKey(target-value)) {
                result[1] = numIndex.get(target-value)[0];
                if (result[0]==result[1]){
                    result[1] = numIndex.get(target-value)[1];
                }
                break;

            }

        }


        return result;
    }
}
