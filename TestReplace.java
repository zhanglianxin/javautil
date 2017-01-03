package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 对指定字符文件进行替换文本替换
 */
public class TestReplace {
    private static String pathname = "D:/USBWebserver v8.6/root/bsstudy/";

    public static void main(String[] args) throws Exception {
        long starttime = System.nanoTime();
        /*
         * 遍历指定路径，获取目标文件，并对其进行处理
         */
        File[] files = new File(pathname).listFiles();
        for (File file : files) {
            if (file.getName().endsWith(".html")) {
                readAndWrite(file);
                exchange(file);
            }
        }
        
        System.out.println("COMPLETE!");
        long endtime = System.nanoTime();
        System.out.println("times: " + (endtime - starttime));
    }

    /**
     * 按行读取，如果找到指定字符串，对其进行替换，并将读取到的每一行写出到临时文件中
     * 
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void readAndWrite(File file) throws FileNotFoundException,
            IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file));
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        getTempname(file))));) {

            String line;
            while ((line = br.readLine()) != null) {
                // 此处为了解字符文件内容的情况下，做了非此即彼的判断
                if (line.contains("https://")) {
                    line = line.replace("https://", "//");
                } else if (line.contains("http://")) {
                    line = line.replace("http://", "//");
                }
                bw.write(line + System.lineSeparator());
                bw.flush();
            }
        }
    }

    /**
     * 删除原始文件，并对临时文件进行重命名
     * 
     * @param file
     */
    private static void exchange(File file) {
        File tempfile = new File(getTempname(file));
        if (tempfile.exists() && file.delete()) {
            tempfile.renameTo(file.getAbsoluteFile());
        }
    }

    /**
     * 获取临时文件名
     * 
     * @param file
     * @return
     */
    private static String getTempname(File file) {
        return file.getAbsolutePath() + "t";
    }
}
