package cn.com.wysha.school_scoring_system.main;

import cn.com.wysha.school_scoring_system.school.SchoolClass;
import cn.com.wysha.school_scoring_system.school.Student;

import javax.swing.*;

public class StudentView extends JPanel {
    private JPanel contentPane;
    private JList<Student> list;
    private JButton delete;
    private JButton add;

    public StudentView() {
        add(contentPane);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add.setText("新建");
        delete.setText("删除");
        delete.addActionListener(e -> delete());
        add.addActionListener(e -> new StudentEdit(MainInterface.mainInterface.getClassValue()).setVisible(true));
    }

    public void flush() {
        SchoolClass classValue = MainInterface.mainInterface.getClassValue();
        if (classValue != null) {
            list.setListData(classValue.getStudents());
            add.setEnabled(true);
            delete.setEnabled(true);
        } else {
            list.setListData(new Student[0]);
            add.setEnabled(false);
            delete.setEnabled(false);
        }
    }

    public void delete() {
        Student schoolClass = list.getSelectedValue();
        if (schoolClass != null) {
            schoolClass.schoolClass.removeStudent(list.getSelectedValue());
        }
        MainInterface.flushStudent();
    }
}
