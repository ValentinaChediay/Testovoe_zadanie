import java.io.*;
import java.util.ArrayList;

public class Task2 {
    public static void main(String[] args) throws IOException {
        BufferedReader readerNameFile = new BufferedReader(new InputStreamReader(System.in));
        String nameFile1 = readerNameFile.readLine();
        String nameFile2 = readerNameFile.readLine();
        pointPositionCalculation(nameFile1, nameFile2);
    }
    public static void pointPositionCalculation(String nameFile1, String nameFile2) throws IOException {
        ArrayList<Point> verticesQuad = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();
        readingDate(verticesQuad, nameFile1);
        readingDate(points, nameFile2);
        int [] answer = new int[points.size()];
        int result = -1;

        //на одной из вершин
        for(int i = 0; i < points.size();) {
            for(int j = 0; j < verticesQuad.size(); j++) {
                if (points.get(i).getX() == verticesQuad.get(j).getX() && points.get(i).getY() == verticesQuad.get(j).getY()) {
                    result = 0;
                }
            }
            answer[i] = result;
            result = -1;
            i++;
        }

        //на одной из сторон
        verticesQuad.add(verticesQuad.get(0));
        for(int i = 0; i < points.size();){
            if(answer[i] == -1){
                for(int j = 0; j < verticesQuad.size() - 1; j++) {
                    if (((points.get(i).getX() - verticesQuad.get(j).getX()) * (verticesQuad.get(j+1).getY() - verticesQuad.get(j).getY()) - (verticesQuad.get(j+1).getX() - verticesQuad.get(j).getX()) * (points.get(i).getY() - verticesQuad.get(j).getY()))==0
                            && (((points.get(i).getY() < verticesQuad.get(j+1).getY() && points.get(i).getY() > verticesQuad.get(j).getY()) || (points.get(i).getY() > verticesQuad.get(j+1).getY() && points.get(i).getY() < verticesQuad.get(j).getY()))
                            || ((points.get(i).getX() < verticesQuad.get(j+1).getX() && points.get(i).getX() > verticesQuad.get(j).getX()) || (points.get(i).getX() > verticesQuad.get(j+1).getX() && points.get(i).getX() < verticesQuad.get(j).getX())))){
                        result = 1;
                    }
                }
                answer[i] = result;
                result = -1;
            }
            i++;
        }

        //точка внутри или снаружи
        for(int i = 0; i < points.size();){
            if(answer[i] == -1){
                int [] left = new int[4];
                for(int j = 0; j < verticesQuad.size() - 1; j++) {
                    if (((points.get(i).getX() - verticesQuad.get(j).getX()) * (verticesQuad.get(j+1).getY() - verticesQuad.get(j).getY()) - (verticesQuad.get(j+1).getX() - verticesQuad.get(j).getX()) * (points.get(i).getY() - verticesQuad.get(j).getY())) > 0){
                        left[j] = 1;
                    }
                }
                int count = 0;
                for(int n : left){
                    if(n == 1) count++;
                }
                if(count == 4){
                answer[i] = 2;
                } else answer[i] = 3;
            }
            i++;
        }
        for(int i : answer){
            System.out.print(i + "\n");
        }
    }
    public static void readingDate(ArrayList<Point> list, String nameFile) throws IOException {
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(nameFile)));
        String line = reader1.readLine();
        while (line != null) {
            String[] split = line.split(" ");

            float x = Float.parseFloat(split[0]);
            float y = Float.parseFloat(split[1].replace("\\n", ""));

            list.add(new Point(x, y));

            line = reader1.readLine();
        }
        reader1.close();
    }
}
