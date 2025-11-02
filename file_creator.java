import java.util.*;
import java.io.*;

public class file_creator {
    public static void main(String[] args) {
        String fileName = "randomDataset.txt";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an integer:");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("Enter only a positive Integer.");
            scanner.close();
            return;
        }

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            Random random = new Random();
            StringBuilder xs = new StringBuilder();
            StringBuilder ys = new StringBuilder();

            for (int i = 0; i < n; i++) {
                double x = random.nextDouble() * 100;
                double y = random.nextDouble() * 100;

                xs.append(String.format("%.2f  ", x));
                ys.append(String.format("%.2f  ", y));
            }
            fileWriter.write(xs.toString().trim() + "\n");
            fileWriter.write(ys.toString().trim() + "\n");
            scanner.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
}