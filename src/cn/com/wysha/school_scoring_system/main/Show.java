package cn.com.wysha.school_scoring_system.main;

import cn.com.wysha.school_scoring_system.school.SchoolClass;
import cn.com.wysha.school_scoring_system.school.ScoreItem;
import cn.com.wysha.school_scoring_system.school.Student;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Show extends JDialog {
    private JPanel contentPane;
    private JList<String> list;
    private JLabel name;
    private JLabel size;
    private JButton exit;

    public Show(SchoolClass schoolClass) {
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height
        );
        setModal(true);
        setContentPane(contentPane);
        name.setText(schoolClass.toString());
        size.setText("人数:" + schoolClass.getStudents().length);
        ScoreItem[] scoreItems = schoolClass.getScoreItems();
        String[] strings = new String[scoreItems.length];
        for (int i = 0; i < scoreItems.length; i++) {
            ScoreItem scoreItem = scoreItems[i];
            StringBuilder stringBuilder = new StringBuilder(scoreItem.toString());
            Student[] students = schoolClass.getStudents();
            long[] scores = new long[students.length];
            for (int j = 0; j < students.length; j++) {
                scores[j] = students[j].getValue(scoreItem);
            }
            long sum = 0;
            for (long student : scores) {
                sum += student;
            }
            stringBuilder.append("  班级总分:").append(sum);
            double a = (double) (sum) / scores.length;
            stringBuilder.append("  平均分:").append(a);
            for (int k = 0; k < scores.length; k++) {
                for (int j = 0; j < scores.length - 1; j++) {
                    if (scores[j] > scores[j + 1]) {
                        long l = scores[j];
                        scores[j] = scores[j + 1];
                        scores[j + 1] = l;
                    }
                }
            }
            double m;
            if (scores.length % 2 == 0) {
                m = (((double) scores[scores.length / 2 - 1]) + scores[scores.length / 2]) / 2;
            } else {
                m = scores[(scores.length - 1) / 2];
            }
            stringBuilder.append("  中位数").append(m);
            ArrayList<Student> min = new ArrayList<>();
            ArrayList<Student> max = new ArrayList<>();
            for (Student student : students) {
                long n = student.getValue(scoreItem);
                if (n == scores[0]) {
                    min.add(student);
                } else if (n == scores[scores.length - 1]) {
                    max.add(student);
                }
            }
            stringBuilder.append("  最低分:").append(min).append("  最高分").append(max);
            strings[i] = stringBuilder.toString();
        }
        list.setListData(strings);
        exit.addActionListener(e -> dispose());
    }
}
