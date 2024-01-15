package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author wysha
 */
public class Choose extends JDialog {
    public boolean choose;
    private JPanel contentPane;
    private JButton buttonOkay;
    private JButton buttonCancel;
    private JTextArea textArea;

    public Choose(String s) {
        setContentPane(contentPane);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setAlwaysOnTop(true);
        textArea.setText(s);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        setModal(true);
        getRootPane().setDefaultButton(buttonOkay);
        buttonOkay.addActionListener(e -> onOkay());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void onOkay() {
        choose = true;
        dispose();
    }

    private void onCancel() {
        choose = false;
        dispose();
    }
}
