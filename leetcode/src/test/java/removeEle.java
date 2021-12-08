import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class removeEle {
    @Test
    public void test(){
        Assertions.assertEquals(5,removeElement.solution(new int[]{3,1,5,3,2,2,9,3},3));
        Assertions.assertEquals(7,removeElement.solution(new int[]{2,15,51,23,55,23,47,46,51},51));
        Assertions.assertEquals(9,removeElement.solution(new int[]{2,15,51,23,55,23,47,46,51},-1));
        Assertions.assertEquals(5,removeElement.solution(new int[]{0,1,2,2,3,0,4,2},2));
        Assertions.assertEquals(0,removeElement.solution(new int[]{2,2},2));
        Assertions.assertEquals(0,removeElement.solution(new int[]{},1));

    }
}
