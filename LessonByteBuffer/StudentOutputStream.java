package LessonByteBuffer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class StudentOutputStream extends OutputStream {
    private DataOutputStream out;
    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public StudentOutputStream(OutputStream out) {
        this.out = new DataOutputStream(out);
    }

    public void writeStudent(Student student) throws IOException {
        String name = student.getName();
        ByteBuffer bb = ByteBuffer.allocate(25);
        for (int i = 0; i < name.length(); i++) {
            bb.putChar(name.charAt(i));
        }
        bb.put((byte)((student.isSex() == true)?1:0));
        long time = student.getBirthday().getTime();
        bb.putLong(time);
        out.write(bb.array());
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
