package LessonByteBuffer;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private String name; //8 characters
    private boolean sex;
    private Date birthday;

    public Student(String name, boolean sex, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }
}
