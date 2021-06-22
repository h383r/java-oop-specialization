import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {

    /**
     * Given a shape, this method returns the perimeter of the shape.
     * @param s Shape
     * @return Perimeter
     */
    public double getPerimeter (Shape s) {

        double totalPerim = 0.0;
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
        
        return totalPerim;
    }

    /**
     * Returns the number of points in Shape.
     * @param s Shape
     * @return
     */
    public int getNumPoints (Shape s) {

        int numPoints = 0;

        // For each point of the shape
        for (Point p : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    /**
     * Returns a number of type double that is the calculated average of all the sides lengths in the Shape.
     * @param s Shape
     * @return
     */
    public double getAverageLength(Shape s) {
        double averageLength = getPerimeter(s) / getNumPoints(s);
        return averageLength;
    }

    /**
     * Returns a number of type double that is the longest side in the Shape.
     * @param s Shape
     * @return
     */
    public double getLargestSide(Shape s) {

        double largestSide = 0.0;

        // Return the last point added to this shape
        Point lastPoint = s.getLastPoint();

        // For each point of the shape
        for (Point currentPoint: s.getPoints()) {

            // Calculate distance from last to current point
            double currentDistance = lastPoint.distance(currentPoint);

            // Search for largest side
            if (currentDistance > largestSide) {
                largestSide = currentDistance;
                lastPoint = currentPoint;
            }
        }
        return largestSide;
    }

    /**
     * Returns a number of type double that is the largest x value over all the points in the Shape.
     * @param s Shape
     * @return
     */
    public double getLargestX(Shape s) {

        double largestX = 0.0;

        // For each point of the shape
        for (Point currentPoint: s.getPoints()) {

            // Get X coordinate of current point
            double currentX = currentPoint.getX();

            // Search for largest X
            if (currentX > largestX) {
                largestX = currentX;
            }
        }
        return largestX;
    }

    /**
     * Creates a DirectoryResource and then iterates over these files.
     * @return
     */
    public double getLargestPerimeterMultipleFiles() {

        double largestPerimeter = 0.0;
        DirectoryResource directoryResource = new DirectoryResource();

        // For each file in directory resource
        for (File currentFile: directoryResource.selectedFiles()) {

            // Converts the file into a file resource
            FileResource fileResource = new FileResource(currentFile);

            // Creates a Shape from the file resource
            Shape s = new Shape(fileResource);

            // Calculates the shapes perimeter
            double perimeter = getPerimeter(s);

            // Search for largest perimeter
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }
        return largestPerimeter;
    }

    /**
     * Creates a DirectoryResource and then iterates over these files.
     * @return
     */
    public String getFileWithLargestPerimeter() {

        File largestPerimeterFile = null;
        double largestPerimeter = 0.0;

        DirectoryResource directoryResource = new DirectoryResource();

        // For each file in directory resource
        for (File currentFile: directoryResource.selectedFiles()) {

            // Convertes the file into a file resource
            FileResource fileResource = new FileResource(currentFile);

            // Creates a Shape from the file resource
            Shape s = new Shape(fileResource);

            // Calculates the shapes perimeter
            double perimeter = getPerimeter(s);

            // Search for largest perimeter
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
                largestPerimeterFile = currentFile;
            }
        }
        return largestPerimeterFile.getName();
    }

    /**
     * Percentage of points from the Shape s with positive X and a negative Y.
     * @param s
     * @return
     */
    public double mysteryShape (Shape s) {
        double tmp = 0;

        for (Point p : s.getPoints()) {
          
          if (p.getX() > 0) {
            
            if (p.getY() < 0) {
              tmp = tmp + 1;
            }
          }
        }
        return tmp / getNumPoints(s);    
      }

    
    /**
     * This method is used to select a data file by using the FileResource class,
     * create a shape based on the points from that data file,
     * and then calculate the perimeter of the shape and output its value. 
     */
    public void testPerimeter () {

        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        // Call methods
        double length = getPerimeter(s);
        int points = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);

        // Print test
        System.out.println("perimeter      = " + length);
        System.out.println("points         = " + points);
        System.out.println("average length = " + averageLength);
        System.out.println("largest side   = " + largestSide);
        System.out.println("largest X      = " + largestX);
    }

    /**
     * Test Case
     */
    public void testPerimeterMultipleFiles() {

        // Call method
        double largestPerimeter = getLargestPerimeterMultipleFiles();

        // Print test
        System.out.println("largest perimeter in selected files = " + largestPerimeter);
    }

    /**
     * Test Case
     */
    public void testFileWithLargestPerimeter() {

        // Call method
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();

        // Print test
        System.out.println("file with largest perimeter = " + fileWithLargestPerimeter);
    }

    /**
     * This method creates a triangle that you can use to test your other methods
     */
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double perimeter = getPerimeter(triangle);
        System.out.println("perimeter = "+perimeter);
    }

    /**
     * This method prints names of all files in a chosen folder
     */
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.triangle();
        //pr.printFileNames();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}