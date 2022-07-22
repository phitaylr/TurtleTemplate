// TurtleTemplate.java
// By Philip Taylor
// Trinity Valley School

import processing.core.*;


public class TurtleTemplate extends PApplet {
   private Turtle t;
     
   public void settings(){
      size(400,400);
   }

   public void setup(){
      t = new Turtle(this, new PVector(width/4, height/4));
      for(int i=0;i<4;i++){
         t.setStrokeWidth((int) (Math.random()*50)+10);
         t.setPenColor(color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
         t.forward(width/2);
         t.turn(PI/2);
      }
   }
    
   public void draw(){
      background(255);
      t.display();
   
   }
   
   public static void main(String[] args) {
      PApplet.main("TurtleTemplate");
   }
}
