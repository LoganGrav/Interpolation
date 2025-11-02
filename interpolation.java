import java.util.*;
import java.io.*;

public class interpolation {
private static double[] xs;
private static double[] ys;
private static double[] cs;
private static double z;
private static double result;





    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("Enter only one argument.");
            return;
        }
        String fileName = args[0];
        Scanner scanner = new Scanner(System.in);
        readFile(fileName);
        coeff(xs, ys, cs);
        while (true) {
            System.out.println("Enter value to be computed:");
            String userInput = scanner.nextLine();
            if (userInput.equals("q")) {
                System.out.println("Exiting...");
                scanner.close();
                return;
            }
            
            try {
                double userValue = Double.parseDouble(userInput);
                double result = evalNewton(xs, cs, userValue);
                System.out.println(result);
            }
            catch (NumberFormatException e) {
                System.out.println("Enter either a value to be computed or 'q' to exit.");
            }
        }
    }

    private static void readFile(String fileName) {
        String line = null;
        int valueNumber = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            if((line = bufferedReader.readLine()) != null) {
                String[] tempValues = line.trim().split(" +");
                valueNumber = tempValues.length;
                cs = new double[valueNumber];
                xs = new double[valueNumber];
                ys = new double[valueNumber];
                for (int i = 0; i < tempValues.length; i++) {
                    xs[i] = Double.parseDouble(tempValues[i]);
                }
                if((line = bufferedReader.readLine()) != null) {
                    tempValues = line.trim().split(" +");
                    for (int i = 0; i < tempValues.length; i++) {
                        ys[i] = Double.parseDouble(tempValues[i]);
                    }
                } else {
                    System.out.println("Invalid input file.");
                }
            } else {
                System.out.println("Invalid input file.");
            }
            
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
    }

    private static void coeff(double[] xs, double[] ys, double[] cs) {
        int n = xs.length-1;
        
        for (int i = 0; i <= n; i++) {
            cs[i] = ys[i];
        }
        for (int j = 1; j <= n; j++) {
            for (int i = n; i >= j; i--) {
                cs[i] = (cs[i] - cs[i-1]) / (xs[i] - xs[i-j]);
            }
        }
    }

    private static double evalNewton(double[] xs, double[] cs, double z) {
        int n = cs.length - 1;
        double result = cs[n];
        for (int i = n-1; i >=0; i--) {
            result = result * (z-xs[i]) + cs[i];
        }
        return result;
    }

}