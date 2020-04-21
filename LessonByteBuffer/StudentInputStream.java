package LessonByteBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Date;

public class StudentInputStream extends InputStream {
    private DataInputStream in;

    public StudentInputStream(InputStream in) {
        this.in = new DataInputStream(in);
    }

    public Student readStudent() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(25);
        bb.clear();
        byte[] tmp = new byte[25];
        in.read(tmp);
        bb.put(tmp);
        bb.rewind();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(bb.getChar());
        }
        String name = new String(sb);
        boolean sex = (bb.get() == 1 ? true : false);
        Date date = new Date(bb.getLong());
        return new Student(name, sex, date);
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
