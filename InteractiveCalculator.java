import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Main class that takes input from command line and print the result of evaluation continuously to
 * provide calculator function.
 * 
 * @author Shuta Shibue
 */
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
      if (args[0].equals("QUIT"))
        break;
      if (args[0].equals("STORE"))
        calculator.store(args[1].toCharArray()[0]);
      else {
        calculator.evaluate(line);
        pen.println(calculator.lastVal);
      }
    }
    sc.close();
  }
}
