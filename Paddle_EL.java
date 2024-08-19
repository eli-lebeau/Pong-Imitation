
/**
 * Creates a paddle that the user moves up and down with StdDraw.mouseY and has a fixed horizontal position
 * @Elebeau
 * @Java 8
 * @6/15/22
 */
public class Paddle_EL
{
    // instance variables
    private double y; //vertical position
    private double x; //horiztonal position
    private double rx; // horizontal radius of the paddle
    private double ry; // vertical radius of the paddle
    public Paddle_EL(){
        ry=0.8; //sets horizontal radius of the paddle
        rx=0.2; // set vertical radius of the paddle
        x = -9; //the x position of the computer paddle is always 
    }
    public void draw() {
        y=StdDraw.mouseY(); //Changes value of y based on the user's mouse's vertical position
        StdDraw.filledRectangle(x, y, rx, ry); //Draws the paddle with a fixed horizontal position of -9 and veritcal position of y.
    }
    //getter method for y
        public double gety()
    {
        return y;
    }

    public double getrx(){
        return rx;
    }

    public double getry(){
        return ry;
    }

    public double getx(){
        return x;
    }
}
