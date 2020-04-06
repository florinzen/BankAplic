package bank.application.service;

import bank.application.model.AccountModel;

import java.util.List;

public class AccountService {

    // The generated files should be saved to the resources folder

    public void persistAccountsToFile(String username, List<AccountModel> accounts) {
        // this method will serialize the list of accounts in a file named like the username who owns the accounts
        // this method will be invoked after a user logs out, in order to save any updates the user made during a sessions
    }

    public void loadAccountsFromFile(String username) {
        // this method will deserialize the accounts a user own
        // the file name will be the username
    }
}
