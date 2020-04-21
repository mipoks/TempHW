package LessonByteBuffer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class StudentOOS extends OutputStream {
    private ObjectOutputStream out = null;

    public StudentOOS(OutputStream out) throws IOException {
        this.out = new ObjectOutputStream(out);
    }

    public void writeStudent(Student student) throws IOException {
        out.writeObject(student);
    }
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}
