import java.util.ArrayList;

class Turtle {

  color penColor;
  boolean penDn;
  int strokeWidth;
  PImage sprite;
  PVector position, direction;
  int speed;
  ArrayList<String> commands;
  int state = 0;
  float percentOnState = 0;


  Turtle() {

    sprite = loadImage("turtle.png");
    initGraphics();
    speed=20;
    commands = new ArrayList<>();
  }

  void displaySprite() {
    imageMode(CENTER);
    pushMatrix();
    translate(position.x, position.y);
    rotate(direction.heading()+PI/2);
    image(sprite, 0, 0, width/16, height/16);
    popMatrix();
  }

  void reset() {

    state = 0;
    commands.clear();
  }

  void initGraphics() {
    background(255);
    penColor = color(0, 0, 255);
    penDn = true;
    strokeWidth = 1;
    position = new PVector(width/2, height/2);
    direction = new PVector(1, 0);
  }

  void turn(float theta) {
    commands.add("turn " + theta);
  }

  void forward(int d) {
    commands.add("forward " + d);
  }

  void display() {
    if (state >= commands.size()) {
      reset();
      return;
    }
    //println(commands + " " + state + " " + percentOnState);
    initGraphics();

    for (int i = 0; i <= state; i++) {
      String cmd="";
      try {
        cmd = commands.get(i);
      }
      catch(Exception e) {
        displaySprite();
        return;
      }
      //all commands
      if (cmd.contains("turn")) {
        turn_draw(Float.parseFloat(cmd.split(" ")[1]));
        if (i==state) state++;
      } else if (cmd.contains("forward")) {
        int d = Integer.parseInt(cmd.split(" ")[1]);
        if (i < state) {
          forward_draw(d, 1);
        } else {
          forward_draw(d, min(1, percentOnState));
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
      }else if (cmd.contains("pendn")) {
        penDn = true;
        if (i==state) state++;
      }
    }

    if (percentOnState >= 1) {
      percentOnState = 0;
      state++;
    }
    displaySprite();
  }

  void turn_draw(float theta) {
    direction.rotate(theta);
  }

  void forward_draw(int d, float perc) {
    PVector oldpos = position.copy();
    position.add(direction.copy().mult(d*perc));

    if (penDn) {
      stroke(penColor);
      strokeWeight(strokeWidth);
      line(oldpos.x, oldpos.y, position.x, position.y);
    }
  }



  void setPenColor(color c) {
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