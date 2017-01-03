package mytest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 对 hosts 记录进行合并排序整理
 * 
 * @author zhanglianxin
 *
 */
public class SortHosts {

    public static void main(String[] args) throws Exception {
        File origin = new File("D:/PortableGit/hosts/hosts");
        File origin1 = new File("C:/Windows/System32/drivers/etc/hosts");
        File[] files = { origin, origin1 };

        // IP, ADDRESSES
        Map<String, Set<String>> map = new TreeMap<>();

        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file));) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.trim().length() > 0) {
                        String[] splits = line.split("\\s+");
                        System.out.println(splits.length);
                        String key = splits[0];
                        String value = splits[1];
                        if (map.containsKey(key)) {
                            map.get(key).add(value);
                        } else {
                            Set<String> valueSet = new TreeSet<>();
                            valueSet.add(value);
                            map.put(key, valueSet);
                        }
                    }
                }
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                "E:/newhosts")));) {
            Set<Entry<String, Set<String>>> entries = map.entrySet();
            for (Entry<String, Set<String>> entry : entries) {
                String ip = entry.getKey();
                Set<String> addresses = entry.getValue();
                for (String address : addresses) {
                    // System.out.println(ip + "\t" + address);
                    bw.append(ip + "    " + address + System.lineSeparator());
                }
                bw.append(System.lineSeparator());
                bw.flush();
            }
        }
        
        if (new File("E:/newhosts").renameTo(new File("E:/hosts")))
            System.out.println("rename succeeded");;
    }

}
