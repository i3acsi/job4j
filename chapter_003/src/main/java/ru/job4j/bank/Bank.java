package ru.job4j.bank;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


enum Bank {
    INSTANCE;

    public static Bank getInstance() {
        return INSTANCE;
    }

    private static AtomicLong idCounter = new AtomicLong();

    private String generatePassport() {
        return String.valueOf(idCounter.getAndIncrement());
    }

    /**
     * HashMap clients - коллекция содержащая ключ - юзера и список его счетов.
     * иными словами - у каждого пользователя может быть список банковских счетов.
     */
    private Map<User, List<Account>> clients = new HashMap<>();

    private final User noNameUser = new User("no name");


    /**
     * Добавление пользователя
     *
     * @param name -  имя нового пользователя
     */
    public User addUser(String name) {
        User user;
        if (name != null) {
            user = new User(name);
            String passport = this.generatePassport();
            user.setPassport(passport);
            clients.putIfAbsent(user, new ArrayList<>());
        } else {
            user = noNameUser;
            clients.putIfAbsent(user, new ArrayList<>());
        }
        return user;
    }

    /**
     * Удаление пользователя
     *
     * @param user - пользователь
     */
    public void deleteUser(User user) {
        clients.remove(user);
    }

    /**
     * Добавить счёт пользователю.
     *
     * @param passport - паспорт пользователя
     * @param account  - счет пользователя
     */
    public void addAccountToUser(String passport, Account account) {
        String requisites = UUID.randomUUID().toString();
        account.setRequisites(requisites);
        User user = findUser(passport);
        clients.getOrDefault(user, new ArrayList<>()).add(account);
    }

    /**
     * Удалить один счёт пользователя.
     *
     * @param passport - паспорт пользователя
     * @param account  - счет пользователя
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUser(passport);
        clients.getOrDefault(user, new ArrayList<>()).remove(account);
    }

    /**
     * Получить список счетов для пользователя.
     *
     * @param passport - паспорт пользователя.
     * @return List<Account> - список счетов клиента.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result;
        User user = findUser(passport);
        result = clients.get(user);
        return result;
    }

    /**
     * @param srcPassport  - паспорт клинта, со счета котрого будет сделан перевод
     * @param srcRequisite - реквизиты счета списания
     * @param destPassport - паспорт клинта, на счет котрого будет сделан перевод
     * @param dstRequisite - реквизиты счета зачисления
     * @param amount       - сумма денег для перевода
     * @return - выполнен ли перевод (true/false).
     */
    public boolean transferMoney(String srcPassport,
                                 String srcRequisite,
                                 String destPassport,
                                 String dstRequisite,
                                 double amount) {
        boolean result = false;
        User srcUeser = findUser(srcPassport);
        User destUser = findUser(destPassport);
        Account srcAccount = findAccount(srcPassport, srcRequisite);
        Account destAccount = findAccount(destPassport, dstRequisite);
        return srcAccount.transfer(destAccount, amount);
    }

    public User findUser(String passport) {
        return clients.keySet().stream().filter(x -> passport.equals(x.getPassport())).findAny().orElse(noNameUser);
    }

    private Account findAccount(String passport, String requisite) {
        return getUserAccounts(passport).stream().filter(x -> requisite.equals(x.getRequisites())).findAny().orElse(new Account(0));
    }

    public void clean() {
        clients.clear();
    }

    public List<User> findAllUsers() {
        return new ArrayList<>(clients.keySet());
    }
}
