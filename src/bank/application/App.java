package bank.application;

import bank.application.model.UserModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
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
