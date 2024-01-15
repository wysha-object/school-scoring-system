package school;

import main.Model;

import javax.swing.*;
import java.awt.*;

public class RemoveScore extends JDialog {
    private JPanel contentPane;
    private JButton ok;
    private JButton cancel;
    private JComboBox<School> school;
    private JComboBox<SchoolGrade> grade;
    private JComboBox<Model> model;
    private JComboBox<SchoolClass> classes;
    private JComboBox<ScoreItem> scores;

    public RemoveScore() {
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
                    }
                    case GRADE -> {
                        school.setEnabled(true);
                        grade.setEnabled(true);
                        classes.setEnabled(false);
                    }
                    case CLASS -> {
                        school.setEnabled(true);
                        grade.setEnabled(true);
                        classes.setEnabled(true);
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
                            sch.removeScoreItem((ScoreItem) scores.getSelectedItem());
                        }
                    }
                    case GRADE -> {
                        SchoolGrade gra = (SchoolGrade) grade.getSelectedItem();
                        if (gra != null) {
                            gra.removeScoreItem((ScoreItem) scores.getSelectedItem());
                        }
                    }
                    case CLASS -> {
                        SchoolClass schoolClass = (SchoolClass) classes.getSelectedItem();
                        if (schoolClass != null) {
                            schoolClass.removeScoreItem((ScoreItem) scores.getSelectedItem());
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
                SchoolClass[] schoolClasses = sch.getSchoolClasses();
                for (SchoolClass g : schoolClasses) {
                    classes.addItem(g);
                }
                if (schoolClasses.length > 0) {
                    classes.setSelectedIndex(0);
                }
            }
        });
        school.addActionListener(e -> {
            scores.removeAllItems();
            School sch = (School) school.getSelectedItem();
            if (sch != null) {
                for (ScoreItem g : sch.getScoreItems()) {
                    scores.addItem(g);
                }
            }
        });
        grade.addActionListener(e -> {
            scores.removeAllItems();
            SchoolGrade sch = (SchoolGrade) grade.getSelectedItem();
            if (sch != null) {
                for (ScoreItem g : sch.getScoreItems()) {
                    scores.addItem(g);
                }
            }
        });
        classes.addActionListener(e -> {
            scores.removeAllItems();
            SchoolClass sch = (SchoolClass) classes.getSelectedItem();
            if (sch != null) {
                for (ScoreItem g : sch.getScoreItems()) {
                    scores.addItem(g);
                }
            }
        });
        if (Data.getSchools().length > 0) {
            school.setSelectedIndex(0);
        }
    }
}