Turtle t;

void setup() {
  size(800, 800);
  background(255);
  t = new Turtle();
  t.speed = 10;
  t.setPenColor(color(255, 0, 255));
  t.setStrokeWidth(10);
  t.forward(100);
  t.turn(30);
  t.setPenColor(color(255, 200, 0));
  t.forward(100);
  t.turn(130);
  t.forward(100);
}



void draw() {
  if (key != 'r')
  background(255);
    t.display();
}

void keyPressed() {

  if (key==' ') {
    background(255);
    //code to restart
  } else if (key == 'r') {
    background(255);
  } else if (key == 's') {
    save("output"+frameCount+".png");
  }
}
