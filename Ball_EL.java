/**
 * This program draws a balls and has it move. It tests for when its collides with the paddles and the walls and changes the velocity of the ball accordingly
 * 
 * @Elebeau
 * @Java 8
 * @6/15/22
 */



public class Ball_EL {

    // instance variables
    private double rx;// y position
    private double ry;// x position
    private double vx;// x velocity
    private double vy;// y velocity
    private double radius;// radius
    private double angle;// angle at which the ball is travelling
    private final double BOUNDARY = 10.0; //Boundary of the screen
    private final double INITIALVELOCITY = 0.41;
    
    public Ball_EL() {
        rx = 0;
        ry = -4;
        vx = INITIALVELOCITY;
        vy = INITIALVELOCITY;
        radius = 0.2;
    }

    /** getter methods */

    public double getrx()
    {
        return rx;
    }

    public double getry()
    {
        return ry;
    }

    public double getRadius()
    {
        return radius;
    }

    public double getvx()
    {
        return vx;
    }

    public double getvy()
    {
        return vy;
    }

    //setter methods
    public void setX(double newX)
    {
        rx=newX; 
    }

    public void setY(double newY)
    {
        ry=newY; 
    }

    public void setvx(double newvx)
    {
        vx=newvx; 
    }

    public void setvy(double newvy)
    {
        vy=newvy; 
    }



    // bounce of horizontal wall by reflecting y-velocity
    private void bounceOffHorizontalWall() {
        this.vy = -this.vy;
    }

    // move the ball one step
    public void move() {
        //Checks for collision with the horizontal walls
        if (Math.abs(this.ry + this.vy) + radius > BOUNDARY) {
            this.bounceOffHorizontalWall();
        }
        //Draws a ball in the center after the ball gets past a paddle so that it seems like the ball is blinking in the center before its position is reset to the center and it begins moving again on the board
        if (Math.abs(this.rx + this.vx + radius) > 16.0 && Math.abs(this.rx + this.vx + radius) < 19.0 || Math.abs(this.rx + this.vx + radius) > 21.0 && Math.abs(this.rx + this.vx + radius) < 24.0){
            StdDraw.filledSquare(0,0,radius);
        }
        //Reset the position of the ball to the center of the board after it has gone sufficiently far off the board to provide enough time before the serve
        if (Math.abs(this.rx + this.vx + radius) > 26.0){
            this.rx=0;
            this.ry=0;
            //sets the x velocity such that whoever scored the last point receives the serve
            if (this.vx > 0) this.vx= -INITIALVELOCITY;
            else this.vx= INITIALVELOCITY;
            this.vy= -INITIALVELOCITY;
        }
        //Moves the ball by adding its velocity to its position
        this.rx = this.rx + this.vx;
        this.ry = this.ry + this.vy;        
    }
    
    //Method for collision detection between the ball and the player's paddle
    public void PlayerCollide(Paddle_EL paddle){
        double distX; // x distance between points
        double distY; // y distance between points
        //finds the vertical distance between the ball and the paddle
        distY = Math.abs(this.ry-paddle.gety());
        //finds the horizontal distance between the ball and the paddle
        distX=Math.abs(this.rx - paddle.getx());
        //Detects collision if the distances between the ball and the paddle are less than the sum of the radii
        if (distY<=this.radius+paddle.getry() && distX<=this.radius+paddle.getrx()){
            //Changes the velocity of the ball and sends it at a different angle depending on where it hits the paddle
            //for loops go through all areas of the paddle
            for (double i = 1; i>0.4; i-=0.2){
                if (this.ry-paddle.gety()<=i && this.ry-paddle.gety()>i-0.2){
                    //Creates a different angle to send the ball of at 
                    angle=90-90/5*((1.2-i)/(paddle.getry()/4));
                    //Changes the vertical velocity of the ball based on the angle
                    this.vy=INITIALVELOCITY*Math.sin(angle/180*Math.PI);
                }
            }
            for (double i = -0.2; i>-1; i-=0.2){
                if (this.ry-paddle.gety()<=i && this.ry-paddle.gety()>i-0.2){
                    //Creates a different angle to send the ball of at
                    double angle=90-90/5*((1+i)/(paddle.getry()/4));
                    //Changes the vertical velocity of the ball based on the angle
                    this.vy=-INITIALVELOCITY*Math.sin(angle/180*Math.PI);
                }
            }
            //checks if the ball hits the middle of the paddle
            if (Math.abs(this.ry-paddle.gety())<=paddle.getrx()){
                //sets the vertical velocity to zero
                this.vy=0;
            }
            //Changes the vertical horizontal of the ball based on the angle
            this.vx=0.8*Math.cos(angle/180*Math.PI);
        }
    }

    //Method for collision detection between the ball and the computer's paddle
    public void ComputerCollide(ComputerPaddle_EL paddle){
        double distX; // x distance between points
        double distY; // y distance between points
        //finds the vertical distance between the ball and the paddle
        distY = Math.abs(this.ry-paddle.gety());
        //finds the horizontal distance between the ball and the paddle
        distX=Math.abs(this.rx-paddle.getx());
        //Detects collision if the distances between the ball and the paddle are less than the sum of the radii
        if (distY<=this.radius+paddle.getry() && distX<=this.radius+paddle.getrx()){
            //Changes the velocity of the ball and sends it at a different angle depending on where it hits the paddle
            //for loops go through all areas of the paddle
            for (double i = 1; i>0.4; i-=0.2){
                if (this.ry-paddle.gety()<=i && this.ry-paddle.gety()>i-0.2){
                    //Creates a different angle to send the ball of at 
                    angle=90-18*((1.2-i)/0.2);
                    //Changes the vertical velocity of the ball based on the angle
                    this.vy=0.8*Math.sin(angle/180*Math.PI);
                }
            }
            for (double i = -0.2; i>-1; i-=0.2){
                if (this.ry-paddle.gety()<=i && this.ry-paddle.gety()>i-0.2){
                    //Creates a different angle to send the ball of at 
                    double angle=90-18*((1+i)/0.2);
                    //Changes the vertical velocity of the ball based on the angle
                    this.vy=-0.8*Math.sin(angle/180*Math.PI);
                }
            }
            //checks if the ball hits the middle of the paddle
            if (Math.abs(this.ry-paddle.gety())<=radius){
                //sets the vertical velocity to zero
                this.vy=0;
                //Moves the paddle after collision
                paddle.yChange();
            }
            //Changes the vertical horizontal of the ball based on the angle
            this.vx=-0.8*Math.cos(angle/180*Math.PI);            
    }
    }

    // draws the ball
    public void draw() {
        StdDraw.filledSquare(rx, ry, radius);
    }

}

