import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
        String nameFile = reader1.readLine();
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
        ArrayList<Double> number = new ArrayList<>();
        while (reader2.ready()){
            number.add(Double.parseDouble(reader2.readLine()));
        }
        reader2.close();
        reader1.close();
        Collections.sort(number);
        System.out.printf("%.2f" + "\n", percentile(number, 90));
        System.out.printf("%.2f" + "\n", (median(number)));
        System.out.printf("%.2f" + "\n", Collections.max(number));
        System.out.printf("%.2f" + "\n", Collections.min(number));
        System.out.printf("%.2f" + "\n", number.stream().mapToDouble(Number::doubleValue).average().getAsDouble());
    }
    public static double percentile(List<Double> number, double P){
        double x = P/100 * (number.size() - 1) + 1;
        double v;
        if(P < 100){
            return v = number.get((int) x - 1) + (x - (int) x) * (number.get((int) x) - number.get((int) x - 1));
        } else return v = number.get(number.size()-1);
    }
    public static double median(List<Double> number){
        int index = (number.size()-1)/2;
        if(number.size() % 2 == 0){
            return (number.get(index) + number.get(index + 1))/2;
        } else return number.get(index);
    }
}
