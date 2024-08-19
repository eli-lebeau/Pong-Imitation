
/**
 * Driver for the pong game. Runs methods for the Ball_EL, Paddle_EL, and ComputerPaddle_EL ADTs
 * Also draws window and has a score keeping feature for the pong game
 * @Elebeau
 * @Java 8
 * @6/15/22
 */
public class Pong_EL
{
    public static void main(String[] args) {
        //initializing scores
        int playerScore=0;
        int compScore=0;
        //creates object paddle from the Paddle_EL class
        Paddle_EL paddle = new Paddle_EL();
        //creates object CompPaddle from the ComputerPaddle_EL class
        ComputerPaddle_EL CompPaddle = new ComputerPaddle_EL();
        //creates ball onject from the Ball_EL class
        Ball_EL blackBall = new Ball_EL();
        //Draws window
        double minBound = -10.0;
        double maxBound = 10.0;
        StdDraw.setXscale(minBound, maxBound);
        StdDraw.setYscale(minBound, maxBound);
        //initiallizes variables that make the while loops run
        boolean play=false;
        boolean round=false;
        boolean start=true;
        
        //Shows welcome screen
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0,0, "Welcome to Pong, click to begin");
        StdDraw.enableDoubleBuffering();
        //outer while loop for the welcome screen
        while (start==true){
            if (StdDraw.isMousePressed()){
                play=true;
                round=true;
            }
            //Mouse being clicked begins the inner while loops
            while (round==true){
                while (play==true){       
                
                //Draws dividing dashed line on the screen
                for (int i = (int)minBound; i< (int)maxBound; i++){
                    StdDraw.line(0,i,0,i+0.5);
                }
                
                //Calls methods for the blackBall, see Ball_EL ADT for details about methods
                blackBall.move();
                blackBall.draw();
                blackBall.PlayerCollide(paddle);
                blackBall.ComputerCollide(CompPaddle);
                
                //Draws player's paddle
                paddle.draw();
                
                //Method for moving and drawing the computer's paddle
                CompPaddle.move(blackBall.getrx(), blackBall.getry(), blackBall.getvx(), blackBall.getvy());
                CompPaddle.draw();
                
                
                //if statements add a point to one side's score based on if the ball enters a certain region behind the paddle
                if (blackBall.getrx()> maxBound && blackBall.getrx()<maxBound+Math.abs(blackBall.getvx())) playerScore++;
                if (blackBall.getrx()< minBound && blackBall.getrx()>minBound-Math.abs(blackBall.getvx())) compScore++;
                //Converts score to string from int and displays it at the top of the screen
                String score1=Integer.toString(playerScore);
                StdDraw.text(-2, 9, score1);
                String score2=Integer.toString(compScore);
                StdDraw.text(2, 9, score2);
                //Shows board
                StdDraw.show();
                StdDraw.pause(20);
                
                //Ends while loop if either side gets to 11 points
                if (playerScore==11 || compScore==11) play=false;
                //clears window and pauses
                StdDraw.clear(StdDraw.BLACK);
                StdDraw.pause(20);
                StdDraw.setPenColor(StdDraw.WHITE);
                }
                //clear window
                StdDraw.clear(StdDraw.BLACK);
                //Displays text after the game depending on who won
                StdDraw.text(0,-1, "Click to play again");
                if (playerScore==11) StdDraw.text(0,0, "You Win!");
                if (compScore==11) StdDraw.text(0,0, "You Lost");
                //if the mouse is pressed again, the scores are reset and the variable that needs to be true for the loop to run is made true again
                if (StdDraw.isMousePressed()){
                        play=true;
                        playerScore=0;
                        compScore=0;
                    }
                StdDraw.show();
            }
        }
    }
}