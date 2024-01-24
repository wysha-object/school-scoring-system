package wysha.school_scoring_system.school;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data implements Serializable {
    private static final Data data;
    private static final String FILE = "./schoolScoringSystemData.schoolScoringSystemData";

    static {
        if (new File(FILE).exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE))) {
                data = (Data) objectInputStream.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            data = new Data();
        }
    }

    protected final HashMap<Integer, School> schools = new HashMap<>();

    private Data() {
    }

    public static void write() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE))) {
            objectOutputStream.writeObject(data);
        } catch (Exception ignored) {
        }
    }

    public static void newSchool(Integer id, String name) {
        School rs = new School(name);
        data.schools.put(id, rs);
    }

    public static List<Integer> getID(School school) {
        List<Integer> keyList = new ArrayList<>();
        for (Integer key : data.schools.keySet()) {
            if (data.schools.get(key).equals(school)) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    public static School[] getSchools() {
        return data.schools.values().toArray(new School[0]);
    }

    public static void removeSchool(School school) {
        if (school != null) {
            data.schools.values().remove(school);
        }
    }
}
