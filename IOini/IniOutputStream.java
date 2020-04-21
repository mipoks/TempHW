package IOini;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class IniOutputStream extends Writer {
    private BufferedWriter out;

    public IniOutputStream(FileWriter out) {
        this.out = new BufferedWriter(out);
    }

    public void writeIni(TreeMap<String, String> map) throws IOException {
        Set keys = map.keySet();
        Collection values = map.values();
        Iterator iterator = keys.iterator();
        Iterator it = values.iterator();
        while(it.hasNext()) {
            out.write(iterator.next() + "=" + it.next() + "\n");
        }
        flush();
    }

    public void writeIniLine(String key, String value) throws IOException {
        out.write(key + "=" + value + "\n");
        flush();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(cbuf, off, len);
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
