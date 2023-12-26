import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JPanel {

    int time = MyFrame.gameTime * 60;
    int minute;
    int second;
    int secondCounter = 0;
    public static int secondForPrize = 0;

    public Timer(Screen screen) {

        this.setBounds(700, 400, 120, 100);
        javax.swing.Timer timer = new javax.swing.Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (time == 0 && MyFrame.timeLimitChoose) {
                    MyFrame.isGameEnd = true;
                }

                if (!MyFrame.isPause && !MyFrame.finishTheGame) {
                    secondCounter++;
                    if (secondCounter == 40) {
                        if (time != 0) {
                            minute = time / 60;
                            second = time - (minute * 60);
                            time--;
                        }
                        secondCounter = 0;
                        secondForPrize++;
                    }

                    screen.refresh();
                    repaint();
                }
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        super.paint(g2D);
        Font f = new Font("Comic Sans MS", Font.BOLD, 40);
        g2D.setFont(f);
        if (minute < 10 && second >= 10) {
            g2D.drawString("0" + minute + ":" + second, 5, 50);
        } else if (minute >= 10 && second < 10) {
            g2D.drawString(minute + ":" + "0" + second, 5, 50);
        } else if (minute < 10) {
            g2D.drawString("0" + minute + ":" + "0" + second, 5, 50);
        } else {
            g2D.drawString(minute + ":" + second, 5, 50);
        }
    }

}
