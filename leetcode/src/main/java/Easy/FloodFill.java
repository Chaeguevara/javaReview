package Easy;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FloodFill {
    public class Point{
        public int x;
        public int y;
        Point(int x, int y){
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
        Map<Point,Integer> visited = new HashMap();

        image = floodFillHelper(image,sr,sc,newColor,image[sr][sc],m,n,visited);
        return image;
    }

    public int[][] floodFillHelper(int[][] image, int sr, int sc, int newColor,
                                   int oldColor, int m, int n, Map<Point, Integer> visited){
        if(sr<0 || sr>m-1){ // row out of range
            return image;
        }
        if(sc<0 || sc>n-1){ // column out of range
            return image;
        }
        try {

            visited.get(new Point(sc,sr));
            return image;
        }catch (NullPointerException e){
            visited.put(new Point(sc,sr),1);
        }

        if(image[sr][sc] != oldColor){
            return image;
        }

        image[sr][sc] = newColor;
        image = floodFillHelper(image,sr+1,sc,newColor,oldColor,m,n,visited);
        image = floodFillHelper(image,sr-1,sc,newColor,oldColor,m,n,visited);
        image = floodFillHelper(image,sr,sc+1,newColor,oldColor,m,n,visited);
        image = floodFillHelper(image,sr,sc-1,newColor,oldColor,m,n,visited);

        return image;


    }


}
