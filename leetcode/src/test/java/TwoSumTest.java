import Easy.TwoSum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoSumTest {

    @Test
    public void testAdd(){
        Assertions.assertEquals(2,1+1);
        Assertions.assertArrayEquals(new int[]{0, 1}, TwoSum.addTwo(new int[]{2, 7, 11, 15},9));
        Assertions.assertArrayEquals(new int[]{0, 1},TwoSum.addTwo(new int[]{3, 3},6));
        Assertions.assertArrayEquals(new int[]{0, 3},TwoSum.addTwo(new int[]{3, 12,5,8},11));

        TwoSum.dupTest();
    }
}
