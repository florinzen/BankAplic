package bank.application.service;

import java.util.Scanner;

public class InputService {
    private Scanner scanner;

    // Constructor which initializes the scanner.
    // How should it be initialized? Well we will use the scanner so that we get input from the keyboard
    public InputService() {
        scanner = new Scanner(System.in);
    }

    // readInput is a method
    public String readInput() {
        // read the input from user and return it, using the scanner
        return "What the user typed";
    }

    public void addNewLine() {
        System.out.println();
    }

    public void closeScanner() {
        // close the scanner
    }
}
