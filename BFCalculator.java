import java.util.HashMap;

public class BFCalculator {
    BigFraction lastVal = new BigFraction(0, 1); // Initialize as 0 so store() doesn't store null value
    HashMap<Character, BigFraction> registerMap = new HashMap<>();

    public BFCalculator(){}

    /**
     * 
     * @param exp String type equation to evaluate, precedence incensitive
     * @return resulting fraction
     */
    public BigFraction evaluate(String exp) {
        String[] args = exp.replaceAll("\s", "").split("[+-*/]"); // remove space and separate arguments
        char[] commands = exp.replaceAll("[^+-*/]", "").toCharArray();

        lastVal = new BigFraction(args[0]);
        for (int i = 1; i < args.length; i++) {
            BigFraction frac2;
            if (args[i].matches("[a-zA-z]"))
                frac2 = registerMap.get(args[i].toCharArray()[0]); // if arg contains char, refer to register and apply. arg is always length 1.
            else
                frac2 = new BigFraction(args[i]); // otherwise just create bigfraction from string
            execute(frac2, commands[i - 1]); // args start from index 1, commands starts 0
        }
        return lastVal;
    }

    public void store(char register) {
        
        registerMap.put('d', lastVal);
    }

    /**
     * Execute given command and store in lastVal. First value is lastVal, and second is frac2.
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
            throw new Error("Unknown Command", null);
    }
}
