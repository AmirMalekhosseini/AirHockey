import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBoxFrame extends JFrame implements ActionListener {

    JCheckBox checkBoxGoalLimited;
    JCheckBox checkBox2Margin;
    JCheckBox checkBoxTimeLimited;
    JButton ok = new JButton("OK");

    CheckBoxFrame(){
        checkBoxGoalLimited = new JCheckBox();
        checkBox2Margin = new JCheckBox();
        checkBoxTimeLimited = new JCheckBox();
        this.setSize(400, 400);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        checkBoxGoalLimited.setText("Goal limit");
        checkBoxGoalLimited.setFocusable(false);
        checkBoxGoalLimited.setBounds(120,10,150,80);
        Font f = new Font("Comic Sans MS", Font.BOLD, 25);
        checkBoxGoalLimited.setFont(f);

        checkBox2Margin.setText("Two margin");
        checkBox2Margin.setFocusable(false);
        checkBox2Margin.setBounds(120, 80, 120, 50);
        Font f1 = new Font("Comic Sans MS", Font.BOLD, 15);
        checkBox2Margin.setFont(f1);

        checkBoxTimeLimited.setText("Time limit");
        checkBoxTimeLimited.setFocusable(false);
        checkBoxTimeLimited.setBounds(120,130,150,80);
        checkBoxTimeLimited.setFont(f);

        ok.setBounds(170,290,60,30);
        ok.setBackground(Color.BLACK);
        ok.setForeground(Color.white);
        ok.setFocusable(false);
        ok.addActionListener(this);

        this.add(checkBoxGoalLimited);
        this.add(checkBoxTimeLimited);
        this.add(checkBox2Margin);
        this.add(ok);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ok) {
            if (checkBox2Margin.isSelected() && checkBoxGoalLimited.isSelected()) {
                MyFrame.goalLimitChoose = true;
                MyFrame.twoMarginChoose = true;
                this.dispose();
            }
            if (checkBoxGoalLimited.isSelected()) {
                MyFrame.goalLimitChoose = true;
                this.dispose();
            }
            if (checkBoxTimeLimited.isSelected()) {
                MyFrame.timeLimitChoose = true;
                this.dispose();
            }
            MyFrame myFrame = new MyFrame();
        }

    }
}
