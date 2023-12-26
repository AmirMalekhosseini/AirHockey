import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Screen extends JPanel implements KeyListener {


    private BufferedImage backgroundImage;
    private boolean upPlayerOne;
    private boolean downPlayerOne;
    private boolean leftPlayerOne;
    private boolean rightPlayerOne;
    private boolean upPlayerTwo;
    private boolean downPlayerTwo;
    private boolean leftPlayerTwo;
    private boolean rightPlayerTwo;

    private boolean hitUpWall;
    private boolean hitDownWall;
    private boolean hitLeftWall;
    private boolean hitRightWall;

    private boolean playerOneHit;
    private boolean playerTwoHit;
    private boolean playerOneHitForPrizeOrGoal;
    private boolean playerTwoHitForPrizeOrGoal;
    private boolean ballThroughPlayerOne;
    private boolean ballThroughPlayerTwo;

    private boolean biggerLine;
    private boolean fireBall;
    private boolean mirrorWall;
    private boolean playerOneCatchFireBall;
    private boolean playerOneCatchBiggerLine;
    private boolean playerOneCatchMirrorWall;
    private boolean playerTwoCatchFireBall;
    private boolean playerTwoCatchBiggerLine;
    private boolean playerTwoCatchMirrorWall;
    private boolean isPrizeCatch;
    private boolean isPrizeVisible;
    private boolean isPrizeBigger;
    private int activePrize = 0;// 1=fireball 2=biggerLine 3=mirrorWall

    private int playerOneBigCircleX = 310;
    private int playerOneBigCircleY = 820;
    private int playerOneSmallCircleX = 333;
    private int playerOneSmallCircleY = 843;
    private int playerOneWhiteCircleX = 312;
    private int playerOneWhiteCircleY = 823;
    private int playerTwoBigCircleX = 310;
    private int playerTwoBigCircleY = 30;
    private int playerTwoSmallCircleX = 333;
    private int playerTwoSmallCircleY = 53;
    private int playerTwoWhiteCircleX = 312;
    private int playerTwoWhiteCircleY = 33;
    private int ballX = 326;
    private int ballY = 420;
    private int ballXVelocity;
    private int ballYVelocity;
    private int ballSpeed = 20;

    int lineLeftPlayerOne = 215;
    int lineRightPlayerOne = 420;
    int lineLeftPlayerTwo = 215;
    int lineRightPlayerTwo = 430;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    int chooseVForStart;
    int choosePrize;
    int xPrize;
    int yPrize;
    int prizeWidth = 30;
    int prizeHeight = 30;


    public Screen() {

        try {
            String pathBackground = "airhockey.png";
            File fileBackground = new File(pathBackground);
            backgroundImage = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Random random = new Random();
        chooseVForStart = random.nextInt(2);
        if (chooseVForStart == 0) {
            ballYVelocity = -20;
        } else {
            ballYVelocity = 20;
        }

        this.setBounds(0, 0, 700, 1000);

        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();
        this.addKeyListener(this);

    }

    public void paint(Graphics g) {

        if (Timer.secondForPrize >= 11) {
            if (activePrize == 0) {// generate prize
                isPrizeCatch = false;
                Random randomForXAndY = new Random();
                xPrize = randomForXAndY.nextInt(600 - 100) + 100;
                yPrize = randomForXAndY.nextInt(850 - 150) + 150;
                Random randomForPrize = new Random();
                choosePrize = randomForPrize.nextInt(4 - 1) + 1;
                if (choosePrize == 1) {
                    fireBall = true;
                    mirrorWall = false;
                    biggerLine = false;
                    activePrize = 1;
                } else if (choosePrize == 2) {
                    fireBall = true;
                    mirrorWall = false;
                    biggerLine = false;
                    activePrize = 1;
                } else {
                    fireBall = true;
                    mirrorWall = false;
                    biggerLine = false;
                    activePrize = 1;
                }
                isPrizeVisible = true;
            }

            if (Timer.secondForPrize >= 16 && !isPrizeCatch) {
                isPrizeBigger = true;
            }

            if (Timer.secondForPrize == 21) {// reset second counter
                Timer.secondForPrize = 0;
                isPrizeVisible = false;
                isPrizeBigger = false;
                fireBall = false;
                biggerLine = false;
                mirrorWall = false;
                activePrize = 0;
            }
        }
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(backgroundImage, 0, 0, null);
        if (playerOneCatchBiggerLine) {
            g2D.setPaint(Color.GREEN);
            g2D.setStroke(new BasicStroke(20.0f));
            g2D.drawLine(170, 8, 220, 8);
            g2D.drawLine(477, 8, 527, 8);
            lineRightPlayerTwo = 500;
            lineLeftPlayerTwo = 150;
        }
        if (playerTwoCatchBiggerLine) {
            g2D.setPaint(Color.GREEN);
            g2D.setStroke(new BasicStroke(20.0f));
            g2D.drawLine(170, 925, 220, 925);
            g2D.drawLine(477, 925, 527, 925);
            lineRightPlayerOne = 500;
            lineLeftPlayerOne = 150;
        }
        if (playerOneCatchFireBall) {
            ballThroughPlayerTwo = true;
            ballSpeed = ballSpeed * 2;
        }

        if (playerTwoCatchFireBall) {
            ballThroughPlayerOne = true;
            ballSpeed = ballSpeed * 2;
        }

        if (playerOneCatchMirrorWall || playerTwoCatchMirrorWall) {
            hitRightWall = false;
            hitLeftWall = false;
            hitDownWall = false;
            hitUpWall = false;
        }


        g2D.setPaint(MyFrame.playerOneColor);
        Font f = new Font("Comic Sans MS", Font.BOLD, 55);
        g2D.setFont(f);
        g2D.drawString(String.valueOf(playerOneScore), 565, 500);

        g2D.setPaint(MyFrame.playerTwoColor);
        g2D.setFont(f);
        g2D.drawString(String.valueOf(playerTwoScore), 565, 430);

        g2D.setPaint(MyFrame.playerOneColor);// draw playerOne
        g2D.setStroke(new BasicStroke(8.0f));
        g2D.drawOval(playerOneBigCircleX, playerOneBigCircleY, 85, 85);
        g2D.setPaint(Color.white);
        g2D.fillOval(playerOneWhiteCircleX, playerOneWhiteCircleY, 80, 80);
        g2D.setPaint(MyFrame.playerOneColor);
        g2D.setStroke(new BasicStroke(5.0f));
        g2D.drawOval(playerOneSmallCircleX, playerOneSmallCircleY, 40, 40);

        g2D.setPaint(MyFrame.playerTwoColor);// draw playerTwo
        g2D.setStroke(new BasicStroke(8.0f));
        g2D.drawOval(playerTwoBigCircleX, playerTwoBigCircleY, 85, 85);
        g2D.setPaint(Color.white);
        g2D.fillOval(playerTwoWhiteCircleX, playerTwoWhiteCircleY, 80, 80);
        g2D.setPaint(MyFrame.playerTwoColor);
        g2D.setStroke(new BasicStroke(5.0f));
        g2D.drawOval(playerTwoSmallCircleX, playerTwoSmallCircleY, 40, 40);

        intersect();
        intersectPrize();

        g2D.setPaint(Color.red);
        g2D.fillOval(ballX, ballY, 55, 55);

        if (!isPrizeCatch && isPrizeVisible) {// draw prize
            if (fireBall) {
                g2D.setPaint(Color.orange);
                g2D.fillOval(xPrize, yPrize, prizeWidth, prizeHeight);
            } else if (biggerLine) {
                g2D.setPaint(Color.GREEN);
                g2D.fillOval(xPrize, yPrize, prizeWidth, prizeHeight);
            } else if (mirrorWall) {
                g2D.setPaint(Color.blue);
                g2D.fillOval(xPrize, yPrize, prizeWidth, prizeHeight);
            }
        }

        if (MyFrame.isGameEnd) {
            g2D.drawImage(backgroundImage, 0, 0, null);
            g2D.setPaint(MyFrame.playerOneColor);
            g2D.setFont(f);
            g2D.drawString(String.valueOf(playerOneScore), 565, 500);
            g2D.setPaint(MyFrame.playerOneColor);// draw playerOne
            g2D.setStroke(new BasicStroke(8.0f));
            g2D.drawOval(playerOneBigCircleX, playerOneBigCircleY, 85, 85);
            g2D.setPaint(Color.white);
            g2D.fillOval(playerOneWhiteCircleX, playerOneWhiteCircleY, 80, 80);
            g2D.setPaint(MyFrame.playerOneColor);
            g2D.setStroke(new BasicStroke(5.0f));
            g2D.drawOval(playerOneSmallCircleX, playerOneSmallCircleY, 40, 40);

            g2D.setPaint(MyFrame.playerTwoColor);// draw playerTwo
            g2D.setStroke(new BasicStroke(8.0f));
            g2D.drawOval(playerTwoBigCircleX, playerTwoBigCircleY, 85, 85);
            g2D.setPaint(Color.white);
            g2D.fillOval(playerTwoWhiteCircleX, playerTwoWhiteCircleY, 80, 80);
            g2D.setPaint(MyFrame.playerTwoColor);
            g2D.setStroke(new BasicStroke(5.0f));
            g2D.drawOval(playerTwoSmallCircleX, playerTwoSmallCircleY, 40, 40);

            g2D.setPaint(Color.red);
            g2D.fillOval(ballX, ballY, 55, 55);

            g2D.setPaint(MyFrame.playerTwoColor);
            g2D.setFont(f);
            g2D.drawString(String.valueOf(playerTwoScore), 565, 430);
            Font f1 = new Font("Comic Sans MS", Font.BOLD, 80);
            g2D.setPaint(Color.BLACK);
            g2D.drawString("Game is Finished", 140, 460);
            MyFrame.finishTheGame = true;
            GameHistory.isGameFinished = "Yes";
        }

        movePlayers();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            upPlayerOne = true;
        }

        if (e.getKeyChar() == 's') {
            downPlayerOne = true;
        }

        if (e.getKeyChar() == 'a') {
            leftPlayerOne = true;
        }

        if (e.getKeyChar() == 'd') {
            rightPlayerOne = true;
        }

        if (e.getKeyChar() == 'o') {
            upPlayerTwo = true;
        }

        if (e.getKeyChar() == 'l') {
            downPlayerTwo = true;
        }

        if (e.getKeyChar() == 'k') {
            leftPlayerTwo = true;
        }

        if (e.getKeyChar() == ';') {
            rightPlayerTwo = true;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            upPlayerOne = false;
        }

        if (e.getKeyChar() == 's') {
            downPlayerOne = false;
        }

        if (e.getKeyChar() == 'a') {
            leftPlayerOne = false;
        }

        if (e.getKeyChar() == 'd') {
            rightPlayerOne = false;
        }

        if (e.getKeyChar() == 'o') {
            upPlayerTwo = false;
        }

        if (e.getKeyChar() == 'l') {
            downPlayerTwo = false;
        }

        if (e.getKeyChar() == 'k') {
            leftPlayerTwo = false;
        }

        if (e.getKeyChar() == ';') {
            rightPlayerTwo = false;
        }

    }

    public void movePlayers() {
        try {

            if (upPlayerOne && !rightPlayerOne && !leftPlayerOne && playerOneBigCircleY >= 453) {
                playerOneBigCircleY -= 10;
                playerOneSmallCircleY -= 10;
                playerOneWhiteCircleY -= 10;
            }
            if (downPlayerOne && !rightPlayerOne && !leftPlayerOne && playerOneBigCircleY <= 822) {
                playerOneBigCircleY += 10;
                playerOneSmallCircleY += 10;
                playerOneWhiteCircleY += 10;
            }
            if (leftPlayerOne && !upPlayerOne && !downPlayerOne && playerOneBigCircleX >= 65) {
                playerOneBigCircleX -= 10;
                playerOneSmallCircleX -= 10;
                playerOneWhiteCircleX -= 10;
            }
            if (rightPlayerOne && !upPlayerOne && !downPlayerOne && playerOneBigCircleX <= 550) {
                playerOneBigCircleX += 10;
                playerOneSmallCircleX += 10;
                playerOneWhiteCircleX += 10;
            }
            if (upPlayerOne && rightPlayerOne && playerOneBigCircleY >= 453 && playerOneBigCircleX <= 550) {
                playerOneBigCircleY -= 7;
                playerOneSmallCircleY -= 7;
                playerOneWhiteCircleY -= 7;

                playerOneBigCircleX += 7;
                playerOneSmallCircleX += 7;
                playerOneWhiteCircleX += 7;
            }
            if (upPlayerOne && leftPlayerOne && playerOneBigCircleY >= 453 && playerOneBigCircleX >= 65) {
                playerOneBigCircleY -= 7;
                playerOneSmallCircleY -= 7;
                playerOneWhiteCircleY -= 7;

                playerOneBigCircleX -= 7;
                playerOneSmallCircleX -= 7;
                playerOneWhiteCircleX -= 7;
            }
            if (downPlayerOne && rightPlayerOne && playerOneBigCircleY <= 822 && playerOneBigCircleX <= 550) {
                playerOneBigCircleY += 7;
                playerOneSmallCircleY += 7;
                playerOneWhiteCircleY += 7;

                playerOneBigCircleX += 7;
                playerOneSmallCircleX += 7;
                playerOneWhiteCircleX += 7;
            }
            if (downPlayerOne && leftPlayerOne && playerOneBigCircleY <= 822 && playerOneBigCircleX >= 65) {
                playerOneBigCircleY += 7;
                playerOneSmallCircleY += 7;
                playerOneWhiteCircleY += 7;

                playerOneBigCircleX -= 7;
                playerOneSmallCircleX -= 7;
                playerOneWhiteCircleX -= 7;
            }


            if (upPlayerTwo && !rightPlayerTwo && !leftPlayerTwo && playerTwoBigCircleY >= 23) {
                playerTwoBigCircleY -= 10;
                playerTwoSmallCircleY -= 10;
                playerTwoWhiteCircleY -= 10;
            }
            if (downPlayerTwo && !rightPlayerTwo && !leftPlayerTwo && playerTwoBigCircleY <= 349) {
                playerTwoBigCircleY += 10;
                playerTwoSmallCircleY += 10;
                playerTwoWhiteCircleY += 10;
            }
            if (leftPlayerTwo && !upPlayerTwo && !downPlayerTwo && playerTwoBigCircleX >= 63) {
                playerTwoBigCircleX -= 10;
                playerTwoSmallCircleX -= 10;
                playerTwoWhiteCircleX -= 10;

            }
            if (rightPlayerTwo && !upPlayerTwo && !downPlayerTwo && playerTwoBigCircleX <= 550) {
                playerTwoBigCircleX += 10;
                playerTwoSmallCircleX += 10;
                playerTwoWhiteCircleX += 10;
            }
            if (upPlayerTwo && rightPlayerTwo && playerTwoBigCircleY >= 23 && playerTwoBigCircleX <= 550) {
                playerTwoBigCircleY -= 7;
                playerTwoSmallCircleY -= 7;
                playerTwoWhiteCircleY -= 7;

                playerTwoBigCircleX += 7;
                playerTwoSmallCircleX += 7;
                playerTwoWhiteCircleX += 7;
            }
            if (upPlayerTwo && leftPlayerTwo && playerTwoBigCircleY >= 23 && playerTwoBigCircleX >= 63) {
                playerTwoBigCircleY -= 7;
                playerTwoSmallCircleY -= 7;
                playerTwoWhiteCircleY -= 7;

                playerTwoBigCircleX -= 7;
                playerTwoSmallCircleX -= 7;
                playerTwoWhiteCircleX -= 7;
            }
            if (downPlayerTwo && rightPlayerTwo && playerTwoBigCircleY <= 349 && playerTwoBigCircleX <= 550) {
                playerTwoBigCircleY += 7;
                playerTwoSmallCircleY += 7;
                playerTwoWhiteCircleY += 7;

                playerTwoBigCircleX += 7;
                playerTwoSmallCircleX += 7;
                playerTwoWhiteCircleX += 7;
            }
            if (downPlayerTwo && leftPlayerTwo && playerTwoBigCircleY <= 349 && playerTwoBigCircleX >= 63) {
                playerTwoBigCircleY += 7;
                playerTwoSmallCircleY += 7;
                playerTwoWhiteCircleY += 7;

                playerTwoBigCircleX -= 7;
                playerTwoSmallCircleX -= 7;
                playerTwoWhiteCircleX -= 7;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void refresh() {
        lineLeftPlayerOne = 215;
        lineRightPlayerOne = 420;
        lineLeftPlayerTwo = 215;
        lineRightPlayerTwo = 430;

        prizeHeight = 30;
        prizeWidth = 30;

        ballSpeed = 20;

        repaint();
        revalidate();
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.requestFocus();
    }

    public void intersect() {
        int playerOneX = playerOneBigCircleX + 42;
        int playerOneY = playerOneBigCircleY + 42;
        int playerTwoX = playerTwoBigCircleX + 42;
        int playerTwoY = playerTwoBigCircleY + 42;
        int ballCenterX = ballX + 27;
        int ballCenterY = ballY + 27;

        int playerOneIntersect = (int) Math.sqrt((Math.pow(playerOneX - ballCenterX, 2)) + (Math.pow(playerOneY - ballCenterY, 2)));
        int playerTwoIntersect = (int) Math.sqrt((Math.pow(playerTwoX - ballCenterX, 2)) + (Math.pow(playerTwoY - ballCenterY, 2)));

        if (playerOneIntersect <= 80 && !playerOneHit && !ballThroughPlayerOne) {// playerOne hits the ball

            if (ballCenterY < playerOneY && ballCenterX >= playerOneX + 25) {// hit up right
                ballYVelocity = -(int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = (int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY < playerOneY && ballCenterX <= playerOneX - 25) {// hit up left
                ballYVelocity = -(int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = -(int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY < playerOneY && ballCenterX < playerOneX + 25 && ballCenterX > playerOneX - 25) {// hit up
                ballYVelocity = -ballSpeed;
                ballXVelocity = 0;
            }

            if (ballCenterY > playerOneY && ballCenterX <= playerOneX - 25) {// hit down left
                ballYVelocity = (int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = -(int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY > playerOneY && ballCenterX >= playerOneX + 25) {// hit down right
                ballYVelocity = (int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = (int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY > playerOneY && ballCenterX < playerOneX + 25 && ballCenterX > playerOneX - 25) {// hit down
                ballYVelocity = ballSpeed;
                ballXVelocity = 0;
            }

            if (ballCenterX < playerOneX && ballCenterY >= playerOneY - 25 && ballCenterY <= playerOneY + 25) {//hit left
                ballYVelocity = 0;
                ballXVelocity = -ballSpeed;
            }
            if (ballCenterX > playerOneX && ballCenterY >= playerOneY - 25 && ballCenterY <= playerOneY + 25) {//hit right

                ballYVelocity = 0;
                ballXVelocity = ballSpeed;
            }


            playerOneHit = true;
            playerTwoHit = false;
            playerTwoHitForPrizeOrGoal = false;
            playerOneHitForPrizeOrGoal = true;
            hitUpWall = false;
            hitLeftWall = false;
            hitRightWall = false;
            hitDownWall = false;
        }

        if (playerTwoIntersect <= 80 && !playerTwoHit && !ballThroughPlayerTwo) {// playerTwo hits the ball

            if (ballCenterY < playerTwoY && ballCenterX >= playerTwoX + 25) {// hit up right
                ballYVelocity = -(int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = (int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY < playerTwoY && ballCenterX <= playerTwoX - 25) {// hit up left
                ballYVelocity = -(int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = -(int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY < playerTwoY && ballCenterX < playerTwoX + 25 && ballCenterX > playerTwoX - 25) {// hit up
                ballYVelocity = -ballSpeed;
                ballXVelocity = 0;
            }

            if (ballCenterY > playerTwoY && ballCenterX <= playerTwoX - 25) {// hit down left
                ballYVelocity = (int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = -(int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY > playerTwoY && ballCenterX >= playerTwoX + 25) {// hit down right
                ballYVelocity = (int) (ballSpeed / Math.sqrt(2));
                ballXVelocity = (int) (ballSpeed / Math.sqrt(2));
            } else if (ballCenterY > playerTwoY && ballCenterX < playerTwoX + 25 && ballCenterX > playerTwoX - 25) {// hit down
                ballYVelocity = ballSpeed;
                ballXVelocity = 0;
            }

            if (ballCenterX < playerTwoX && ballCenterY >= playerTwoY - 25 && ballCenterY <= playerTwoY + 25) {//hit left
                ballYVelocity = 0;
                ballXVelocity = -ballSpeed;
            }
            if (ballCenterX > playerTwoX && ballCenterY >= playerTwoY - 25 && ballCenterY <= playerTwoY + 25) {//hit right

                ballYVelocity = 0;
                ballXVelocity = ballSpeed;
            }

            playerTwoHit = true;
            playerOneHit = false;
            playerTwoHitForPrizeOrGoal = true;
            playerOneHitForPrizeOrGoal = false;
            hitUpWall = false;
            hitLeftWall = false;
            hitRightWall = false;
            hitDownWall = false;
        }

        if (ballY <= 900 && ballY >= -100) {
            ballY = ballY + ballYVelocity;
        }
        if (ballX <= 700 && ballX >= 0) {
            ballX = ballX + ballXVelocity;
        }
        if (ballX >= 568 && !hitRightWall) {// hit wall
            if (playerOneCatchMirrorWall || playerTwoCatchMirrorWall) {
                ballX = 100;
            } else {
                ballXVelocity = -ballXVelocity;
            }

            playerOneHit = false;
            playerTwoHit = false;
            hitRightWall = true;
            hitLeftWall = false;
            hitDownWall = false;
            hitUpWall = false;
        }

        if (ballX <= 70 && !hitLeftWall) {// hit wall
            if (playerOneCatchMirrorWall || playerTwoCatchMirrorWall) {
                ballX = 530;
            } else {
                ballXVelocity = -ballXVelocity;
            }

            playerOneHit = false;
            playerTwoHit = false;
            hitLeftWall = true;
            hitRightWall = false;
            hitUpWall = false;
            hitDownWall = false;
        }

        if (ballY >= 850 && !hitDownWall && (ballX > lineRightPlayerOne || ballX < lineLeftPlayerOne)) {// hit wall
            ballYVelocity = -ballYVelocity;
            playerOneHit = false;
            playerTwoHit = false;
            hitDownWall = true;
            hitUpWall = false;
            hitLeftWall = false;
            hitRightWall = false;
        } else if (ballY >= 900 && (ballX <= lineRightPlayerOne || ballX >= lineLeftPlayerOne)) {// playerTwo scores
            playerTwoScore++;
            if (playerTwoScore >= MyFrame.gameGoal) {
                if (!MyFrame.twoMarginChoose && MyFrame.goalLimitChoose) {
                    MyFrame.isGameEnd = true;
                } else if (MyFrame.twoMarginChoose && MyFrame.goalLimitChoose){
                    if (playerTwoScore == playerOneScore + 2) {
                        MyFrame.isGameEnd = true;
                    }
                }
            }
            ballY = 600;
            ballX = 326;
            ballYVelocity = 0;
            ballXVelocity = 0;
            playerOneBigCircleX = 310;
            playerOneBigCircleY = 820;
            playerOneSmallCircleX = 333;
            playerOneSmallCircleY = 843;
            playerOneWhiteCircleX = 312;
            playerOneWhiteCircleY = 823;
            playerTwoBigCircleX = 310;
            playerTwoBigCircleY = 30;
            playerTwoSmallCircleX = 333;
            playerTwoSmallCircleY = 53;
            playerTwoWhiteCircleX = 312;
            playerTwoWhiteCircleY = 33;
            playerOneHit = false;
            playerTwoHit = false;
            playerOneHitForPrizeOrGoal = false;
            playerTwoHitForPrizeOrGoal = false;
            hitUpWall = false;
            hitLeftWall = false;
            hitRightWall = false;
            hitDownWall = false;
            isPrizeCatch = false;
            isPrizeBigger = false;
            ballThroughPlayerTwo = false;
            ballThroughPlayerOne = false;
            playerOneCatchFireBall = false;
            playerOneCatchBiggerLine = false;
            playerOneCatchMirrorWall = false;
            playerTwoCatchFireBall = false;
            playerTwoCatchBiggerLine = false;
            playerTwoCatchMirrorWall = false;
        }
        if (ballY <= 25 && !hitUpWall && (ballX > lineRightPlayerTwo || ballX < lineLeftPlayerTwo)) {// hit wall
            ballYVelocity = -ballYVelocity;
            playerOneHit = false;
            playerTwoHit = false;
            hitUpWall = true;
            hitDownWall = false;
            hitLeftWall = false;
            hitRightWall = false;
        } else if (ballY <= -50 && (ballX <= lineRightPlayerTwo || ballX >= lineLeftPlayerTwo)) {// playerOne scores
            playerOneScore++;
            if (playerOneScore >= MyFrame.gameGoal) {
                if (!MyFrame.twoMarginChoose && MyFrame.goalLimitChoose) {
                    MyFrame.isGameEnd = true;
                } else if (MyFrame.twoMarginChoose && MyFrame.goalLimitChoose){
                    if (playerOneScore == playerTwoScore + 2) {
                        MyFrame.isGameEnd = true;
                    }
                }
            }
            ballY = 200;
            ballX = 326;
            ballYVelocity = 0;
            ballXVelocity = 0;
            playerOneBigCircleX = 310;
            playerOneBigCircleY = 820;
            playerOneSmallCircleX = 333;
            playerOneSmallCircleY = 843;
            playerOneWhiteCircleX = 312;
            playerOneWhiteCircleY = 823;
            playerTwoBigCircleX = 310;
            playerTwoBigCircleY = 30;
            playerTwoSmallCircleX = 333;
            playerTwoSmallCircleY = 53;
            playerTwoWhiteCircleX = 312;
            playerTwoWhiteCircleY = 33;
            playerOneHit = false;
            playerTwoHit = false;
            playerOneHitForPrizeOrGoal = false;
            playerTwoHitForPrizeOrGoal = false;
            hitUpWall = false;
            hitLeftWall = false;
            hitRightWall = false;
            hitDownWall = false;
            isPrizeCatch = false;
            isPrizeBigger = false;
            ballThroughPlayerTwo = false;
            ballThroughPlayerOne = false;
            playerOneCatchFireBall = false;
            playerOneCatchBiggerLine = false;
            playerOneCatchMirrorWall = false;
            playerTwoCatchFireBall = false;
            playerTwoCatchBiggerLine = false;
            playerTwoCatchMirrorWall = false;
        }
    }

    public void intersectPrize() {// player get prize
        if (isPrizeBigger) {
            prizeWidth = prizeWidth * 2;
            prizeHeight = prizeHeight * 2;
        }
        int ballCenterX = ballX + 27;
        int ballCenterY = ballY + 27;
        int prizeCenterX = xPrize + (prizeWidth / 2);
        int prizeCenterY = yPrize + (prizeHeight / 2);
        int ballAndPrizeIntersect = (int) Math.sqrt((Math.pow(prizeCenterX - ballCenterX, 2)) + (Math.pow(prizeCenterY - ballCenterY, 2)));
        if (!isPrizeCatch && ballAndPrizeIntersect <= 50) {
            if (playerOneHitForPrizeOrGoal) {
                if (activePrize == 1) {
                    playerOneCatchFireBall = true;
                } else if (activePrize == 2) {
                    playerOneCatchBiggerLine = true;
                } else if (activePrize == 3) {
                    playerOneCatchMirrorWall = true;
                }

            } else if (playerTwoHitForPrizeOrGoal) {
                if (activePrize == 1) {
                    playerTwoCatchFireBall = true;
                } else if (activePrize == 2) {
                    playerTwoCatchBiggerLine = true;
                } else if (activePrize == 3) {
                    playerTwoCatchMirrorWall = true;
                }
            }
            isPrizeCatch = true;
            isPrizeVisible = false;
            Timer.secondForPrize = 0;
            activePrize = 0;
        }
    }

    public void refreshEveryThing() {

        upPlayerOne = false;
        downPlayerOne = false;
        leftPlayerOne = false;
        rightPlayerOne = false;
        upPlayerTwo = false;
        downPlayerTwo = false;
        leftPlayerTwo = false;
        rightPlayerTwo = false;

        hitUpWall = false;
        hitDownWall = false;
        hitLeftWall = false;
        hitRightWall = false;

        playerOneHit = false;
        playerTwoHit = false;
        playerOneHitForPrizeOrGoal = false;
        playerTwoHitForPrizeOrGoal = false;
        ballThroughPlayerOne = false;
        ballThroughPlayerTwo = false;

        biggerLine = false;
        fireBall = false;
        mirrorWall = false;
        playerOneCatchFireBall = false;
        playerOneCatchBiggerLine = false;
        playerOneCatchMirrorWall = false;
        playerTwoCatchFireBall = false;
        playerTwoCatchBiggerLine = false;
        playerTwoCatchMirrorWall = false;
        isPrizeCatch = false;
        isPrizeVisible = false;
        isPrizeBigger = false;
        activePrize = 0;// 1=fireball 2=biggerLine 3=mirrorWall

        playerOneBigCircleX = 310;
        playerOneBigCircleY = 820;
        playerOneSmallCircleX = 333;
        playerOneSmallCircleY = 843;
        playerOneWhiteCircleX = 312;
        playerOneWhiteCircleY = 823;
        playerTwoBigCircleX = 310;
        playerTwoBigCircleY = 30;
        playerTwoSmallCircleX = 333;
        playerTwoSmallCircleY = 53;
        playerTwoWhiteCircleX = 312;
        playerTwoWhiteCircleY = 33;
        ballX = 326;
        ballY = 420;
        ballSpeed = 20;

        lineLeftPlayerOne = 215;
        lineRightPlayerOne = 420;
        lineLeftPlayerTwo = 215;
        lineRightPlayerTwo = 430;

        playerOneScore = 0;
        playerTwoScore = 0;

        prizeWidth = 30;
        prizeHeight = 30;

        MyFrame.timeLimitChoose = false;
        MyFrame.twoMarginChoose = false;
        MyFrame.isPause = false;
        MyFrame.isGameEnd = false;
        MyFrame.finishTheGame = false;
        MyFrame.goalLimitChoose = false;
        MyFrame.playerTwoGamerTag = "";
        MyFrame.playerOneGamerTag = "";
        MyFrame.gameGoal = 0;
        MyFrame.gameTime = 0;
        MyFrame.playerOneColor = null;
        MyFrame.playerTwoColor = null;
    }

}
