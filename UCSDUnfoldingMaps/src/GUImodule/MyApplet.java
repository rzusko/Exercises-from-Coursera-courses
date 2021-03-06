package GUImodule;

import processing.core.*;

public class MyApplet extends PApplet {
	PImage img;
	
	public void setup() {
		size(400, 400);
		background(255);
		stroke(0);
		img = loadImage("https://www.milujemcestovanie.sk/wp-content/uploads/2014/09/Presov.jpg");
		img.resize(0, height);
		image(img, 0, 0);
	}
	
	public void draw() {
		int[] color = sunColorSec(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/6, height/6, width/6, height/6);
	}

	public int[] sunColorSec(float seconds) {
		
		int[] rgb = new int[3];
		
		float diffFrom30 = Math.abs(30 - seconds);
		
		float ratio = diffFrom30/30;
		rgb[0] = (int) (255*ratio);
		rgb[1] = (int) (255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
}