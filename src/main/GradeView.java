package main;

import school.School;
import school.SchoolGrade;

import javax.swing.*;

public class GradeView extends JPanel {
    private JPanel contentPane;
    private JList<SchoolGrade> list;
    private JButton delete;

    public GradeView() {
        add(contentPane);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> MainInterface.flushClass());
        delete.setText("删除");
        delete.addActionListener(e -> delete());
    }

    public void flush() {
        School school = MainInterface.mainInterface.getSchoolValue();
        if (school != null) {
            list.setListData(school.getGrades());
            delete.setEnabled(true);
        } else {
            list.setListData(new SchoolGrade[0]);
            delete.setEnabled(false);
        }
    }

    public void delete() {
        SchoolGrade grade = list.getSelectedValue();
        if (grade != null) {
            grade.school.removeGrade(list.getSelectedValue());
        }
        MainInterface.flush();
    }

    public SchoolGrade getValue() {
        return list.getSelectedValue();
    }

}
