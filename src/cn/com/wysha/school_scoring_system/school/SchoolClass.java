package cn.com.wysha.school_scoring_system.school;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class SchoolClass implements Serializable {
    public final SchoolGrade schoolGrade;
    private final HashSet<ScoreItem> scoreItems = new HashSet<>();
    private final HashMap<Integer, Student> students = new HashMap<>();

    protected SchoolClass(SchoolGrade schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public ScoreItem[] getScoreItems() {
        return scoreItems.toArray(new ScoreItem[0]);
    }

    public void addScoreItem(ScoreItem scoreItem) {
        scoreItems.add(scoreItem);
        for (Student student : students.values()) {
            student.addValue(scoreItem);
        }
    }

    protected void removeScoreItem(ScoreItem scoreItem) {
        scoreItems.remove(scoreItem);
        for (Student student : students.values()) {
            student.removeValue(scoreItem);
        }
    }

    public void addStudent(Integer integer, String name) {
        Student rs = new Student(this, name);
        students.put(integer, rs);
        for (ScoreItem scoreItem : scoreItems) {
            rs.addValue(scoreItem);
        }
    }

    public Student[] getStudents() {
        return students.values().toArray(new Student[0]);
    }

    public void removeStudent(Student student) {
        if (student != null) {
            students.values().remove(student);
        }
    }

    @Override
    public String toString() {
        return schoolGrade.school.getID(schoolGrade) + "届" + schoolGrade.getID(this) + "班";
    }
}
