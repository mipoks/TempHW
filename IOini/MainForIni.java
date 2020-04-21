package IOini;

import java.io.*;
import java.util.TreeMap;

public class MainForIni {
    public void run() {
        TreeMap<String, String> map = new TreeMap();
        map.put("test", "1");
        map.put("test2", "test2");
        map.put("kay", "vlaue");
        map.put("fde", "928");
        try {
            IniOutputStream iniOut = new IniOutputStream(new FileWriter("test.ini"));
            iniOut.writeIni(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            IniInputStream iniOut = new IniInputStream(new FileReader("test.ini"));
            TreeMap treeMap = iniOut.readIni();
            System.out.print(treeMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
