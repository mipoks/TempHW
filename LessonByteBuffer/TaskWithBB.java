package LessonByteBuffer;

import LessonCollection.MyCollection;
import LessonCollection.MyEditableCollection;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

public class TaskWithBB {
    public MyEditableCollection<Student> read(File file) {
        MyEditableCollection<Student> students = new MyEditableCollection<>();
        try(FileInputStream inputStream = new FileInputStream(file)) {
        ByteBuffer bb = ByteBuffer.allocate(25);
        while (inputStream.available() != 0) {
            bb.clear();
            byte[] tmp = new byte[25];
            inputStream.read(tmp);
            bb.put(tmp);
            bb.rewind();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                sb.append(bb.getChar());
            }
            String name = new String(sb);
            boolean sex = (bb.get() == 1? true:false);
            Date date = new Date(bb.getLong());
            students.add(new Student(name, sex, date));
        }
        inputStream.close();
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void write(MyEditableCollection <Student> myEditableCollection, File file) {
        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            Iterator iterator = myEditableCollection.iterator();
            while (iterator.hasNext()) {
                Student student = (Student)iterator.next();
                String name = student.getName();
                ByteBuffer bb = ByteBuffer.allocate(25);
                for (int i = 0; i < name.length(); i++) {
                    bb.putChar(name.charAt(i));
                }
                bb.put((byte)((student.isSex() == true)?1:0));
                long time = student.getBirthday().getTime();
                bb.putLong(time);
                outputStream.write(bb.array());
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        MyEditableCollection<Student> students = new MyEditableCollection<>();
        students.add(new Student("Kakakaka", true, new Date(2001, 1, 11)));
        students.add(new Student("Mamapapa", false, new Date(1987, 8, 23)));
        File file = new File("students.sec");
        write(students, file);
        MyEditableCollection<Student> test = read(file);
        Iterator it = test.iterator();
        while (it.hasNext()) {
            Student tmp = (Student) it.next();
            System.out.println(tmp.getName() + " " + tmp.isSex() + " " + tmp.getBirthday());
        }
    }

    public void run2() {
        try(OutputStream out = new FileOutputStream("students.sec")) {
            StudentOutputStream sot = new StudentOutputStream(out);
            sot.writeStudent(new Student("Kakakaka", true, new Date(2001, 1, 11)));
            sot.writeStudent(new Student("Mamapapa", false, new Date(1987, 8, 23)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(InputStream in = new FileInputStream("students.sec")) {
            StudentInputStream sin = new StudentInputStream(in);
            Student student = sin.readStudent();
            System.out.println(student.getName() + " " + student.isSex() + " " + student.getBirthday());
            student = sin.readStudent();
            System.out.println(student.getName() + " " + student.isSex() + " " + student.getBirthday());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run3() {
        try(OutputStream out = new FileOutputStream("students.sec")) {
            StudentOOS sot = new StudentOOS(out);
            sot.writeStudent(new Student("Kakakaka", true, new Date(2001, 1, 11)));
            sot.writeStudent(new Student("Mamapapa", false, new Date(1987, 8, 23)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(InputStream in = new FileInputStream("students.sec")) {
            StudentOIS sin = new StudentOIS(in);
            Student student = sin.readStudent();
            System.out.println(student.getName() + " " + student.isSex() + " " + student.getBirthday());
            student = sin.readStudent();
            System.out.println(student.getName() + " " + student.isSex() + " " + student.getBirthday());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
