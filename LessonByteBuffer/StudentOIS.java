package LessonByteBuffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class StudentOIS extends InputStream {
    private ObjectInputStream in = null;

    public StudentOIS(InputStream in) throws IOException {
        this.in = new ObjectInputStream(in);
    }

    public Student readStudent() throws IOException, ClassNotFoundException {
        Student student = (Student) in.readObject();
        return student;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return in.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return in.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return in.skip(n);
    }

    @Override
    public int available() throws IOException {
        return in.available();
    }

    @Override
    public void close() throws IOException {
        in.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        in.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        in.reset();
    }

    @Override
    public boolean markSupported() {
        return in.markSupported();
    }
}
