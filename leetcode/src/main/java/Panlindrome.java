public class Panlindrome {
    public static boolean isPalindrom(int x){
        if (x<0){
            return  false;
        }
        try{
            isPalUtil(x,x);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public static int isPalUtil(int num, int dupNum) throws  Exception{
        if (num == 0){
            return dupNum;
        }else{
            dupNum = isPalUtil(num/10,dupNum);
        }

        if (num%10 == dupNum%10){
            return dupNum/10;
        }else{
            throw new Exception();
        }
    }
}
