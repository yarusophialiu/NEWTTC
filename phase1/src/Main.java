import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException{
        BufferedReader fileReader = new BufferedReader(new FileReader("phase1/src/stations.txt"));
        String info = fileReader.readLine();
        System.out.println(info);
  }
}