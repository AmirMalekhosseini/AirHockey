import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MenuFrame extends JFrame implements ActionListener {

    JLabel background;
    JButton newGame;
    JButton gameHistory;
    JButton exit;
    MenuFrame(){
        ImageIcon backgroundImage = new ImageIcon("background.jpeg");
        newGame = new JButton("New Game");
        gameHistory = new JButton("Game History");
        exit = new JButton("Exit");
        background = new JLabel(backgroundImage);

        background.setOpaque(true);
        background.setBounds(0, 0, 850, 965);
        newGame.setBounds(340,305,200,70);
        newGame.setBackground(Color.BLACK);
        newGame.setForeground(Color.white);
        newGame.setFocusable(false);
        gameHistory.setBounds(340,390,200,70);
        gameHistory.setBackground(Color.BLACK);
        gameHistory.setForeground(Color.white);
        gameHistory.setFocusable(false);
        exit.setBounds(340,475,200,70);
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.setFocusable(false);
        this.setSize(850, 965);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        newGame.addActionListener(this);
        gameHistory.addActionListener(this);
        exit.addActionListener(this);
        background.add(newGame);
        background.add(gameHistory);
        background.add(exit);
        this.add(background);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        if (e.getSource() == newGame) {
            this.dispose();
            CheckBoxFrame checkBoxFrame = new CheckBoxFrame();
        }

        if (e.getSource() == gameHistory) {
            this.dispose();
            GameHistoryFrame gameHistoryFrame = new GameHistoryFrame();
        }


    }
}
