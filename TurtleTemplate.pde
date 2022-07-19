Turtle t;

void setup() {
  size(800, 800);
  background(255);
  t = new Turtle();
  t.speed = 100;

}



void draw() {
  if (key != 'r')
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
