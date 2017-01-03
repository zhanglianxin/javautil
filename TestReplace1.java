package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 对指定字符文件进行替换文本替换
 * 使用多线程
 */
public class TestReplace1 {
    private static String pathname = "D:/PortableGit/coolman/phpstudy";
    private final static int threadNum = 3; // 线程数量

    public static void main(String[] args) throws Exception {
        long starttime = System.nanoTime();
        /*
         * 遍历指定路径，对其中的文件进行分批处理
         */
        File[] files = new File(pathname).listFiles();
        
        int size = files.length / threadNum; // 每个线程要处理的个数
        
        for (int threadId = 0; threadId < threadNum; threadId++) {
            // 对目标文件进行确切地分批
            int start = threadId * size, end = (threadId + 1) * size; 
            if (threadId == threadNum - 1) {
                end = files.length - 1;
            }
            // 每个线程要处理的文件集合
            List<File> eachFiles = new ArrayList<File>();
            for (int i = start; i < end; i++) {
                eachFiles.add(files[i]);
            }
            // 将处理的文件通过线程的构造方法传入
            new SingleThread(eachFiles).start();
        }
        
        System.out.println("COMPLETE!");
        long endtime = System.nanoTime();
        System.out.println("times: " + (endtime - starttime));
    }
    
    /**
     * 为每个线程分配要处理的任务 
     */
    private static class SingleThread extends Thread {
        private List<File> files;
        
        public SingleThread(List<File> eachFiles) {
            this.files = eachFiles;
        }

        @Override
        public void run() {
            // 获取目标文件，并对其进行处理
            for (File file : files) {
                // 判断目标文件也可以拿到线程外面去处理
                if (file.getName().endsWith(".php")) {
                    try {
                        readAndWrite(file);
                        exchange(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
//                if (line.contains("https://")) {
//                    line = line.replace("https://", "//");
//                } else if (line.contains("http://")) {
//                    line = line.replace("http://", "//");
//                }
                
//                if (line.contains("img") && line.contains("//")) {
//                    line = line.replace("//", "http://");
//                }
                // replace the tab indent to 4 spaces
                if (line.contains("\t")) {
                    System.out.println("run");
                    line = line.replace("\t", "    ");
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
