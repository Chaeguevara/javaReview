import Easy.merge;
import org.junit.jupiter.api.Test;

public class testMerge {

    @Test
    public void solution(){
        merge.solution(new int[]{1,2,3,6,7,0,0,0},5,new int[]{2,5,6},3);
        merge.solution(new int[]{3,5,10,19,27,0,0,0,0,0},5,new int[]{7,8,12,15,21},5);
    }
}
