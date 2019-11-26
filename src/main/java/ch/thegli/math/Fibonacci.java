package ch.thegli.math;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Fibonacci sequence: </br>
 * F(0) = 0, F(1) = 1, F(n) = F(n-1) + F(n-2)
 *
 * How to compile and run: <code>
 *  javac @arguments
 * </code> where content of file 'arguments' is: <code>
 *  -d bin
 *  src/Fibonacci.java
 * </code>
 * 
 * java -cp bin ch.thegli.math.Fibonacci 20
 * 
 * Resulting output: <code>
 *  0 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1'597 2'584 4'181 
 *  Fibonacci number at index 20 is 6'765
 * </code>
 * 
 * @author Thomas Egli
 */
public class Fibonacci {
  private DecimalFormat formatter = null;

  public static void main(final String[] args) {
    if (args.length < 1) {
      System.out.println("Please provide the index of the Fibonacci number to get calculated.");
      System.out.println("Syntax: <Index> [-a]");
      System.exit(1);
    }

    String requestedIndexArg = args[0];
    String outputSequenceArg = args.length > 1 ? args[1] : null;

    final Fibonacci app = new Fibonacci();
    app.execute(requestedIndexArg, outputSequenceArg);
  }

  public Fibonacci() {
    formatter = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
    final DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
    // Swiss German style
    symbols.setGroupingSeparator('\'');
    formatter.setDecimalFormatSymbols(symbols);
  }

  public int execute(final String requestedIndexArg, final String outputSequenceArg) {
    int rc = 0;

    try {
      final int index = parseIndex(requestedIndexArg);
      final boolean doOutputSequence = parseDoOutputSequence(outputSequenceArg);

      final long fibonacciNumber = calculate(index, doOutputSequence);
      System.out.println("Fibonacci number at index " + index + " is " + formatter.format(fibonacciNumber));
    } catch (IllegalArgumentException iae) {
      rc = 2;
    }

    return rc;
  }

  private int parseIndex(final String requestedIndexArg) throws IllegalArgumentException {
    int index = -1;
    try {
      index = Integer.parseInt(requestedIndexArg);
    } catch (final NumberFormatException nfe) {
      // swallow
    }

    if (index < 0) {
      throw new IllegalArgumentException("Given index is not a non-negative integer: " + requestedIndexArg);
    } else if (index > 92) {
      throw new IllegalArgumentException(
          "Sorry, index too large: " + requestedIndexArg + ". Please choose a value between 0 and 92.");
    }

    return index;
  }

  private boolean parseDoOutputSequence(final String outputSequenceArg) {
    return "-a".equals(outputSequenceArg);
  }

  protected long calculate(final int index) {
    return calculate(index, false);
  }

  /*
   * long up to index 92 (7'540'113'804'746'346'429)
   */
  protected long calculate(final int index, final boolean doOutputSequence) {
    // F(0) = 0
    long f1 = 0;
    // F(1) = 1
    long f2 = 1;
    long fib = 0;

    StringBuilder sequence = new StringBuilder();
    for (int i = 1; i <= index; i++) {
      sequence.append(formatter.format(fib));
      sequence.append(" ");
      f1 = f2;
      f2 = fib;
      fib = f1 + f2;
    }

    if (doOutputSequence) {
      sequence.append(fib);
      System.out.println(sequence);
    }

    return fib;
  }
}
