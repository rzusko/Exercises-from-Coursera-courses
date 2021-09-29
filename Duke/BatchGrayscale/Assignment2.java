
/**
 * Trieda {@code Assignment2} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import edu.duke.*;
import java.io.*;

public class Assignment2 {
    public ImageResource makeInverted(ImageResource inFile) {
        ImageResource outFile = new ImageResource(inFile.getWidth(), inFile.getHeight());
        
        for (Pixel p : outFile.pixels()) {
            Pixel inPixel = inFile.getPixel(p.getX(), p.getY());
            
            p.setRed(255 - inPixel.getRed());
            p.setGreen(255 - inPixel.getGreen());
            p.setBlue(255 - inPixel.getBlue());          
        }
        
        return outFile;
    }
    
    public void saveInverted() {
        DirectoryResource dr = new DirectoryResource();
        
        for(File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            
            String fname = ir.getFileName();
            String fPath = f.getParentFile().getAbsolutePath();
            String newName = fPath + "/inverted-" + fname;
            
            ir = makeInverted(ir);
            ir.setFileName(newName);
            ir.save();
            ir.draw();
        }
    }
 
}
