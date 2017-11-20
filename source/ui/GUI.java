package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import model.Model;

/**
 *
 * @author xjurp20
 */
public class GUI {

    private Model model;
    private JFrame frame;
    private JPanel panel;
    private JPanel panelMid;
    private JPanel panelScore;
    private JLabel lblAnimal;
    private JLabel lblCorrect;
    private JLabel lblWrong;
    private boolean isScore; //true if score's dashboard is displayed

    private class BtnClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                JButton btn = (JButton) e.getSource();
                String animal = btn.getIcon().toString().toLowerCase();
                if (model.guessAnimal(animal)) {
                    JLabel lbl = new JLabel("Yes, I'm " + animal + "!",
                            JLabel.CENTER);
                    JOptionPane.showMessageDialog(frame,
                            lbl, "",
                            JOptionPane.PLAIN_MESSAGE);
                    lblCorrect.setText("" + model.guessedRight());
                    lblAnimal.setText(model.getWantedAnimal());
                }
                lblWrong.setText("" + model.guessedWrong());
            }
        }
    }

    private class MidPanelDblClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                panelMid.removeAll();
                if (isScore) {
                    panelMid.add(lblAnimal);
                    isScore = false;
                } else {
                    panelMid.add(panelScore);
                    isScore = true;
                }
                panelMid.revalidate();
                panelMid.repaint();
            }
        }
    }

    public GUI(Model model) {
        this.model = model;
        init();
    }

    private void init() {
        isScore = false;
        //Create and set up the window
        frame = new JFrame("Where's the animal?");
        panel = new JPanel(new GridLayout(3, 3, 6, 6));
        panelMid = new JPanel(new GridLayout(1, 1, 0, 0));
        panelScore = new JPanel(new GridLayout(2, 1, 0, 0));
        lblAnimal = new JLabel(model.getWantedAnimal(), JLabel.CENTER);
        lblCorrect = new JLabel("" + model.guessedRight(), JLabel.CENTER);
        lblWrong = new JLabel("" + model.guessedWrong(), JLabel.CENTER);

        lblCorrect.setBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(1,1,1,1),
                "CORRECT",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        lblWrong.setBorder(
                BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(1,1,1,1),
                "WRONG",
                TitledBorder.CENTER,
                TitledBorder.TOP));
        panelScore.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        panelScore.add(lblCorrect);
        panelScore.add(lblWrong);
        panelScore.addMouseListener(new MidPanelDblClick());

        lblAnimal.setFont(new Font(Font.MONOSPACED, Font.BOLD, 27));
        panelMid.setBackground(Color.white);
        panelMid.add(lblAnimal);
        panelMid.addMouseListener(new MidPanelDblClick());

        for (ImageIcon img : model.getAnimalsImg()) {
            ImageIcon newImg = new ImageIcon(
                    img.getImage().getScaledInstance(175, 175,
                    Image.SCALE_SMOOTH), img.toString());

            JButton btn = new JButton(newImg);
            btn.setFocusable(false);
            btn.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
            btn.addActionListener(new BtnClick());
            panel.add(btn);
        }

        if (panel.getComponentCount() > 4) {
            panel.add(panelMid, 4);
        } else {
            panel.add(panelMid);
        }
        panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        panel.setBackground(Color.white);

        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void show() {
        frame.setVisible(true);
    }

    public void close() {
        System.exit(0);
    }
}
