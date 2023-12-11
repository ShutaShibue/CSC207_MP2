import java.io.PrintWriter;

/**
 * Main class that takes expressions as argument and write answers to the console.
 * 
 * @author Shuta Shibue
 */
public class QuickCalculator {
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    BFCalculator calculator = new BFCalculator();
    for (int i = 0; i < args.length; i++) {
      String[] cmd = args[i].split(" ");
      if (cmd[0].equals("STORE"))
        calculator.store(cmd[1].toCharArray()[0]);
      else {
        BigFraction result = calculator.evaluate(args[i]);
        if (result != null)
          pen.println(args[i] + " = " + result);
      }
    }
  }
}
