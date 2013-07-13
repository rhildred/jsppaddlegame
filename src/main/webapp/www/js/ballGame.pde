noStroke();

var paddleWidth = 100;

// the speed of the ball when it first leaves the paddle
var initialSpeedX = 7;
var initialSpeedY = -8;

// the current speed of the ball
var ballSpeedX = initialSpeedX;
var ballSpeedY = initialSpeedY;

// the current location of the ball
var ballX = 0;
var ballY = 0;
var nScore = 0;
// true if ball is moving, false if ball is attached to paddle
var ballMoving = false;

void setup() {
  size(400, 400);
  frameRate(20);
}

void draw() {
    background(0, 108, 135);
    
    // draw the paddle
    fill(240, 126, 65);
    rect(mouseX - paddleWidth/2, 350, paddleWidth, 10);
    
    // move the ball
    if (ballMoving) {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
    }
    else {
        ballX = mouseX;
        ballY = 340;
    }
    
    // draw the ball
    fill(255, 234, 0);
    ellipse(ballX, ballY, 20, 20);
    
    // check if ball has hit the top wall
    if (ballY <= 10) {
        ballSpeedY = -ballSpeedY;
    }
    // check if the ball has hit the left wall
    if (ballX <= 10) {
        ballSpeedX = -ballSpeedX;
    }
    // check if the ball has hit the right wall
    if (ballX >= 390) {
        ballSpeedX = -ballSpeedX;
    }
    // check if the ball has hit the paddle
    if (ballMoving && ballY >= 340 && ballY < 346 && ballX >= mouseX - paddleWidth/2 && ballX <= mouseX + paddleWidth/2) {
        ballSpeedY = -ballSpeedY;
        nScore++;
        jQuery("#score").html(nScore);
    }
    // check if ball fell out the bottom
    if (ballY >= 400) {
        ballMoving = false;
        if(jQuery("#currenthighscore").html() < nScore)
        {
        	jQuery("#highscore").html(nScore);
        	jQuery( "#dialog-form" ).dialog( "open" );
        }
        else
        {
        	jQuery("#score").html(0);
        }
        nScore = 0;
    }
}

// this function gets called automatically
// when the mouse is clicked
void mousePressed() {
    if (!ballMoving) {
        // reset the ball speed
        ballSpeedX = initialSpeedX;
        ballSpeedY = initialSpeedY;
        
        ballMoving = true;
    }
}