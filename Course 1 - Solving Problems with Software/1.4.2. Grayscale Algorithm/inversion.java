import java.io.*;
import edu.duke.*;

public class inversion {

    /**
     * Create new images that are photographic negatives (or inverted images)
     * @param imageInput
     * @return
     */
    public ImageResource inversionAlgorithm(ImageResource imageInput) {

        ImageResource imageOutput = new ImageResource(imageInput.getWidth(), imageInput.getHeight());

        //For each pixel in image
        for (Pixel currentPixel : imageOutput.pixels()) {

            //Look at the corresponding pixel in image
            Pixel inputPixel = imageInput.getPixel(currentPixel.getX(), currentPixel.getY());

            //Inverted component = 255 - component value (0-255 range)
            int invertedRed = 255 - (inputPixel.getRed());
            currentPixel.setRed(invertedRed);

            int invertedBlue = 255 - (inputPixel.getBlue());
            currentPixel.setBlue(invertedBlue);

            int invertedGreen = 255 - (inputPixel.getGreen());
            currentPixel.setGreen(invertedGreen);
        }
        return imageOutput;    
    }

    /**
     * Select several files and display the inverted images.
     */
    public void inversionSelected() {
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {
            ImageResource imageInput = new ImageResource(currentFile);
            ImageResource imageInversion = inversionAlgorithm(imageInput);
            imageInversion.draw();
        }
    }

    /**
     * Save each inverted image as a file with a new filename.
     */
    public void inversionBatch() {
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {
            ImageResource imageInput = new ImageResource(currentFile);
            ImageResource imageInversion = inversionAlgorithm(imageInput);

            String filename = imageInput.getFileName();
            String filenameNew = "inverted-" + filename;

            imageInversion.setFileName(filenameNew);
            imageInversion.save();
        }
    }

    public static void main (String[] args) {
        inversion inversion = new inversion();
        inversion.inversionBatch();
    }
}