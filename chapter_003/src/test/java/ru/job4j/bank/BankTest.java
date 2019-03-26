package ru.job4j.bank;

import org.junit.After;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private final Bank bank = Bank.getInstance();
    private final User[] users = {
            bank.addUser("Petr"),
            bank.addUser("Vasiliy"),
            bank.addUser("Ivan"),
            bank.addUser("Masha"),
            bank.addUser("Semen"),
            bank.addUser("Vova")
    };

    @After
    public void clean() {
        bank.clean();
    }

    @Test
    public void addDeleteUserTest() {
        int before = users.length;
        User user = bank.addUser("Test");
        String passport = user.getPassport();
        int after = bank.findAllUsers().size();
        assertThat(before + 1, is(after));
        assertThat(bank.findUser(passport), is(user));
        bank.deleteUser(user);
        int nextAfter = bank.findAllUsers().size();
        assertThat(before, is(nextAfter));
    }

    @Test
    public void addDeleteAccountTest() {
        String passport = users[0].getPassport();
        Account account = new Account(100);
        bank.addAccountToUser(passport, account);
        assertThat(bank.getUserAccounts(passport).contains(account), is(true));
        bank.deleteAccountFromUser(passport, account);
        assertThat(bank.getUserAccounts(passport).contains(account), is(false));
    }

    @Test
    public void transferMoneyTest() {
        String srcPassport = users[0].getPassport();
        String destPassport = users[1].getPassport();
        Account srcAccount = new Account(100);
        Account destAccount = new Account(0);
        bank.addAccountToUser(srcPassport, srcAccount);
        bank.addAccountToUser(destPassport, destAccount);
        bank.transferMoney(srcPassport, srcAccount.getRequisites(), destPassport, destAccount.getRequisites(), 50);
        assertThat(srcAccount.getValue(), is(destAccount.getValue()));
    }

    @Test
    public void npeTest() {
        User user = new User("Test");
        bank.deleteUser(user);
        String passport = user.getPassport();
        Account account = new Account(0);
        bank.addAccountToUser(passport, account);
        bank.deleteAccountFromUser(passport, account);
        bank.getUserAccounts(passport);
        bank.findUser(passport);
        bank.clean();
        bank.findAllUsers();
    }

}
