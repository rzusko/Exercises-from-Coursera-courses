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
        // počiatočná hodnota numPoints
        int numPoints = 0;
        // 
        for (Point currPt : s.getPoints()) {
            numPoints = numPoints + 1;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // overall length
        double overallLength = getPerimeter(s);
        // number of sides
        int sidesNum = getNumPoints(s);
        // average length
        double averageLength = overallLength / sidesNum;
        
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // počiatočná hodnota largestSideLegth 0,0
        double largestSideLength = 0.0;
        // začiatok s posledným bodom
        Point prevPt = s.getLastPoint();
        //výber najdlhšej zo všetkých strán
        for (Point currPt : s.getPoints()) {
            //zistí aktuálnu vzdialenosť medzi predchádzajúcim a aktuálnym bodom
            double currDist = prevPt.distance(currPt);
            //porovná aktuálnu vzdialenosť s najväčšou
            if (currDist > largestSideLength) {
                largestSideLength = currDist;
            }
            //aktualizuje predchádzajúci bod prevPt na aktuálny currPt
            prevPt = currPt;
        }
        return largestSideLength;
    }

    public double getLargestX(Shape s) {
        // počiatočná hodnota largestX 0,0
        double XX = 0.0;
        //Prechádza všetkými bodmi a porovnáva x súradnícu
        for (Point currPt : s.getPoints()) {
            //zistí súradnicu X aktuálneho bodu
            double x = currPt.getX();
            //porovná súradnicu x aktuálneho bodu s najväčšou súradnicou bodu X
            if (x > XX) {
                XX = x;
            }
        }
        
        return XX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Vytvorí DirectoryResource, aby bolo možné vybrať viacero súborov
        DirectoryResource dr = new DirectoryResource();
        // počiatočná hodnota najväčšieho priemeru 0,0
        double largestPerim = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerim) {
                largestPerim = length;
            }
        }
        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        // Vytvorí DirectoryResource, aby bolo možné vybrať viacero súborov
        DirectoryResource dr = new DirectoryResource();
        File temp = null;    // replace this code
        double largestPerim = 0.0;
        
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length > largestPerim) {
                largestPerim = length;
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
        int count = getNumPoints(s);
        System.out.println("Počet bodov = " +count);
        double averageLength = getAverageLength(s);
        System.out.println("Priemerná dĺžka strany = " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("Najväčšia dĺžka strany = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Najväčšia hodnota súradnice X je: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Najväčší obvod je: " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println(fileName);
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
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
