package shape;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int size = 0;
        for (Point c:s.getPoints()) {
            size = size+1;
        }
        return size;
    }

    public double getAverageLength(Shape s) {
        double t = getPerimeter(s);
        int n = getNumPoints(s);
        double res = t/n;
        return res;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt: s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            largest = Math.max(largest, currDist);
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
        double large = 0.0;
        for (Point cur: s.getPoints()) {
            large = Math.max(cur.getX(), large);
        }
        // Put code here
        return large;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largest = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            //System.out.println(fr);
            Shape s = new Shape(fr);
            largest = Math.max(getPerimeter(s), largest);
        }
        return largest;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        double largest = 0.0;
        File temp = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            //System.out.println(fr);
            Shape s = new Shape(fr);
            largest = Math.max(getPerimeter(s), largest);
        }
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) == largest) {
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int num = getNumPoints(s);
        System.out.println("num = " + num);
        double avg = getAverageLength(s);
        System.out.println("avg = " + avg);
        double largest = getLargestSide(s);
        System.out.println("largest side = " + largest);
        double largestX = getLargestX(s);
        System.out.println("largest x = "+largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largest = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeters over files: "+largest);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String res = getFileWithLargestPerimeter();
        System.out.println("file name = "+res);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        //pr.testFileWithLargestPerimeter();
    }
}
