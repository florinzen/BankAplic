package bank.application.service;

import bank.application.model.AuthenticationModel;

public class CredentialsService {
    private String pathToFile; // path to the credentials file

    //Constructor

    public boolean authenticateUser(AuthenticationModel credentials) {
        // Implement a way in which you check if the username/password combination is found in the credentialsFile
        // this methods will return true if the username/password matched an entry from the file
        // false otherwise
        return false;
    }
}
