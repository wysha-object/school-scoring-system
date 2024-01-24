package wysha.school_scoring_system.main;

import wysha.school_scoring_system.school.Data;
import wysha.school_scoring_system.school.School;
import wysha.school_scoring_system.school.SchoolGrade;

import javax.swing.*;
import java.awt.*;

public class Edit extends JDialog {
    private JPanel contentPane;
    private JButton ok;
    private JButton cancel;
    private JComboBox<School> school;
    private JComboBox<SchoolGrade> grade;
    private JSpinner setID;
    private JComboBox<Model> model;
    private JTextArea setName;

    public Edit() {
        setContentPane(contentPane);
        model.addItem(Model.SCHOOL);
        model.addItem(Model.GRADE);
        model.addItem(Model.CLASS);
        setUndecorated(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2
        );
        setAlwaysOnTop(true);
        setModal(true);
        model.addActionListener(e -> {
            Model mod = (Model) model.getSelectedItem();
            if (mod != null) {
                switch (mod) {
                    case SCHOOL -> {
                        school.setEnabled(false);
                        grade.setEnabled(false);
                        setName.setEnabled(true);
                        setID.setEnabled(true);
                    }
                    case GRADE -> {
                        school.setEnabled(true);
                        grade.setEnabled(false);
                        setName.setEnabled(false);
                        setID.setEnabled(true);
                    }
                    case CLASS -> {
                        school.setEnabled(true);
                        grade.setEnabled(true);
                        setName.setEnabled(false);
                        setID.setEnabled(true);
                    }
                }
            }
        });
        ok.addActionListener(e -> {
            Model mod = (Model) model.getSelectedItem();
            if (mod != null) {
                dispose();
                switch (mod) {
                    case SCHOOL -> Data.newSchool((Integer) setID.getValue(), setName.getText());
                    case GRADE -> {
                        School sch = (School) school.getSelectedItem();
                        if (sch != null) {
                            sch.addGrade((Integer) setID.getValue());
                        }
                    }
                    case CLASS -> {
                        SchoolGrade gra = (SchoolGrade) grade.getSelectedItem();
                        if (gra != null) {
                            gra.addSchoolClass((Integer) setID.getValue());
                        }
                    }
                }
            }
        });
        for (School s : Data.getSchools()) {
            school.addItem(s);
        }
        cancel.addActionListener(e -> dispose());
        model.setSelectedIndex(0);
        school.addActionListener(e -> {
            grade.removeAllItems();
            School sch = (School) school.getSelectedItem();
            if (sch != null) {
                SchoolGrade[] schoolGrades = sch.getGrades();
                for (SchoolGrade g : schoolGrades) {
                    grade.addItem(g);
                }
                if (schoolGrades.length > 0) {
                    grade.setSelectedIndex(0);
                }
            }
        });
        if (Data.getSchools().length > 0) {
            school.setSelectedIndex(0);
        }
    }
}