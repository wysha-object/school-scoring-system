package wysha.school_scoring_system.school;

import wysha.school_scoring_system.main.Model;

import javax.swing.*;
import java.awt.*;

public class AddScore extends JDialog {
    private JPanel contentPane;
    private JButton ok;
    private JButton cancel;
    private JComboBox<School> school;
    private JComboBox<SchoolGrade> grade;
    private JComboBox<Model> model;
    private JTextArea setName;
    private JComboBox<SchoolClass> classes;

    public AddScore() {
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
                        school.setEnabled(true);
                        grade.setEnabled(false);
                        classes.setEnabled(false);
                        setName.setEnabled(true);
                    }
                    case GRADE -> {
                        school.setEnabled(true);
                        grade.setEnabled(true);
                        classes.setEnabled(false);
                        setName.setEnabled(true);
                    }
                    case CLASS -> {
                        school.setEnabled(true);
                        grade.setEnabled(true);
                        classes.setEnabled(true);
                        setName.setEnabled(true);
                    }
                }
            }
        });
        ok.addActionListener(e -> {
            Model mod = (Model) model.getSelectedItem();
            if (mod != null) {
                dispose();
                switch (mod) {
                    case SCHOOL -> {
                        School sch = (School) school.getSelectedItem();
                        if (sch != null) {
                            sch.addScoreItem(new ScoreItem(setName.getText()));
                        }
                    }
                    case GRADE -> {
                        SchoolGrade gra = (SchoolGrade) grade.getSelectedItem();
                        if (gra != null) {
                            gra.addScoreItem(new ScoreItem(setName.getText()));
                        }
                    }
                    case CLASS -> {
                        SchoolClass schoolClass = (SchoolClass) classes.getSelectedItem();
                        if (schoolClass != null) {
                            schoolClass.addScoreItem(new ScoreItem(setName.getText()));
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
        grade.addActionListener(e -> {
            classes.removeAllItems();
            SchoolGrade sch = (SchoolGrade) grade.getSelectedItem();
            if (sch != null) {
                for (SchoolClass g : sch.getSchoolClasses()) {
                    classes.addItem(g);
                }
            }
        });
        if (Data.getSchools().length > 0) {
            school.setSelectedIndex(0);
        }
    }
}