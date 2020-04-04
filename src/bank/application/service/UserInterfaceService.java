package bank.application.service;

import bank.application.model.AuthenticationModel;

public class UserInterfaceService {
    private InputService inputService = new InputService();

    // this class will implement the user interaction logic (get input data
    // and print different things accordingly

    // some suggestions from me

    public void printWelcomeMenu() {
        System.out.println("Hello! Welcome to the BankApplication");
        System.out.println("Please enter your credentials");
    }

    public AuthenticationModel getCredentialsFromUser() {
        System.out.print("Username: ");
        String username = inputService.readInput();
        inputService.addNewLine();
        String password = inputService.readInput();

        // return new AuthenticationModel(username, password);
        return null;
    }

    // etc. Think of different scenarios which would need this kind of interactions with the user
    // and express them in methods
}
