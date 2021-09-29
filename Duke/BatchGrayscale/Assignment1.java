
/**
 * Trieda {@code Assignment1} slúži «doplňte opis»…
 *
 * @author        {meno autora}
 * @version        {verzia alebo dátum}
 */
import edu.duke.*;
import java.io.*;

public class Assignment1 {
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
                        
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
                      
        return outImage;
    }
    
    public void saveGray() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
                        
            String fname = ir.getFileName();
            String fPath = f.getParentFile().getAbsolutePath();
            String newName = fPath + "/gray-" + fname;
                                                   
            ir = makeGray(ir);
            ir.setFileName(newName);
            ir.save();
            ir.draw();
        }
    }
}
