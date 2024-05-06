package oth.ics.pg2.polynomial.cli;

import oth.ics.pg2.polynomial.Polynomial;
import oth.ics.pg2.polynomial.list.PolynomialList;

import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class PolynomialCLI {

    private final PrintStream consoleOut;
    private final Scanner consoleIn;
    private final PolynomialList polynomialList;

    // constructor for the scanner and console I/O
    public PolynomialCLI(PrintStream consoleOut, Scanner consoleIn) {
        this.consoleOut = consoleOut;
        this.consoleIn = consoleIn;
        consoleIn.useDelimiter("\n");
        consoleIn.useLocale(Locale.ENGLISH);
        this.polynomialList = new PolynomialList();
    }

    // main method for running the console program
    public static void main(String[] args) {
        new PolynomialCLI(System.out, new Scanner(System.in)).run();
    }

    // run until the "running" flag becomes 'false'
    public void run() {
        boolean running = true;
        while (running) {
            running = showMainMenu();
        }
    }

    /*
    displays a menu, reads the user input,
    and performs the action which is connected to that case.
    if the input is not valid, displays an error message.
    */
    private boolean showMainMenu() {
        consoleOut.println("(1) Show current polynomials");
        consoleOut.println("(2) Evaluate a polynomial");
        consoleOut.println("(3) Create new polynomial from input");
        consoleOut.println("(4) Add two polynomials");
        consoleOut.println("(5) Calculate derivative of a polynomial");
        consoleOut.println("(6) Delete a polynomial");
        consoleOut.println("(9) Exit");
        consoleOut.print("$ ");

        if (consoleIn.hasNextInt()) {
            switch (consoleIn.nextInt()) {
                case 1: showPolynomials(); break;
                case 2: evaluatePolynomial(); break;
                case 3: createNewPolynomial(); break;
                case 4: addPolynomials(); break;
                case 5: calculateDerivative(); break;
                case 6: deletePolynomial(); break;
                case 9: return false;
            }
        } else {
            consoleIn.next();
            consoleOut.println("Invalid choice. Please enter a valid number. (1,2,3,4,5,6,9)");
        }
        return true;
    }

    // case 1:
    private void showPolynomials() {
        polynomialList.showCurrentPolynomials();
    }

    /*
    rest of the code contains all the other cases and they all use a similar structure:
    - output the message for getting the ID of a polynomial or the degree of a new polynomial
    - get the polynomial(s) from the list based on the input
    - call the relevant method from Polynomial.java and perform operations on the specified ID
    - if a new polynomial is created, add it to the list
    - output the message for confirming the operation
    - if there is no valid ID, output an error message
    */

    // case 2:
    private void evaluatePolynomial() {
        consoleOut.print("ID of polynomial to evaluate: \n$ ");
        int id = consoleIn.nextInt();
        Polynomial polynomial = polynomialList.getById(id);
        if (polynomial != null) {
            consoleOut.print("Value of x: \n$ ");
            double x = consoleIn.nextDouble();
            double result = polynomial.evaluate(x);
            consoleOut.println("y = " + result);
        } else {
            consoleOut.println("Polynomial with ID " + id + " not found.");
        }
    }

    // case 3:
    private void createNewPolynomial() {
        consoleOut.print("Degree of new polynomial: \n$ ");
        int degree = consoleIn.nextInt();
        double[] coefficients = new double[degree + 1];

        for (int i = 0; i <= degree; i++) {
            if (i == 0) consoleOut.print("Linear coefficient: \n$ ");
            else if (i == 1) consoleOut.print("Coefficient for factor x: \n$ ");
            else consoleOut.print("Coefficient for factor x^" + i + ": \n$ ");
            coefficients[i] = consoleIn.nextDouble();
        }

        Polynomial polynomial = new Polynomial(coefficients);
        polynomialList.add(polynomial);
        consoleOut.println("Created polynomial [" + polynomial.getID() + "] " + polynomial);
    }

    // case 4:
    private void addPolynomials() {
        consoleOut.print("ID of first polynomial to add: \n$ ");
        int id1 = consoleIn.nextInt();
        consoleOut.print("ID of second polynomial to add: \n$ ");
        int id2 = consoleIn.nextInt();

        Polynomial polynomial1 = polynomialList.getById(id1);
        Polynomial polynomial2 = polynomialList.getById(id2);

        if (polynomial1 != null && polynomial2 != null) {
            Polynomial sum = polynomial1.add(polynomial2);
            polynomialList.add(sum);
            consoleOut.println("Created polynomial [" + sum.getID() + "] " + sum);
        } else {
            consoleOut.println("One or both polynomials not found.");
        }
    }

    // case 5:
    private void calculateDerivative() {
        consoleOut.print("ID of polynomial to derive: \n$ ");
        int id = consoleIn.nextInt();
        Polynomial polynomial = polynomialList.getById(id);
        if (polynomial != null) {
            Polynomial derivative = polynomial.derivative();
            polynomialList.add(derivative);
            consoleOut.println("Created polynomial [" + derivative.getID() + "] " + derivative);
        } else {
            consoleOut.println("Polynomial with ID " + id + " not found.");
        }
    }

    // case 6:
    private void deletePolynomial() {
        consoleOut.print("ID of polynomial to delete: \n$ ");
        int id = consoleIn.nextInt();
        Polynomial polynomial = polynomialList.getById(id);
        if (polynomial != null) {
            polynomialList.delete(id);
            consoleOut.println("Deleted polynomial [" + polynomial.getID() + "] " + polynomial);
        } else {
            consoleOut.println("Polynomial with ID " + id + " not found.");
        }
    }
}
