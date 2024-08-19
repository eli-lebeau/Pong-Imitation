
/**
 * Creates a paddle that  moves up and down to block the ball and has a fixed horizontal position
 * @Elebeau
 * @Java 8
 * @6/15/22
 */
public class ComputerPaddle_EL
{
    // instance variables
    private double y; //vertical position
    private double x; //horizontal position
    private double rx; // horizontal radius of the paddle
    private double ry; // vertical radius of the paddle
    private double vy; // vertical velocity of the paddle
    private double dist; //initializes distance variable
    private double destination=0; //initializes destination that the paddle will go to
    public ComputerPaddle_EL(){
        //Sets values of the horizontal radius of the paddle and the vertical radius of the paddle
        rx=0.2;
        ry=0.8;
        //Sets vertical velocity of the paddle
        vy=0.6;
        //Sets the horizontal position as 9
        x=9;
        dist = 0;
    }
    //Draws the paddle
    public void draw() {
        StdDraw.filledRectangle(x, y, rx, ry);
    }
    //Getter methods
    public double gety()
    {
        return y;
    }
    public double getx(){
        return x;
    }

    public double getry() {
        return ry;
    }

    public double getrx() {
        return rx;
    }

    //Alters the position of the paddle so that it always moves between hits
    public void yChange()
    {
        y++; 
    }
    //Moves the paddle using similar triangles
    public void move(double x, double y, double vx, double vy){
    //Checks if the ball is moving the proper direction and is in the proper area    
    if((vx>0 && x>-1) || Math.abs(x)>10){
        //Uses similar triangles as it finds the ratio of the horizontal and veritcal velocities and applies it the horizontal distance to find the vertical distance between the paddle and the ball's destination
        dist=vy/vx*(9-x);
        //Calculates final destination
        if (x>-10) {
            destination=y+dist;
        }       
        
        //returns the paddle to a vertical position of zero
        if (Math.abs(x)>10) destination=0;
        
        //Moves the paddle to the ball's destination until it reaches the end of the board so that the paddle does not go off screen or it reaches the destination
        if (Math.abs(this.y)!=Math.abs(destination)){
            if (destination>this.y+this.vy && this.y+this.vy<10.2) this.y=this.y+this.vy;
            if (destination<this.y-this.vy && this.y-this.vy>-10.2) this.y=this.y-this.vy;
        }
    }
}

}
