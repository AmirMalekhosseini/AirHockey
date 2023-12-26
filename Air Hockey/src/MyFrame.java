import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {


    public static Color playerOneColor;
    public static Color playerTwoColor;
    public static boolean goalLimitChoose;
    public static boolean timeLimitChoose;
    public static boolean twoMarginChoose;
    public static boolean isGameEnd;
    public static boolean isPause;
    public static boolean finishTheGame;
    public static int gameTime;
    public static int gameGoal;

    public static String playerOneGamerTag;
    public static String playerTwoGamerTag;
    JButton homeButton;
    JButton pauseButton;

    public MyFrame() {
        ImageIcon homeIcon = new ImageIcon("images.png");
        ImageIcon pauseIcon = new ImageIcon("pauseImage.png");
        ImageIcon logo = new ImageIcon("GameIcon.jpeg");
        GameHistory gameHistory = new GameHistory();

        if (timeLimitChoose) {
            gameTime = Integer.parseInt(JOptionPane.showInputDialog("enter game time"));
        }
        if (goalLimitChoose) {
            gameGoal = Integer.parseInt(JOptionPane.showInputDialog("enter game goal"));
        }

        playerOneGamerTag = JOptionPane.showInputDialog("enter playerOne gamerTag");
        playerTwoGamerTag = JOptionPane.showInputDialog("enter playerTwo gamerTag");



        Screen screen = new Screen();

        playerOneColor = JColorChooser.showDialog(null, "Choose playerOne's color", null);
        playerTwoColor = JColorChooser.showDialog(null, "Choose playerTwo's color", null);
        Timer timer = new Timer(screen);
        homeButton = new JButton();
        homeButton.setIcon(homeIcon);
        homeButton.setBounds(700, 20, 50, 50);
        homeButton.addActionListener(this);
        pauseButton = new JButton();
        pauseButton.setIcon(pauseIcon);
        pauseButton.setBounds(770, 20, 50, 50);
        pauseButton.addActionListener(this);
        JLabel test = new JLabel();
        test.setOpaque(true);
        test.setBackground(Color.red);
        test.setBounds(228, 920, 20, 20);

        this.setSize(850, 965);
        this.setIconImage(logo.getImage());
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);


        this.add(homeButton);
        this.add(pauseButton);
//        this.add(test);
        this.add(screen);
        this.add(timer);

        gameHistory.setGamerTagPlayerOne(playerOneGamerTag);
        gameHistory.setGamerTagPlayerTwo(playerTwoGamerTag);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == pauseButton) {
            isPause = !isPause;
        }

        if (e.getSource() == homeButton) {// click homeButton
            this.dispose();
            MenuFrame newMenu = new MenuFrame();
        }

    }
}



