package main;

public enum Model {
    SCHOOL("新建一个学校"), GRADE("新建一个年级"), CLASS("新建一个班级");
    final String name;

    Model(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
