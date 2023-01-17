// TurtleTemplate.java
// By Philip Taylor
// Trinity Valley School

import processing.core.*;
import processing.svg.*;


public class TurtleTemplate extends PApplet  {
   private Turtle t;
    
   private boolean record = false; 
    
   public void settings(){
      size(600,600);
   }

   public void setup(){
      t = new Turtle(this, new PVector(width/2, height/2));
      t.speed = 100;
      t.hideTurtle();
      int x = 3;
      while(x < .75*width){
         t.forward(x);
         t.turn(radians(91));
         x+=4;
      }
   }
    
   public void draw(){
      if (record) {
         beginRecord(SVG, "frame-####.svg");
      }
      background(255);
      t.display();
      if (record) {
         endRecord();
         record = false;
      }
   }
   
   public static void main(String[] args) {
      PApplet.main("TurtleTemplate");
   }
   
   // Use a keypress so thousands of files aren't created
   public void mousePressed() {
      record = true;
   }
}
