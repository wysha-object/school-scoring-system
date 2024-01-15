package school;

import java.io.Serializable;
import java.util.*;

public class SchoolGrade implements Serializable {
    public final School school;
    private final HashMap<Integer, SchoolClass> classes = new HashMap<>();

    protected SchoolGrade(School school) {
        this.school = school;
    }

    public ScoreItem[] getScoreItems() {
        boolean first = true;
        HashSet<ScoreItem> scoreItems = new HashSet<>();
        for (SchoolClass schoolClass : classes.values()) {
            ScoreItem[] items = schoolClass.getScoreItems();
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

    public void addScoreItem(ScoreItem scoreItem) {
        for (SchoolClass schoolClass : classes.values()) {
            schoolClass.addScoreItem(scoreItem);
        }
    }

    public void addSchoolClass(Integer integer) {
        SchoolClass rs = new SchoolClass(this);
        classes.put(integer, rs);
    }

    public SchoolClass[] getSchoolClasses() {
        return classes.values().toArray(new SchoolClass[0]);
    }

    public void removerSchoolClass(SchoolClass schoolClass) {
        if (schoolClass != null) {
            classes.values().remove(schoolClass);
        }
    }

    public List<Integer> getID(SchoolClass schoolClass) {
        List<Integer> keyList = new ArrayList<>();
        for (Integer key : classes.keySet()) {
            if (classes.get(key).equals(schoolClass)) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    protected void removeScoreItem(ScoreItem scoreItem) {
        for (SchoolClass schoolClass : classes.values()) {
            schoolClass.removeScoreItem(scoreItem);
        }
    }

    @Override
    public String toString() {
        return school.name + school.getID(this) + "届";
    }
}
