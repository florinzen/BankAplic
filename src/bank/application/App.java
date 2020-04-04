package bank.application;

import bank.application.model.UserModel;
import bank.application.service.AccountService;
import bank.application.service.CredentialsService;
import bank.application.service.UserInterfaceService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    // TODO Inside main we have to do some refactoring
    /*
        The current implementation checks the username/pass one at a time
        We need to change that. To user the userInterfaceService and credentialsService for this
        One for retrieving the data from the user the other one for validating the input with the credentials.txt file

        However, we can test our implementation incrementally
        After finishing implementing the methods from a service, test it in a main function
        Check if, given input data, the methods you have implemented return the expected results

        For example:
            public static void main(String[] args) {
                CredentialsService credentialsService = new CredentialsService();
                AuthenticationModel a = new AuthenticationModel("user1", "parola");
                credentialsService.authenticateUser(a) // This method should return true if user1/parola is an entry from credentials.txt

                AuthenticationModel b = new AuthenticationModel("invalidUsername", "invalidPassword");
                credentialsService.authenticateUser(b) // This method should return false

                AuthenticationModel c = new AuthenticationModel("invalidUsername", "validPassword");
                credentialsService.authenticateUser(c) // This method should return false

                AuthenticationModel d = new AuthenticationModel("validUsername", "invalidPassword");
                credentialsService.authenticateUser(d) // This method should return false
            }

        The same logic can be applied for the rest of our services. Test each one, one by one, before trying to glue everything together
     */
    public static void main(String[] args) {
        // First off, when the application starts, we need to load all the contents from the file.
        // args[0] -> calea catre fisierul de credentiale
        Map<String, UserModel> availableUsers = loadUserData("resources/credentials.txt");

        Scanner input = new Scanner(System.in);

        boolean isUserLoggedIn = false;
        boolean userCancelled = false;

        do {
            System.out.println("Please enter your username");
            String usernameInput = input.nextLine();
            if(usernameInput.equals("exit")) {
                userCancelled = true;
            }
            if(availableUsers.containsKey(usernameInput)) {
                // Check password
                System.out.println("Please enter your password");
                String passwordInput = input.nextLine();

                UserModel userModelToBeLoggedIn = availableUsers.get(usernameInput);
                if(userModelToBeLoggedIn.getPassword().equals(passwordInput)) {
                    System.out.println("Welcome back " + usernameInput);
                    System.out.println("You are now logged in");
                    isUserLoggedIn = true;
                    // metoda care afiseaza meniul aplicatiei
                } else {
                    System.err.println("Wrong password. Please try again.");
                    // Throw invalid password exception
                }
            } else {
                System.err.println("User not allowed to log in; Please contact service desk or enter \"exit\" to cancel!");
            }
        } while(!(isUserLoggedIn || userCancelled));
    }


    private static Map<String, UserModel> loadUserData(String pathToFile) {
        Map<String, UserModel> availableUsers = new HashMap<>();
        try (BufferedReader bufInput = new BufferedReader(new FileReader(pathToFile))) {
            String userDataRaw;
            while((userDataRaw = bufInput.readLine()) != null) {
                String[] userDataParsed = userDataRaw.split("-");
                availableUsers.put(userDataParsed[0], new UserModel(userDataParsed[0], userDataParsed[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return availableUsers;
    }
}
