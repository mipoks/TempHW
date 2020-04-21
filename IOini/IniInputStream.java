package IOini;

import java.io.*;
import java.util.TreeMap;

public class IniInputStream extends Reader {
    private BufferedReader in;
    public IniInputStream(FileReader in) {
        this.in = new BufferedReader(in);
    }

    public TreeMap readIniLine(){
        TreeMap<String,String> map = new TreeMap<>();
        String line;
        try {
            if(in.ready()) {
                line = in.readLine();
                String[] KV=(line.split("="));
                map.put(KV[0], KV[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public TreeMap<String, String> readIni() {
        TreeMap<String,String> map = new TreeMap<>();
        while(true) {
            try {
                if (!in.ready()) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            TreeMap<String, String> tmp = readIniLine();
            map.put(tmp.lastKey(), tmp.get(tmp.lastKey()));
        }
        return map;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return in.read(cbuf, off, len);
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
