package cn.com.wysha.school_scoring_system.main;

import cn.com.wysha.school_scoring_system.school.SchoolClass;
import cn.com.wysha.school_scoring_system.school.ScoreItem;
import cn.com.wysha.school_scoring_system.school.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ScoreEdit extends JDialog {
    private final Student[] students;
    private JPanel contentPane;
    private JLabel name;
    private JSpinner value;
    private JButton cancel;
    private JComboBox<ScoreItem> comboBox;
    private int now;

    public ScoreEdit(SchoolClass schoolClass) {
        for (ScoreItem scoreItem : schoolClass.getScoreItems()) {
            comboBox.addItem(scoreItem);
        }
        students = schoolClass.getStudents();
        now = 0;
        name.setText(students[now].toString());
        setContentPane(contentPane);
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2
        );
        setModal(true);
        cancel.addActionListener(e -> dispose());
        contentPane.registerKeyboardAction(e -> {
            now++;
            if (now == students.length) {
                dispose();
            } else {
                name.setText(students[now].toString());
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        comboBox.addActionListener(e -> value.setValue(students[now].getValue((ScoreItem) comboBox.getSelectedItem())));
        value.addChangeListener(e -> students[now].setValue((ScoreItem) comboBox.getSelectedItem(), (long) ((int) value.getValue())));
    }
}
