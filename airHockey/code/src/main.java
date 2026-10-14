/**
 * This main.java class USES the Object classes provided to make a simple Air Hockey Game .
 * @author Satyaki Saha
 * @param puck is my puck created with Ball Class
 * @param ball1 is my LEFT - MALLET created with Ball Class
 * @param r2 is my white table
 * @param r1 is my blue border
 * @param leftpost is the Left Goal Post
 * @param rightpost is the Right Goal Post
 * @param top_left is the Welcome Message
 * @param Score_Player_Left is the Left Side Score
 * @param Score_Player_Right is the Right Side Score
 * @param music is the Text thing that shows whether music is turned ONN or OFF
 * @param l1 is the Mid - Line
 * @param centre is the Center Field Border
 * @param white_centre is the actual Center
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class main{

    private static Ball puck;
    private static Rectangle r2;
    private static Rectangle r1;
    private static boolean sound = true;


    // I CREATED A movePuck() FUNCTION THAT ACTUALLY, CHANGES THE POSITION OF THE PUCK IF IT SAY COLLIDES WITH MY WHITE RECTANGULAR TABLE, TO KEEP THE PUCK INSIDE THE TABLE

    /**
     * movePuck function allows my puck to deflect along with the help of a formula I used when it hits the table, also it takes in a parameter called clip_bounce to play
     * the sound when the puck hits the table.
     *
     */
    public static void movePuck(Clip clip_bounce) {

        // Update the puck's position using its velocity in the x and y directions
        puck.setXPosition(puck.getXPosition() + puck.getXVelocity());
        puck.setYPosition(puck.getYPosition() + puck.getYVelocity());

        // Check if the puck collides with the table
        if (puck.getXPosition() < r2.getXPosition()) {
            // Puck hits the left side of the table, deflect it to the right
            if (sound==true){

                clip_bounce.start(); // NOT WORKING
                clip_bounce.setMicrosecondPosition(0); // NOT WORKING
            }

            puck.setXPosition(r2.getXPosition());
            puck.setXVelocity(-puck.getXVelocity());
        } else if (puck.getXPosition() + puck.getSize() > r1.getXPosition() + r1.getWidth()) {
            // Puck hits the right side of the table, deflect it to the left
            if (sound==true){

                clip_bounce.start(); // NOT WORKING
                clip_bounce.setMicrosecondPosition(0); // NOT WORKING
            }
            puck.setXPosition(r2.getXPosition() + r2.getWidth() - puck.getSize());
            puck.setXVelocity(-puck.getXVelocity());
        }
        if (puck.getYPosition() < r2.getYPosition()) {
            // Puck hits the top of the table, deflect it downwards
            if (sound==true){

                clip_bounce.start(); // NOT WORKING
                clip_bounce.setMicrosecondPosition(0); // NOT WORKING
            }
            puck.setYPosition(r2.getYPosition());
            puck.setYVelocity(-puck.getYVelocity());
        } else if (puck.getYPosition() + puck.getSize() > r1.getYPosition() + r1.getHeight()) {
            // Puck hits the bottom of the table, deflect it upwards
            if (sound==true){

                clip_bounce.start(); // NOT WORKING
                clip_bounce.setMicrosecondPosition(0); // NOT WORKING
            }
            puck.setYPosition(r2.getYPosition() + r2.getHeight() - puck.getSize());
            puck.setYVelocity(-puck.getYVelocity());
        }

    }

    // END OF movePuck() FUNCTION //


    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        GameArena arena = new GameArena(1000,700); // CREATES/SETUPS THE Game Arena

        Ball ball1 = new Ball(250, 250, 50, "GREEN"); // THIS REPRESENTS THE LEFT/PLAYER 1 MALLET
        ball1.setLayer(5); // I GAVE IT A LAYER OF 5 SINCE MY MALLETS HAVE TO HAVE A HIGHER LAYER THAN THE BOARD

        Ball ball2 = new Ball(250, 250, 50, "RED"); // THIS REPRESENTS THE RIGHT/PLAYER 2 MALLET
        ball2.setLayer(5); // I GAVE IT A LAYER OF 5 SINCE MY MALLETS HAVE TO HAVE A HIGHER LAYER THAN THE BOARD

        // MY IDEA IS TO USE THE RECTANGLE TO MAKE THE OUTER LAYER OF THE BOARD, SO THAT I DO NOT HAVE TO MAKE HORIZONTAL AND VERTICAL LINES

        r1 = new Rectangle(60,100, 875,550, "BLUE"); // X-COORDINATE = 60, Y-COORDINATE = 100, WIDTH = 875, HEIGHT = 550 - FORMS THE OUTER-BOUND/BLUE REGION OF THE TABLE.
        r2 = new Rectangle(80, 120, 837, 510,"WHITE"); // FORMS THE ACTUAL WHITE REGION, WHICH I OVERLAPPED ON TOP OF THE BLUE r1


        Rectangle left_post = new Rectangle(80, 270, 10,170, "GREY"); // LEFT - SIDE GOAL POST
        Rectangle right_post = new Rectangle(907, 270, 10,170, "GREY"); // RIGHT - SIDE GOAL POST

        Text top_left = new Text("Welcome to Air Hockey", 17, 90, 50, "WHITE", 3); // GREETING

        Text Score_Player_Left = new Text("0", 50, 5, 370, "WHITE", 0 ); // SETTING UP THE SCORE BOARD FOR LEFT - PLAYER
        Text Score_Player_Right = new Text("0", 50, 965, 370, "WHITE", 0 ); // SETTING UP THE SCORE BOARD FOR RIGHT - PLAYER

        Text music = new Text("PRESS M TO MUTE AND K TO PLAY", 17, 375, 680, "RED", 4);

        Line l1 = new Line(510, 630, 510, 102, 0.5, "BLUE", 2); // THICKNESS = 0.5 | DECREASING y2 <-> INCREASES SIZE OF THE LINE | x2 AND x1 HAS TO BE SAME VALUE IN ORDER TO ACHIEVE A STRAIGHT HORIZONTAL MIDDLE - LINE.
        Ball center = new Ball(10+(r1.getWidth()/2), r1.getYPosition()/2, 100, "BLUE", 3); // TO CREATE THE BLUISH BORDER OF THE CENTER - FIELD

        Ball white_center = new Ball(10+(r1.getWidth()/2), r1.getYPosition()/2, 98, "WHITE", 3); // 98 IS THE PERFECT DIAMETER FOR WHITE CENTER - ACTUAL CENTER - FIELD
        puck = new Ball(10+(r1.getWidth()/2), r1.getYPosition()/2, 30, "BLACK", 5); // MY PUCK.

        //System.out.println(r1.getWidth()/2); ----> 437.5
        //System.out.println(r1.getYPosition()/2); ----> 50.0

        File start_game = new File("fanfare.wav");
        AudioInputStream start_audio = AudioSystem.getAudioInputStream(start_game);
        Clip clip_start = AudioSystem.getClip();
        clip_start.open(start_audio);
        clip_start.start();

        File ball_mallet_hit = new File("hit.wav");
        AudioInputStream hit = AudioSystem.getAudioInputStream(ball_mallet_hit);
        Clip clip_hit = AudioSystem.getClip();
        clip_hit.open(hit);

        File wall_puck_bounce = new File("bounce.wav");
        AudioInputStream bounce = AudioSystem.getAudioInputStream(wall_puck_bounce);
        Clip clip_bounce = AudioSystem.getClip();
        clip_bounce.open(bounce);

        File applause = new File("applause.wav");
        AudioInputStream start_applause = AudioSystem.getAudioInputStream(applause);
        Clip start_applause_clip = AudioSystem.getClip();
        start_applause_clip.open(start_applause);

        /////////////////////////////////////////// EVERY PLAYING COMPONENTS OF MY GAME ARENA HAS TO BE IN A SPECIFIC POSITION AT THE START OF THE GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        ball1.setXPosition(190);
        ball1.setYPosition(350);

        ball2.setXPosition(805);
        ball2.setYPosition(350);

        center.setXPosition(510);
        center.setYPosition(345); // THE MORE YOU INCREASE, THE DOWN THE BALL GOES IT GOES

        white_center.setXPosition(510);
        white_center.setYPosition(345);

        puck.setXPosition(510);
        puck.setYPosition(345);

        /////////////////////////////////////////// ADDING MY PLAY - ELEMENTS IN THE BALL ARENA \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        arena.addRectangle(r1); // BASE RECTANGLE [BLUE]
        arena.addRectangle(r2); // ON - TOP OF BASE RECTANGLE [WHITE]

        arena.addRectangle(left_post); // LEFT GOAL POST
        arena.addRectangle(right_post); // RIGHT GOAL POST

        arena.addBall(ball1); // LEFT - MALLET
        arena.addBall(ball2); // RIGHT - MALLET

        arena.addBall(center); // CENTER - BORDER

        arena.addBall(white_center); // CENTER
        arena.addBall(puck);

        arena.addText(top_left); // GREETINGS

        arena.addText(Score_Player_Left); // SCORE - BOARD FOR LEFT - PLAYER
        arena.addText(Score_Player_Right); // SCORE - BOARD FOR RIGHT PLAYER


        arena.addLine(l1); // MID - LINE

        arena.addText(music);

        /////////////////////////////////////////// END OF ADDING MY PLAY - ELEMENTS IN THE BALL ARENA \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

        double Speedball1 = 15;
        double Speedball2 = 15;
        double puckX = 510;
        double puckY = 345;
        double puckSpeed = 20;
        double puckAngle = Math.PI/4;
        //puck.setVelocity(puckSpeed * Math.cos(puckAngle), puckSpeed * Math.sin(puckAngle));

        //////////////////////////////////////////////////////////////////////////
        //               Calculating the boundaries of the table                //
        //--------------------------------------------------------------------- //
        //        double leftBound = r2.getXPosition() - r2.getWidth() / 2;     //
        //        double rightBound = r2.getXPosition()+ r2.getWidth() / 2;     //
        //        double topBound = r2.getYPosition() - r2.getHeight() / 2;     //
        //        double bottomBound = r2.getYPosition() + r2.getHeight() / 2;  //
        //////////////////////////////////////////////////////////////////////////

        int Player_1_Score = 0; // SETTING THE SCORE FOR EACH SIDE TO 0 [INITIAL - VALUE]
        int Player_2_Score = 0; // SETTING THE SCORE FOR EACH SIDE TO 0 [INITIAL - VALUE]

        int limit = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Score Limit or Enter 0 to Quit: "));

        if (limit == 0){
            arena.exit();
        }

        while(true){

            arena.pause();

            /////////////////////////////////////////// MALLET - MOVEMENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            // MOVEMENT FOR LEFT - SIDE MALLET
            if (arena.rightPressed() && ball2.getXPosition() + ball2.getSize()/2 < r2.getXPosition() + r2.getWidth() ) {
                // move the mallet to the right
                ball2.setXPosition(ball2.getXPosition() + Speedball1);
            }if (arena.leftPressed() && ball2.getXPosition() - ball2.getSize()/2 > r2.getXPosition() + r2.getWidth()/2) {
                // move the mallet to the left
                ball2.setXPosition(ball2.getXPosition() - Speedball1);
            }if (arena.upPressed() && ball2.getYPosition() - ball2.getSize()/2> r2.getYPosition()) {
                // move the mallet up
                ball2.setYPosition(ball2.getYPosition() - Speedball1);
            }if (arena.downPressed() && ball2.getYPosition() + ball2.getSize()/2 < r2.getYPosition() + r2.getHeight()) {
                // move the mallet down
                ball2.setYPosition(ball2.getYPosition() + Speedball1);
            }

            // MOVEMENT FOR LEFT - SIDE MALLET
            if (arena.letterPressed('d') && ball1.getXPosition() + ball1.getSize()/2 < r2.getXPosition() + r2.getWidth()/2) {
                // move the mallet to the right
                ball1.setXPosition(ball1.getXPosition() + Speedball2);
            }if (arena.letterPressed('a') && ball1.getXPosition() - ball1.getSize()/2 > r2.getXPosition()) {
                // move the mallet to the left
                ball1.setXPosition(ball1.getXPosition() - Speedball2);
            }if (arena.letterPressed('w') && ball1.getYPosition() - ball1.getSize()/2> r2.getYPosition()) {
                // move the mallet up
                ball1.setYPosition(ball1.getYPosition() - Speedball2);
            }if (arena.letterPressed('s') && ball1.getYPosition() + ball1.getSize()/2 < r2.getYPosition() + r2.getHeight()) {
                // move the mallet down
                ball1.setYPosition(ball1.getYPosition() + Speedball2);
            }

            /////////////////////////////////////////// END OF MALLET - MOVEMENTS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            ///////////////////////////////////////////////////  CHEATS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            //---- SIZE - CHEATS ----//

            if (arena.leftMousePressed()){
                ball2.setSize(ball2.getSize()+3);
            }if (arena.rightMousePressed()){
                ball2.setSize(ball2.getSize()-2);
            }if (arena.letterPressed('b')){
                ball1.setSize(ball1.getSize()-1);
            }

            if (arena.letterPressed('z')){
                ball1.setSize(ball1.getSize()+3);
            }if (arena.letterPressed('x')){
                ball1.setSize(ball1.getSize()-2);
            }if (arena.letterPressed('c')){
                ball2.setSize(ball2.getSize()-1);
            }

            ///////////////////////////////////////////////////  END - OF - CHEATS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            if (arena.letterPressed('k') == true){

                sound = true;
                music.setText("Music is Turned On");
                music.setColour("GREEN");
            }

            if (arena.letterPressed('m') == true){

                sound = false;
                music.setText("Music is Turned Off");
                music.setColour("WHITE");
            }

            /////////////////////////////////////////// Checks if the puck is NOT colliding with the MALLETS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            if (ball1.collides(puck) == false & ball2.collides(puck) == false) { // | BASICALLY, DECREASE THE puck SPEED GRADUALLY IF THERE IS NO COLLISION WITH MALLETS |

                if (puck.getXVelocity() < 0) {
                    puck.setXVelocity(puck.getXVelocity() + 0.03);
                } else {
                    puck.setXVelocity(puck.getXVelocity() - 0.03);
                }
                if (puck.getYVelocity() < 0) {
                    puck.setYVelocity(puck.getYVelocity() + 0.03);
                } else {
                    puck.setYVelocity(puck.getYVelocity() - 0.03);
                }
            }

            /////////////////////////////////////////// END OF Checks if the puck is NOT colliding with the MALLETS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


            /////////////////////////////////////////// Checks if the puck is colliding with the MALLETS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
            if (ball1.collides(puck)){

                if (sound==true){

                    clip_hit.start();
                    clip_hit.setMicrosecondPosition(0);
                }
                // Math.atan2 - I would like to define this function, by Imagining the entire GUI [GameArena] as a Coordinate System,
                // A Coordinate system has 4 - Quadrants.  What atan2 does, is measures the angle between the positive X - Axis, and the origin {0, 0} to the point in the CARTESIAN PLANE.

                double puckAngleDiff = Math.atan2(puck.getYPosition() - ball1.getYPosition(), puck.getXPosition() - ball1.getXPosition()) + Math.PI; // MOST IMPORTANT FORMULA AS IT HANDLES MY DEFLECTION
                puck.setVelocity(-puckSpeed * Math.cos(puckAngleDiff), -puckSpeed * Math.sin(puckAngleDiff));


            }

            if (ball2.collides(puck)){

                if (sound == true){
                    clip_hit.start();
                    clip_hit.setMicrosecondPosition(0);
                }

                double puckAngleDiff = Math.atan2(puck.getYPosition() - ball2.getYPosition(), puck.getXPosition() - ball2.getXPosition()) + Math.PI;
                puck.setVelocity(-puckSpeed * Math.cos(puckAngleDiff), -puckSpeed * Math.sin(puckAngleDiff));

            }

            // --------------------------------Check for collisions between the walls and the puck-------------------------------- //

            if (puck.getYPosition() - puck.getSize() / 2 < 0) {

                puck.setYPosition(puck.getSize() / 2);
                puck.setVelocity(puck.getXVelocity(), -puck.getYVelocity());
            }

            // --------------------------------END OF WALL - PUCK COLLISION CHECKING-------------------------------- //

            /////////////////////////////////////////// END OF MALLETS - PUCK COLLISION CHECKING \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            /////////////////////////////////////////// CHECKS FOR GOAL SCORED BY RED - PLAYER 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            //puck.getXPosition() == left_post.getXPosition() && (puck.getYPosition() >= left_post.getYPosition()) && puck.getXPosition() <= (left_post.getYPosition()+left_post.getHeight())
            // if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() <= 80  ){
            if (puck.getYPosition() >= left_post.getYPosition() && puck.getXPosition() <= left_post.getXPosition() && puck.getYPosition() <= left_post.getYPosition() + left_post.getHeight()  ){

                if (sound==true){

                    start_applause_clip.start();
                    start_applause_clip.setMicrosecondPosition(0);
                }

                Player_2_Score += 1; // INCREMENT THE SCORE OF PLAYER 2 BY 1
                Score_Player_Right.setText(Integer.toString(Player_2_Score)); // UPDATE THE RIGHT - SIDE SCORE BOARD
                top_left.setText("Player 2 wins the round"); // DECISION AFTER EVERY GOAL SCORED BY PLAYER 2
                top_left.setColour("RED");

                puck.setXPosition(460); // IF GOAL SCORED BY RIGHT - MALLET PLAYER, THE BALL SHOULD BE SERVED BY THE LEFT - MALLET PLAYER
                puck.setYPosition(345);

                // -----------------------------------SETTING THE MALLETS BACK TO THEIR ORIGINAL POSITION----------------------------------- //

                ball1.setXPosition(190);
                ball1.setYPosition(350);

                ball2.setXPosition(805);
                ball2.setYPosition(350);

                // -----------------------------------END OF SETTING THE MALLETS BACK TO THEIR ORIGINAL POSITION----------------------------------- //

                puck.setVelocity(0,0); // THE PUCK SHOULD NOT MOVE

            }

            /////////////////////////////////////////// END OF CHECK FOR GOAL SCORED BY RED - PLAYER 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


            /////////////////////////////////////////// CHECKS FOR GOAL SCORED BY GREEN - PLAYER 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            // puck.getYPosition() -puck.getSize()/2 >= right_post.getYPosition() && puck.getXPosition() + puck.getSize()/2 >= right_post.getXPosition() && puck.getYPosition()+puck.getSize()/2 <= right_post.getYPosition() + right_post.getHeight()
            // puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() >= 890
            if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() >= 890){

                if (sound==true){

                    start_applause_clip.start();
                    start_applause_clip.setMicrosecondPosition(0);
                }
                Player_1_Score += 1; // INCREMENT THE SCORE OF PLAYER 1 BY 1
                Score_Player_Left.setText(Integer.toString(Player_1_Score)); // UPDATE THE LEFT - SIDE SCORE BOARD
                top_left.setText("Player 1 wins the round"); // DECISION AFTER EVERY GOAL SCORED BY PLAYER 1
                top_left.setColour("GREEN");

                // -----------------------------------IF GOAL SCORED BY LEFT - MALLET PLAYER, THE BALL SHOULD BE SERVED BY THE RIGHT - MALLET PLAYER----------------------------------- //

                puck.setXPosition(560);
                puck.setYPosition(345);

                // -------------------------------------------------------------------------------------------------------------------------------------------------------------------- //

                // -----------------------------------SETTING THE MALLETS BACK TO THEIR ORIGINAL POSITION----------------------------------- //

                ball1.setXPosition(190);
                ball1.setYPosition(350);

                ball2.setXPosition(805);
                ball2.setYPosition(350);

                // -----------------------------------END OF SETTING THE MALLETS BACK TO THEIR ORIGINAL POSITION----------------------------------- //

                puck.setVelocity(0,0); // THE PUCK SHOULD NOT MOVE

            }

            /////////////////////////////////////////// END OF CHECK FOR GOAL SCORED BY GREEN - PLAYER 1\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            /////////////////////////////////////////// GIVING THE PLAYERS THE OPTION TO CONTINUE - OR START A NEW GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

            if (Player_1_Score == limit){ // CHECKS IF PLAYER 1 REACHES THE SCORE OF 6

                top_left.setText("Player 1 wins with "+limit+" points! Press space to start a new game."); // FINAL DECISION OF MATCH
                top_left.setColour("WHITE");

                if (arena.spacePressed()){// GIVE THE PLAYER THE OPTION TO START A NEW GAME


                    clip_start.setMicrosecondPosition(0);
                    clip_start.start();

                    limit = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Score Limit or Enter 0 to Quit: "));

                    if (limit == 0){

                        arena.exit();

                    }

                    top_left.setText("Welcome to Air Hockey");

                    Score_Player_Left.setText("0"); // SETTING SCORE OF LEFT PLAYER BACK TO 0.

                    // ------------------------INITIAL POSITION OF LEFT - MALLETS------------------------ //
                    ball1.setXPosition(190);
                    ball1.setYPosition(350);
                    // ---------------------------------------------------------------------------------- //

                    Score_Player_Right.setText("0"); // // SETTING SCORE OF RIGHT PLAYER BACK TO 0.

                    // ------------------------INITIAL POSITION OF RIGHT - MALLETS------------------------ //
                    ball2.setXPosition(805);
                    ball2.setYPosition(350);
                    // ---------------------------------------------------------------------------------- //

                    // ------------------------INITIAL POSITION OF PUCK------------------------ //
                    puck.setXPosition(510);
                    puck.setYPosition(345);
                    // ------------------------------------------------------------------------ //

                    Player_1_Score = 0; // AGAIN, SETTING THE ACTUAL SCORE - CONTROLLER FOR PLAYER 1 TO 0.
                    Player_2_Score = 0; // AGAIN, SETTING THE ACTUAL SCORE - CONTROLLER FOR PLAYER 2 TO 0.

                    /////////////////////////////////////////// SINCE, NEW GAME STARTED CHECKS FOR GOAL SCORED BY RED - PLAYER 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() <= 80  ){

                        Player_2_Score += 1;
                        Score_Player_Right.setText(Integer.toString(Player_2_Score));
                        top_left.setText("Player 2 wins the round");
                        top_left.setColour("RED");

                        puck.setXPosition(460);
                        puck.setYPosition(345);
                        ball1.setXPosition(190);
                        ball1.setYPosition(350);

                        ball2.setXPosition(805);
                        ball2.setYPosition(350);

                        puck.setVelocity(0,0);

                    }

                    /////////////////////////////////////////// END OF CHECKS FOR GOAL SCORED BY RED - PLAYER 2 AFTER NEW GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    /////////////////////////////////////////// SINCE, NEW GAME STARTED CHECKS FOR GOAL SCORED BY RED - PLAYER 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() >= 890  ){

                        Player_1_Score += 1;
                        Score_Player_Left.setText(Integer.toString(Player_1_Score));
                        top_left.setText("Player 1 wins the round");
                        top_left.setColour("GREEN");

                        puck.setXPosition(560);
                        puck.setYPosition(345);
                        ball1.setXPosition(190);
                        ball1.setYPosition(350);

                        ball2.setXPosition(805);
                        ball2.setYPosition(350);

                        puck.setVelocity(0,0);
                    }

                    /////////////////////////////////////////// END OF CHECKS FOR GOAL SCORED BY GREEN - PLAYER 1 AFTER NEW GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                }
            }

            if (Player_2_Score == limit){ // CHECKS IF PLAYER 2 REACHES THE SCORE OF 6

                top_left.setText("Player 2 wins with "+limit+" points! Press space to start a new game."); // FINAL DECISION OF MATCH
                top_left.setColour("WHITE");

                if (arena.spacePressed()){ // GIVE THE PLAYER THE OPTION TO START A NEW GAME

                    clip_start.setMicrosecondPosition(0);
                    clip_start.start();

                    limit = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your Score Limit or Enter 0 to Quit: "));

                    if (limit == 0){

                        arena.exit();

                    }

                    top_left.setText("Welcome to Air Hockey");

                    Score_Player_Left.setText("0"); // SETTING SCORE OF LEFT PLAYER BACK TO 0.

                    // ------------------------INITIAL POSITION OF LEFT - MALLETS------------------------ //
                    ball1.setXPosition(190);
                    ball1.setYPosition(350);
                    // ---------------------------------------------------------------------------------- //

                    Score_Player_Right.setText("0"); // SETTING SCORE OF RIGHT PLAYER BACK TO 0.

                    // ------------------------INITIAL POSITION OF RIGHT - MALLETS------------------------ //
                    ball2.setXPosition(805);
                    ball2.setYPosition(350);
                    // ---------------------------------------------------------------------------------- //

                    // ------------------------INITIAL POSITION OF PUCK------------------------ //
                    puck.setXPosition(510);
                    puck.setYPosition(345);
                    // ------------------------------------------------------------------------ //

                    Player_1_Score = 0; // AGAIN, SETTING THE ACTUAL SCORE - CONTROLLER FOR PLAYER 1 TO 0.
                    Player_2_Score = 0; // AGAIN, SETTING THE ACTUAL SCORE - CONTROLLER FOR PLAYER 2 TO 0.

                    /////////////////////////////////////////// SINCE, NEW GAME STARTED CHECKS FOR GOAL SCORED BY RED - PLAYER 2 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() <= 80  ){

                        if (sound==true){

                            start_applause_clip.start();
                            start_applause_clip.setMicrosecondPosition(0);
                        }

                        Player_2_Score += 1;
                        Score_Player_Right.setText(Integer.toString(Player_2_Score));
                        top_left.setText("Player 2 wins the round");
                        top_left.setColour("RED");

                        puck.setXPosition(460);
                        puck.setYPosition(345);
                        ball1.setXPosition(190);
                        ball1.setYPosition(350);

                        ball2.setXPosition(805);
                        ball2.setYPosition(350);

                        puck.setVelocity(0,0);

                    }

                    /////////////////////////////////////////// END OF CHECKS FOR GOAL SCORED BY RED - PLAYER 2 AFTER NEW GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    /////////////////////////////////////////// SINCE, NEW GAME STARTED CHECKS FOR GOAL SCORED BY GREEN - PLAYER 1 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    if (puck.getYPosition() >= 270 && puck.getYPosition() <= 440 && puck.getXPosition() >= 890  ){

                        if (sound==true){

                            start_applause_clip.start();
                            start_applause_clip.setMicrosecondPosition(0);
                        }

                        Player_1_Score += 1;
                        Score_Player_Left.setText(Integer.toString(Player_1_Score));
                        top_left.setText("Player 1 wins the round");
                        top_left.setColour("GREEN");

                        puck.setXPosition(560);
                        puck.setYPosition(345);
                        ball1.setXPosition(190);
                        ball1.setYPosition(350);

                        ball2.setXPosition(805);
                        ball2.setYPosition(350);

                        puck.setVelocity(0,0);

                    }


                    /////////////////////////////////////////// END OF CHECKS FOR GOAL SCORED BY GREEN - PLAYER 1 AFTER NEW GAME \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                }
            }

            // update the game arena
            arena.pause();
            movePuck(clip_bounce); // WORKING

        }
    }
}
