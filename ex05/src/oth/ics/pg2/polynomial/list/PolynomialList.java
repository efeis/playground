package oth.ics.pg2.polynomial.list;

import oth.ics.pg2.polynomial.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class PolynomialList {

    // declares a list variable and a counter
    private final List<Polynomial> polynomialList;
    private int counter;
        public PolynomialList() {
            polynomialList = new ArrayList<>();
            counter = 1;
        }

    // returns the size of the list
    public int size() {
        return polynomialList.size();
    }

    // returns the polynomial at the given index in the list
    public Polynomial getByIndex(int index) {
        if (index >= 0 && index < polynomialList.size())
            return polynomialList.get(index);
        else
            return null;
    }

    // returns the polynomial with the specified ID
    public Polynomial getById(int id) {
        for (Polynomial polynomial : polynomialList) {
            if (polynomial.getID() == id)
                return polynomial;
        }
        return null;
    }

    // adds a new polynomial to the list
    public void add(Polynomial polynomial) {
        polynomialList.add(polynomial);
    }

    // deletes the polynomial with the specified ID
    public void delete(int ID) {
        for (int i = 0; i < polynomialList.size(); i++) {
            if (polynomialList.get(i).getID() == ID) {
                polynomialList.remove(i);
                return;
            }
        }
    }

    // displays all the polynomials in the list
    public void showCurrentPolynomials() {
        for (Polynomial polynomial : polynomialList) {
            System.out.println("[" + polynomial.getID() + "] " + polynomial);
        }
    }

    // generates and returns the next unique ID for the next polynomial
    public int getNextID() {
        return counter++;
    }
}
