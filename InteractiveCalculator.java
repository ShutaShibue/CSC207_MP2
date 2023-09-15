import java.io.PrintWriter;
import java.util.Scanner;

public class InteractiveCalculator {
    public static void main(String[] DefArgs) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true);
        BFCalculator calculator = new BFCalculator();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().replaceAll("\n", "");
            // return pressed
            if (line.length() == 0) 
                continue;
            String[] args = line.split(" ");   
            if(args[0].equals("QUIT")) break; 
            if(args[0].equals("STORE")) calculator.store(args[1].toCharArray()[0]);
            else{
                calculator.evaluate(line);
                pen.println(calculator.lastVal);
            }
        }
        sc.close();
    }
}
