import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader  reader = new BufferedReader(new InputStreamReader(System.in));
        String nameCatalog = reader.readLine();
        maxBuyers(nameCatalog);
    }
    public static void maxBuyers(String nameCatalog) throws IOException {
        List<Path> files = Files.walk(Paths.get(nameCatalog))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        ArrayList<Double> [] cash= new ArrayList[files.size()];
        for(int i = 0; i < cash.length; i++) {
            cash[i] = new ArrayList<>();
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(String.valueOf(files.get(i)))));
            while (reader1.ready()) {
                cash[i].add(Double.parseDouble(reader1.readLine().replace("\\n", "")));
            }
        }

        ArrayList<Double> result = new ArrayList<>();
        double count = 0;
        for(int j = 0; j < cash[0].size(); j++){
            for(int i = 0; i < cash.length; i++){
                count = count + cash[i].get(j);
            }
            result.add(count);
            count = 0;
        }

        double max = 0;
        int maxInterval = -1;
        for(int i = 0; i < result.size(); i++){
            if(max < result.get(i)){
                max = result.get(i);
                maxInterval = i;
            }
        }
        maxInterval++;

        System.out.println(maxInterval);
    }
}
