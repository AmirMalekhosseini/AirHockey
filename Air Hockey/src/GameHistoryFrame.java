import javax.swing.*;
import java.awt.*;

public class GameHistoryFrame extends JFrame {
    private JTextArea historyTextArea;

    public GameHistoryFrame() {
        setTitle("Game History");
        this.setSize(850, 965);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane();
        historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);

        // Set Text Area:
        scrollPane.setViewportView(historyTextArea);

        getContentPane().add(scrollPane);

        for (int i = 0; i < MyProject.gameHistory.size(); i++) {
            addGameResult(MyProject.gameHistory.get(i));
        }

    }

    public void addGameResult(GameHistory gameHistory) {
        String gameResult = gameHistory.getGamerTagPlayerOne() + " \"" + gameHistory.playerOneScore + "\" - " + gameHistory.playerTwoScore + " \"" + gameHistory.playerTwoScore + "\"\n";
        historyTextArea.append(gameResult);
    }

}
