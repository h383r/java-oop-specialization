package module2;

import processing.core.PApplet;
import processing.core.PImage;

// It's a FUI, so my class needs to extend the PApplet class
// which is my base class for implementing GUIs.
public class MyApplet extends PApplet {

	PImage img;
	
	public void setup() {
		
		//set canvas size
		size(300, 400);
		//set canvas color
		background(255);
		// set pen color
		stroke(0);
		//loads image
		img = loadImage("https://images.unsplash.com/photo-1638812191510-841c3465d60c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1287&q=80", "jpg");
		//resize loaded image to full height of canvas
		img.resize(width, height);
		//display image
		image(img, 0, 0);
		
	}
	
}
