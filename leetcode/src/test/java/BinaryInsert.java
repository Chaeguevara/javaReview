import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryInsert {

    @Test
    public void testBinary(){
//        Assertions.assertEquals(0,searchInsert.solution(new int[]{1},0)); // out of range
//        Assertions.assertEquals(0,searchInsert.solution(new int[]{1,3,5,6},0)); // out of range
//        Assertions.assertEquals(2,searchInsert.solution(new int[]{1,3,5,6},5)); // equal case
//        Assertions.assertEquals(1,searchInsert.solution(new int[]{1,3,5,6},2) );
        Assertions.assertEquals(4,searchInsert.solution(new int[]{1,3,5,6},7));

    }
}
