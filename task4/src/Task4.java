import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader.readLine();
        maxWorkload(nameFile);
    }
    public static void maxWorkload(String nameFile) throws IOException {
        //прочла как String
        BufferedReader readerTime = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
        ArrayList<String[]> listHours = new ArrayList<>();
        while (readerTime.ready()){
            String s = readerTime.readLine().replace("\\n", "");
            String[] split = s.split(" ");
            listHours.add(split);
        }

        List<Integer>[] result= new ArrayList[3];
        for(int i = 0; i < 3; i++){
            result[i] = new ArrayList<>();
        }

        //перевела в Integer, потом в минуты
        ArrayList<Integer[]> listMinutes = new ArrayList<>();
        for(int i = 0; i < listHours.size(); i++){
            Integer[] minutes = new Integer[listHours.get(i).length];
            for(int j = 0; j < listHours.get(i).length; j++){
                String[] split = listHours.get(i)[j].split(":");
                int minute = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
                minutes[j] = minute;
                result[0].add(minute);
            }
            listMinutes.add(minutes);
        }

        //упорядочила минуты по возрастанию и без повторений
        result[0] = result[0].stream().distinct().sorted().collect(Collectors.toList());

        for(int i = 0; i < result[0].size(); i++){
            result[1].add(i, 0);
        }

        //учет вошедщих и вышедщих
        for(int i = 0; i < result[0].size(); i++){
            for (Integer[] n : listMinutes){
                if(result[0].get(i).equals(n[0])){
                    result[1].set(i, result[1].get(i) + 1);
                }
                if(result[0].get(i).equals(n[1])){
                    result[1].set(i, result[1].get(i) - 1);
                }
            }
        }


        for(int i = 0; i < result[1].size()-1; i++){
            if(result[2].isEmpty()){
                result[2].add(result[1].get(0));
            }
            result[2].add(result[2].get(i)+result[1].get(i+1));
        }

        int max = 0;
        for(int i = 0; i < result[2].size(); i++){
            if(result[2].get(i) > max){
                max = result[2].get(i);
            }
        }

        ArrayList<String> results = new ArrayList<>();
        ArrayList<String> start = new ArrayList<>();
        ArrayList<String> end = new ArrayList<>();

        for(int i = 1; i < result[2].size(); i++){
            if(result[2].get(0) == max) {
                start.add(result[0].get(0)/60 + ":" + result[0].get(0)%60);
            }
            if(result[2].get(i) == max && result[2].get(i-1) != max){
                start.add(result[0].get(i)/60 + ":" + result[0].get(i)%60);
            }
            if(result[2].get(i) != max && result[2].get(i-1) == max){
                end.add(result[0].get(i)/60 + ":" + result[0].get(i)%60);
            }
        }

        for(int i = 0; i < start.size(); i++){
            results.add(start.get(i) + " " + end.get(i));
        }

        for(String s : results){
            System.out.print(s + "\n");
        }
    }
}
