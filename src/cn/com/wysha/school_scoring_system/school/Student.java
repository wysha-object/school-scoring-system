package cn.com.wysha.school_scoring_system.school;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
    public final SchoolClass schoolClass;
    private final String name;
    private final HashMap<ScoreItem, Long> values = new HashMap<>();

    public Student(SchoolClass schoolClass, String name) {
        this.schoolClass = schoolClass;
        this.name = name;
    }

    public void setValue(ScoreItem scoreItem, Long score) {
        boolean b = true;
        for (ScoreItem sco : values.keySet()) {
            if (sco.equals(scoreItem)) {
                b = false;
                break;
            }
        }
        if (b) {
            return;
        }
        values.put(scoreItem, score);
    }

    protected void addValue(ScoreItem scoreItem) {
        values.put(scoreItem, 0L);
    }

    protected void removeValue(ScoreItem scoreItem) {
        values.remove(scoreItem);
    }

    public long getValue(ScoreItem scoreItem) {
        return values.get(scoreItem);
    }

    @Override
    public String toString() {
        return "姓名:" + name;
    }
}
