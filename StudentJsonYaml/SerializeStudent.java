package StudentJsonYaml;

import LessonByteBuffer.Student;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

public class SerializeStudent {

    public void writeJsonStudent(Student student, String file, boolean appendToFile){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", student.getName());
        jsonObject.put("sex", student.isSex());
        jsonObject.put("date", student.getBirthday().getTime());
        //System.out.print(jsonObject.toJSONString());
        try (FileWriter fileWriter = new FileWriter(file, appendToFile)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAllJsonStudent(Collection students, String file, boolean appendToFile){
        JSONArray jsonArray = new JSONArray();
        Iterator iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", student.getName());
            jsonObject.put("sex", student.isSex());
            jsonObject.put("date", student.getBirthday().getTime());
            jsonArray.add(jsonObject);
        }
        try (FileWriter fileWriter = new FileWriter(file, appendToFile)) {
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Collection<Student> readAllJsonStudent(String file){
        Student student;
        Collection<Student> collection = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject studentJSON = (JSONObject) jsonParser.parse(new FileReader(file));
            while ((student = readJsonStudent(file)) != null) {
                Student student1 = new Student((String) studentJSON.get("name"), (boolean) studentJSON.get("sex"), new Date((long) studentJSON.get("date")));
                collection.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collection;
    }

    public Student readJsonStudent(String file) {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject studentJSON = (JSONObject) jsonParser.parse(new FileReader(file));
            Student student1 = new Student((String) studentJSON.get("name"), (boolean) studentJSON.get("sex"), new Date((long) studentJSON.get("date")));
            return student1;
        } catch (Exception e) {
            return null;
        }
    }
}
