package cn.com.wysha.school_scoring_system.school;

import java.io.Serializable;
import java.util.*;

public class School implements Serializable {
    public final String name;
    private final HashMap<Integer, SchoolGrade> grades = new HashMap<>();

    protected School(String name) {
        this.name = name;
    }

    public void addScoreItem(ScoreItem scoreItem) {
        for (SchoolGrade schoolGrade : grades.values()) {
            schoolGrade.addScoreItem(scoreItem);
        }
    }

    protected void removeScoreItem(ScoreItem scoreItem) {
        for (SchoolGrade schoolGrade : grades.values()) {
            schoolGrade.removeScoreItem(scoreItem);
        }
    }

    public ScoreItem[] getScoreItems() {
        boolean first = true;
        HashSet<ScoreItem> scoreItems = new HashSet<>();
        for (SchoolGrade schoolGrade : grades.values()) {
            ScoreItem[] items = schoolGrade.getScoreItems();
            if (first) {
                scoreItems.addAll(Arrays.asList(items));
                first = false;
                continue;
            }
            for (ScoreItem scoreItem : scoreItems) {
                boolean b = true;
                for (ScoreItem item : items) {
                    if (scoreItem.equals(item)) {
                        b = false;
                        break;
                    }
                }
                if (b) {
                    scoreItems.remove(scoreItem);
                }
            }
        }
        return scoreItems.toArray(new ScoreItem[0]);
    }

    public void addGrade(Integer integer) {
        SchoolGrade rs = new SchoolGrade(this);
        grades.put(integer, rs);
    }

    public void removeGrade(SchoolGrade schoolGrade) {
        if (schoolGrade != null) {
            grades.values().remove(schoolGrade);
        }
    }

    public SchoolGrade[] getGrades() {
        return grades.values().toArray(new SchoolGrade[0]);
    }

    public List<Integer> getID(SchoolGrade schoolGrade) {
        List<Integer> keyList = new ArrayList<>();
        for (Integer key : grades.keySet()) {
            if (grades.get(key).equals(schoolGrade)) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    @Override
    public String toString() {
        return "学校:" + name + "         ID:" + Data.getID(this) + "          年级数" + grades.values().size();
    }
}
