package cn.com.wysha.school_scoring_system.main;

import cn.com.wysha.school_scoring_system.school.School;
import cn.com.wysha.school_scoring_system.school.Data;

import javax.swing.*;

public class SchoolView extends JPanel {
    private JPanel contentPane;
    private JList<School> list;
    private JButton delete;

    public SchoolView() {
        add(contentPane);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e -> MainInterface.flushGradeView());
        delete.setText("删除");
        delete.addActionListener(e -> delete());
    }

    public void flush() {
        list.setListData(Data.getSchools());
    }

    public void delete() {
        Data.removeSchool(list.getSelectedValue());
        MainInterface.flush();
    }

    public School getValue() {
        return list.getSelectedValue();
    }

}
