import java.io.*;
import edu.duke.*;

public class grayscale {

    /**
     * Create new images that are greyscale versions
     * @param imageInput
     * @return
     */
    public ImageResource grayscaleAlgorithm(ImageResource imageInput) {

        ImageResource imageOutput = new ImageResource(imageInput.getWidth(), imageInput.getHeight());

        //For each pixel in image
        for (Pixel currentPixel : imageOutput.pixels()) {

            //Look at the corresponding pixel in image
            Pixel inputPixel = imageInput.getPixel(currentPixel.getX(), currentPixel.getY());

            //Greyscale = average of red+blue+green value
            int average = (inputPixel.getRed() + inputPixel.getBlue() + inputPixel.getGreen()) / 3;

            currentPixel.setRed(average);
            currentPixel.setBlue(average);
            currentPixel.setGreen(average);
        }
        return imageOutput;
    }

    /**
     * Select several files and display the greyscale images.
     */
    public void grayscaleSelected() {
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {
            ImageResource imageInput = new ImageResource(currentFile);
            ImageResource imageGrayscale = grayscaleAlgorithm(imageInput);
            imageGrayscale.draw();
        }
    }

    /**
     * Save each greyscale image as a file with a new filename.
     */
    public void grayscaleBatch() {
        DirectoryResource dr = new DirectoryResource();

        for (File currentFile : dr.selectedFiles()) {
            ImageResource imageInput = new ImageResource(currentFile);
            ImageResource imageGrayscale = grayscaleAlgorithm(imageInput);

            String filename = imageInput.getFileName();
            String filenameNew = "gray-" + filename;

            imageGrayscale.setFileName(filenameNew);
            imageGrayscale.save();
        }
    }

    public static void main (String[] args) {
        grayscale grayscale = new grayscale();
        grayscale.grayscaleBatch();
    }
}