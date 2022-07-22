// Turtle.java
// By Philip Taylor
// Trinity Valley School

import java.util.ArrayList;
import processing.core.*;


public class Turtle{
   private PApplet sketch;
   private int penColor;
   private boolean penDn;
   private int strokeWidth;
   private PImage sprite;
   private PVector position, direction, origpos;
   private int speed;
   private ArrayList<String> commands;
   private int state = 0;
   private float percentOnState = 0;
   private boolean showTurtle = true;

   Turtle(PApplet sketch) {
      this.sketch=sketch;
      sprite = sketch.loadImage("turtle.png");
      origpos = new PVector(sketch.width/2, sketch.height/2);
      initGraphics();
      speed=20;
      commands = new ArrayList<>();
   }

   Turtle(PApplet sketch, PVector p) {
      this.sketch=sketch;
   
      sprite = sketch.loadImage("turtle.png");
      origpos = p.copy();
      initGraphics();
      speed=20;
   
      commands = new ArrayList<>();
   }

   void displaySprite() {
      sketch.imageMode(sketch.CENTER);
      sketch.pushMatrix();
      sketch.translate(position.x, position.y);
      sketch.rotate(direction.heading()+sketch.PI/2);
      sketch.image(sprite, 0, 0, sketch.width/16, sketch.height/16);
      sketch.popMatrix();
   }

   void reset() {
   
      state = 0;
      commands.clear();
   }

   void initGraphics() {
      penColor = sketch.color(0, 0, 255);
      penDn = true;
      strokeWidth = 1;
      position = origpos.copy();
      direction = new PVector(1, 0);
   }

   void turn(float theta) {
      commands.add("turn " + theta);
   }

   void forward(float d) {
      commands.add("forward " + d);
   }


   void display() {
      initGraphics();
   
      for (int i = 0; i <= state; i++) {
         String cmd="";
         try {
            cmd = commands.get(i);
         }
         catch(Exception e) {
            if (showTurtle) displaySprite();
            return;
         }
         //all commands
         if (cmd.contains("turn")) {
            turn_draw(Float.parseFloat(cmd.split(" ")[1]));
            if (i==state) state++;
         } else if (cmd.contains("forward")) {
            float d = Float.parseFloat(cmd.split(" ")[1]);
            if (i < state) {
               forward_draw(d, 1);
            } else {
               forward_draw(d, Math.min(1, percentOnState));
               percentOnState+=speed/250.0;
            }
         } else if (cmd.contains("pencolor")) {
            penColor = Integer.parseInt(cmd.split(" ")[1]);
            if (i==state) state++;
         } else if (cmd.contains("strokewidth")) {
            strokeWidth = Integer.parseInt(cmd.split(" ")[1]);
            if (i==state) state++;
         } else if (cmd.contains("penup")) {
            penDn = false;
            if (i==state) state++;
         } else if (cmd.contains("pendn")) {
            penDn = true;
            if (i==state) state++;
         } else if (cmd.contains("hideTurtle")) {
            showTurtle = false;
            if (i==state) state++;
         }else if (cmd.contains("showTurtle")) {
            showTurtle = true;
            if (i==state) state++;
         }else{}
      }
   
      if (percentOnState >= 1) {
         percentOnState = 0;
         state++;
      }
      if (showTurtle) displaySprite();
   }

   void turn_draw(float theta) {
      direction.rotate(theta);
   }

   void forward_draw(float d, float perc) {
      PVector oldpos = position.copy();
      position.add(direction.copy().mult(d*perc));
   
      if (penDn) {
         sketch.stroke(penColor);
         sketch.strokeWeight(strokeWidth);
         sketch.line(oldpos.x, oldpos.y, position.x, position.y);
      }
   }

   void hideTurtle() {
      commands.add("hideTurtle");
   }
   
   void showTurtle() {
      commands.add("showTurtle");
   }

   void setPenColor(int c) {
      commands.add("pencolor " + c);
   }

   void setStrokeWidth(int w) {
      commands.add("strokewidth " + w);
   }

   void penUp() {
      commands.add("penup");
   }

   void penDown() {
      commands.add("pendn");
   }
}