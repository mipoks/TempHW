package HW16;

import javax.activation.MimetypesFileTypeMap;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW16 {
    StringBuilder sb;
    Scanner in;
    private void rename(File file, File toFile) {
        file.renameTo(toFile);
    }
    private void open(File file) throws IOException {
        System.out.print("Do you want to open the file? Y/N: ");
        boolean bool = in.next().charAt(0) == 'Y' || in.next().charAt(0) == 'y';
        if (bool) {
            Desktop.getDesktop().open(file);
        }
    }
    private File saveFile(String fileName) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(sb.toString());
            fileWriter.flush();
        }
        catch (Exception ex) {
            System.out.print(ex);
        }
        return file;
    }
    public void taks2(String URIstr) {
        in = new Scanner(System.in);
        URI uri;
        try {
            uri = new URI(URIstr);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            BufferedReader readFile = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            sb = new StringBuilder();
            while ((line = readFile.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            String fileName = url.getFile();
            Pattern pattern = Pattern.compile("[\\/][a-z\\.\\-A-Z0-9]+[\\.][a-zA-Z]{1,}$");
            Matcher ff = pattern.matcher(fileName);
            if (ff.find()) {
                fileName = fileName.substring(ff.start() + 1, ff.end());
                File toFile = saveFile(fileName);
                open(toFile);
            }
            else {
                File file = saveFile("unknown");
                String mimeType = connection.getContentType();
                if (mimeType.length() > 0) {
                    pattern = Pattern.compile("[\\/][a-z\\.\\-A-Z0-9]+$");
                    ff = pattern.matcher(mimeType);
                    File toFile = file;
                    if (ff.find()) {
                        toFile = new File("unknown." + mimeType.substring(ff.start() + 1, ff.end()));
                    }
                    rename(file, toFile);
                    open(toFile);
                }
                else {
                    System.out.print("Can't open, sorry");
                }

            }
        } catch (URISyntaxException e) {
            System.out.print("Uncorrect URI");
            return;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("Check your Internet connection");
        }
    }


    public void task3(String URIstr) {
        URI uri;
        try {
            uri = new URI(URIstr);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            BufferedReader readFile = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            sb = new StringBuilder();
            while ((line = readFile.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            line = sb.toString();
            Pattern pattern = Pattern.compile("<div");
            Matcher ff = pattern.matcher(line);
            int ans = 0;
            while (ff.find()) {
                ans++;
            }
            System.out.print(ans);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
