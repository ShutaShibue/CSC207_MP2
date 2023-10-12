import java.math.BigInteger;

/**
 * A simple implementation of Fractions.
 * 
 * @author Samuel A. Rebelsky
 * @author Shuta Shibue
 * @version 1.2 of August 2023
 */
public class BigFraction {
  BigInteger num;
  BigInteger denom;

  /*
   * New fraction with BigInt num and denom
   */
  public BigFraction(BigInteger num, BigInteger denom) {
    this.num = num;
    this.denom = denom;
  }

  /*
   * New fraction with int num and denom
   */
  public BigFraction(int num, int denom) {
    this.num = BigInteger.valueOf(num);
    this.denom = BigInteger.valueOf(denom);
  }

  /*
   * New fraction with string "num/denom"
   */
  public BigFraction(String str) {
    if (!str.contains("/")) {
      this.num = BigInteger.valueOf(Integer.parseInt(str));
      this.denom = BigInteger.valueOf(1);
    } else {
      String[] sep = str.split("/");
      this.num = BigInteger.valueOf(Integer.parseInt(sep[0]));
      this.denom = BigInteger.valueOf(Integer.parseInt(sep[1]));
    }
  }

  /**
   * @return double value of fraction
   */
  public double doubleValue() {
    return this.num.doubleValue() / this.denom.doubleValue();
  } // doubleValue()

  /**
   * @param addMe BigFraction to add
   * @return Resulting fraction
   */
  public BigFraction add(BigFraction addMe) {
    BigInteger resultNumerator =
        (this.num.multiply(addMe.denom)).add(addMe.num.multiply(this.denom));
    BigInteger resultDenominator = this.denom.multiply(addMe.denom);
    return new BigFraction(resultNumerator, resultDenominator);
  }// add(BigFraction)

  /**
   * @param subtractMe BigFraction to add
   * @return Resulting fraction
   */
  public BigFraction subtract(BigFraction subtractMe) {
    BigFraction minus1 = new BigFraction(-1, 1);
    return add(subtractMe.multiply(minus1)); // return addition of two values but subtractMe is
                                             // negated
  }// add(BigFraction)

  /**
   * @param multiplyMe BigFraction to multiply
   * @return Resulting fraction
   */
  public BigFraction multiply(BigFraction multiplyMe) {
    BigInteger resultNum = this.num.multiply(multiplyMe.num); // set result numerator to multiplied
                                                              // numerators
    BigInteger resultDenom = this.denom.multiply(multiplyMe.denom); // set result denominator to
                                                                    // multiplied denominators
    return new BigFraction(resultNum, resultDenom); // return new BigFraction with result numerator
                                                    // and result
                                                    // denominator
  } // multiply()

  /**
   * @param divideMe BigFraction to multiply
   * @return Resulting fraction
   */
  public BigFraction divide(BigFraction divideMe) {
    return multiply(divideMe.Invert());
  }

  public BigFraction fractional() {
    int whole = this.num.divide(this.denom).intValue();
    BigFraction wholeFrac = new BigFraction(-whole, 1);
    return this.add(wholeFrac);
  }

  /**
   * 
   * @return Inverted Bigfraction
   */
  public BigFraction Invert() {
    return new BigFraction(this.denom, this.num);
  }

  /**
   * Get the denominator of this fraction.
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   */
  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   */
  public String toString() {
    simplify();
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } // if it's zero
    else if(this.denom.intValue() == 1)
    {
      return this.num + "";
    }
    else return this.num + "/" + this.denom;
  } // toString()

  static BigInteger gcd(BigInteger x, BigInteger y) { // Euclidean algorithm
    return (y.intValue() == 0) ? x : gcd(y, x.mod(y)); // return previous y if x is divisible
  }

  void simplify() {
    // bring nevative sign on denom to num
    if (this.denom.intValue() < 0) {
      this.num = this.num.multiply(BigInteger.valueOf(-1));
      this.denom = this.denom.multiply(BigInteger.valueOf(-1));
    }
    BigInteger factor = gcd(this.num, this.denom);
    this.num = this.num.divide(factor);
    this.denom = this.denom.divide(factor);
  }

} // class Fraction
