package Easy;

public class merge {
    public static void solution(int[] nums1, int m, int[] nums2, int n){
        System.out.println("Start of Test");
        int t=0,i=m,j=0;
        //shift order [1,2,3,0,0,0,0] -> [0,0,0,0,1,2,3]
        for (int k = m+n-1;k>=n; k--){
            nums1[k] = nums1[i-1];
//            nums1[i-1] = 0;
            i--;
//            System.out.println(nums1[k]);
        }
        i=n;
        while(i<nums1.length && j<nums2.length){
            if(nums1[i]<=nums2[j]){
                nums1[t] = nums1[i];
                i++;
            }else{
                nums1[t] = nums2[j];
                j++;
            }
            t++;
        }

        while(i< nums1.length){
            nums1[t] = nums1[i];
            i++;
            t++;
        }

        while(j< nums2.length){
            nums1[t] = nums2[j];
            j++;
            t++;
        }
        for(int k=0; k< nums1.length;k++){
            System.out.println(nums1[k]);
        }
        System.out.println("End of Test");





    }
}
