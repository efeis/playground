package oth.ics.pg2.polynomial;

public class Polynomial {
    private static int counter = 0;         // counts how many polynomials have been created
    private final int ID;                   // stores an ID for each polynomial
    private final double[] coefficients;    // stores the coefficients

    // assigns the coefficients to the array and a unique ID to the polynomial
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
        this.ID = ++counter;
    }

    // this method generates a string representation of the polynomial
    @Override
    public String toString() {
        String result = "y ="; // beginning of the result string
        int degree = coefficients.length - 1;

        boolean isFirstTerm = true;
        boolean isFirstSign = true;
        for (int i = degree; i >= 0; i--) { // iterate from highest degree to 0
            double coefficient = coefficients[i];
            if (coefficient != 0) {
                // Determine sign
                String sign = (coefficient < 0) ? " - " : (isFirstTerm ? " " : " + ");
                coefficient = Math.abs(coefficient);

                result += sign;

                // append coefficient if not 1 or degree is 0
                if (coefficient != 1 || i == 0) result += coefficient;

                // append x if not constant term
                if (i > 0) result += "x";

                // append power if not linear or constant term
                if (i > 1) result += "^" + i;

                isFirstTerm = false;
                isFirstSign = false;
            }
        }
        // if all coefficients are zero
        if (isFirstSign) result += " 0";
        return result;
    }


    // returns the highest power of x (degree of the polynomial)
    public int degree() {
        int degree = coefficients.length - 1;
        while (degree >= 0 && coefficients[degree] == 0)
            degree--;
        return degree;
    }

    // getter method for getting the ID of a polynomial
    public int getID() {
        return ID;
    }

    // evaluates the result with a given value of x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // adds two polynomials and returns a new polynomial representing their sum
    public Polynomial add(Polynomial other) {
        // finds the max coefficient length and stores it
        int maxLength = Math.max(this.coefficients.length, other.coefficients.length);
        double[] resultCoefficients = new double[maxLength];

        // loop for adding all coefficients one by one
        for (int i = 0; i < maxLength; i++) {
            double thisCoefficient = (i < this.coefficients.length) ? this.coefficients[i] : 0;
            double otherCoefficient = (i < other.coefficients.length) ? other.coefficients[i] : 0;
            resultCoefficients[i] = thisCoefficient + otherCoefficient;
        }
        return new Polynomial(resultCoefficients);
    }

    // calculates the derivative and returns a new polynomial representing the derivative
    public Polynomial derivative() {
        int degree = this.degree();
        /* if the polynomial is a constant (degree 0),
           it returns a new polynomial with coefficient 0 */
        if (degree == 0)
            return new Polynomial(new double[]{0});

        // loop for multiplying each coefficient by its degree
        double[] derivativeCoefficients = new double[degree];
        for (int i = 1; i <= degree; i++) {
            derivativeCoefficients[i - 1] = coefficients[i] * i;
        }
        return new Polynomial(derivativeCoefficients);
    }
}
