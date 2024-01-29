package cn.com.wysha.school_scoring_system.main;

import cn.com.wysha.school_scoring_system.school.SchoolClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class StudentEdit extends JDialog {
    private JPanel contentPane;
    private JTextField textField;
    private JLabel label;
    private JButton ok;
    private JButton cancel;
    private JSpinner spinner;
    private JLabel id;

    public StudentEdit(SchoolClass schoolClass) {
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2
        );
        setModal(true);
        setContentPane(contentPane);
        ok.addActionListener(e -> {
            dispose();
            schoolClass.addStudent((Integer) spinner.getValue(), textField.getText());
            MainInterface.flushClass();
        });
        cancel.addActionListener(e -> {
            dispose();
            MainInterface.flushClass();
        });
        contentPane.registerKeyboardAction(e -> {
            schoolClass.addStudent((Integer) spinner.getValue(), textField.getText());
            spinner.setValue((Integer) spinner.getValue() + 1);
            textField.setText("");
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

}
