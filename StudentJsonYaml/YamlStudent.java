package StudentJsonYaml;

import LessonByteBuffer.Student;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class YamlStudent {
    YamlFile yamlFile;
    public void writeYamlStudent(Student student){
        yamlFile.set("name", student.getName());
        yamlFile.set("sex", student.isSex());
        yamlFile.set("date", student.getBirthday().getTime());
        try {
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlStudent(String yamlFile) {
        this.yamlFile = new YamlFile(yamlFile);
    }

    public void writeAllYamlStudent(Student[] students){
        for (Student student: students) {
            yamlFile.set("name", student.getName());
            yamlFile.set("sex", student.isSex());
            yamlFile.set("date", student.getBirthday().getTime());
        }
        try {
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Student[] readAllYamlStudent(){
        Student student;
        Vector<Student> vector = new Vector<>();
        while ((student = readYamlStudent()) != null) {
            vector.add(student);
        }
        Student[] students = (Student[]) vector.toArray();
        return students;
    }

    public Student readYamlStudent() {
        try {
            yamlFile.load();
            String name = (String) yamlFile.get("name");
            boolean sex = (Boolean) yamlFile.get("sex");
            Date date = new Date((long) yamlFile.get("date"));
            Student student1 = new Student(name, sex, date);
            return student1;
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
