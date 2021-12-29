package Easy;

import java.util.HashSet;
import java.util.Set;

public class FloodFill {
    public class Coordinate{
        public int x;
        public int y;

        Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    public int[][] solution(int[][] image, int sr, int sc, int newColor) {
        int m = image.length;
        int n = image[0].length;
        if(n==1 && m==1){
            image[0][0]=newColor;
            return image;
        }
        Set<Coordinate> mVisitied = new HashSet<>();
        mVisitied.add(new Coordinate(sr,sc));
        image = floodFillHelper(image,sr,sc,newColor,mVisitied,image[sr][sc],m,n);
        return image;
    }

    public static int[][] floodFillHelper(int[][] image, int sr, int sc, int newColor,
                                          Set<Coordinate> visitied, int oldColor,int m, int n){
        if(sr<0 || sr>m-1){
            return image;
        }
        if(sc<0 || sc>n-1){
            return image;
        }
        return null;


    }


}
