package main;

import school.*;
import view.Choose;

import javax.swing.*;
import java.awt.*;


/**
 * @author wysha
 */
public class MainInterface extends JFrame {
    public static MainInterface mainInterface;

    static {
        new MainInterface();
    }

    private JPanel contentPane;
    private JLabel title;
    private JPanel menubar;
    private JButton add;
    private JButton exit;
    private SchoolView schoolView;
    private GradeView gradeView;
    private ClassView classView;
    private StudentView studentView;
    private JButton addScore;
    private JButton removeScore;
    private JLabel v;

    private MainInterface() {
        mainInterface = this;
        setUndecorated(true);
        setContentPane(contentPane);
        title.setText("学校计分系统");
        exit.setText("退出");
        add.setText("新建");
        v.setText(CuringConfiguration.VERSION);
        addScore.setText("增加分数项");
        removeScore.setText("删除分数项");
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height
        );
        exit.addActionListener(e -> {
            Choose choose = new Choose("确认退出?");
            choose.setVisible(true);
            if (choose.choose) {
                Data.write();
                System.exit(0);
            }
        });
        add.addActionListener(e -> {
            new Edit().setVisible(true);
            flush();
        });
        addScore.addActionListener(e -> new AddScore().setVisible(true));
        removeScore.addActionListener(e -> new RemoveScore().setVisible(true));
        flush();
    }

    public static void flush() {
        mainInterface.schoolView.flush();
        mainInterface.gradeView.flush();
        mainInterface.classView.flush();
        mainInterface.studentView.flush();
    }

    public static void flushGradeView() {
        mainInterface.gradeView.flush();
        mainInterface.classView.flush();
        mainInterface.studentView.flush();
    }

    public static void flushClass() {
        mainInterface.classView.flush();
        mainInterface.studentView.flush();
    }

    public static void flushStudent() {
        mainInterface.studentView.flush();
    }

    public School getSchoolValue() {
        return schoolView.getValue();
    }

    public SchoolGrade getGradeValue() {
        return gradeView.getValue();
    }

    public SchoolClass getClassValue() {
        return classView.getValue();
    }
}
