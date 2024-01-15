package main;

import school.SchoolClass;
import school.SchoolGrade;

import javax.swing.*;

public class ClassView extends JPanel {
    private JPanel contentPane;
    private JList<SchoolClass> list;
    private JButton delete;
    private JButton edit;
    private JButton show;

    public ClassView() {
        add(contentPane);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> MainInterface.flushStudent());
        delete.setText("删除");
        edit.setText("编辑学生分数项");
        show.setText("详情");
        delete.addActionListener(e -> delete());
        edit.addActionListener(e -> edit());
        show.addActionListener(e -> showClass());
    }

    public void flush() {
        SchoolGrade grade = MainInterface.mainInterface.getGradeValue();
        if (grade != null) {
            list.setListData(grade.getSchoolClasses());
            delete.setEnabled(true);
            edit.setEnabled(true);
        } else {
            list.setListData(new SchoolClass[0]);
            delete.setEnabled(false);
            edit.setEnabled(false);
        }
    }

    public void showClass() {
        SchoolClass schoolClass = list.getSelectedValue();
        if (schoolClass != null) {
            new Show(schoolClass).setVisible(true);
        }
    }

    public void edit() {
        SchoolClass schoolClass = list.getSelectedValue();
        if (schoolClass != null) {
            new ScoreEdit(schoolClass).setVisible(true);
        }
        MainInterface.flushStudent();
    }

    public void delete() {
        SchoolClass schoolClass = list.getSelectedValue();
        if (schoolClass != null) {
            schoolClass.schoolGrade.removerSchoolClass(list.getSelectedValue());
        }
        MainInterface.flush();
    }

    public SchoolClass getValue() {
        return list.getSelectedValue();
    }
}
