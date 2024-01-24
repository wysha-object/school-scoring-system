package wysha.school_scoring_system.main;

public enum Model {
    SCHOOL("学校"), GRADE("年级"), CLASS("班级");
    final String name;

    Model(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
