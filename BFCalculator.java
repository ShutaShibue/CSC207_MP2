/**
 * Calculator class that can evaluate expressions and store values.
 * 
 * @author Shuta Shibue
 */
public class BFCalculator {
  public BigFraction lastVal = new BigFraction(0, 1); // Initialize as 0 so store() doesn't store
                                                              // null value
  BigFraction[] f = new BigFraction[26];
  public BFCalculator() {}
  
  /**
    * Evaluate given string, but precedence incensitive
    * @param exp String type equation to evaluate
    * @return resulting fraction
    */
  public BigFraction evaluate(String exp) {
    String[] args = exp.split("\s"); // remove space and separate arguments

    //This should be done outside loop because the first number must be stored into lastVal.
    if (args[0].matches("[a-zA-z]")) {
      lastVal = f[Character.toLowerCase(args[0].toCharArray()[0]) - 97]; //turn to lower and then shift 97 (a->0)

    } else if(args[0].matches("(-?\\d{1,}\\/-?\\d{1,})|\\d{1,}")){
      lastVal = new BigFraction(args[0]);
    }
    else throw new Error("Number or char expected, but " + args[0] + " was given.", null);

    for (int i = 1; i < args.length; i += 2) {
      BigFraction frac2;
      if (args[i + 1].matches("[a-zA-z]")) {
        // if arg contains char, refer to register and apply.
        frac2 = f[Character.toLowerCase(args[i + 1].toCharArray()[0]) - 97];
      } else if(args[i + 1].matches("(-?\\d{1,}\\/-?\\d{1,})|\\d{1,}")){ //fraction or integer
        // otherwise just create bigfraction from string.
        frac2 = new BigFraction(args[i + 1]); 
      }
      else throw new Error("Number or char expected, but " + args[i + 1] + " was given.", null);  
      execute(frac2, args[i].toCharArray()[0]); // args start from index 1, commands starts 0
    }
    lastVal.simplify();
    return lastVal;
  }//evaluate(String)
  
  /**
  * Store lastVal into given named register.
  * @param register A character key to register the latest value.
  */
  public void store(char register) {
    if(!Character.isAlphabetic(register)) throw new Error("STORE key must be alphabet.", null);
    f[Character.toLowerCase(register)- 97] = lastVal;
  }//store(char)
  
  /**
  * Execute given command and store in lastVal. First value is lastVal, and second is frac2.
  * 
  * @param frac2 value to calculate
  * @param command char, either sign of add, sub, mult, div
  * @return null
  */
  void execute(BigFraction frac2, char command) {
    if (command == '+')
      lastVal = lastVal.add(frac2);
    else if (command == '-')
      lastVal = lastVal.subtract(frac2);
    else if (command == '*')
      lastVal = lastVal.multiply(frac2);
    else if (command == '/')
      lastVal = lastVal.divide(frac2);
    else
      throw new Error("+, -, *, or / was expected, but " + command + " was given." , null);
  }//execute(Bigfraction, char)
  
} //class BFCalculator
  