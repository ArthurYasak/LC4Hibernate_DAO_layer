package application.main;

import application.dao.UserDAOImpl;
import application.models.Bet;
import application.models.Lot;
import application.models.User;
import application.models.UserType;

public class Main {
    public static void main(String[] args) {
        User user1 = new User(UserType.GUEST);
        user1.setUserBalance(1_000.0);
        user1.setUserType(UserType.ADMIN);

        User user2 = new User(UserType.USER);
        user2.setUserBalance(12_000.0);

        Lot lot1 = new Lot();
        lot1.setMinPrice(100.0);
        lot1.setUserOwner(user1);

        Lot lot2 = new Lot();
        lot2.setCurrentPrice(2_000.0);
        lot2.setUserOwner(user2);
        // lot2.setLastCustomer(user1);

        Bet bet1 = new Bet(200.0);

        // bet1.setUserOwner(user1);
        bet1.setUserOwner(user2);

        System.out.println("POJO User: " + user1);

        UserDAOImpl userDAO = new UserDAOImpl();

        System.out.println("Delete ALL: " + userDAO.deleteAll());

        System.out.println("User was add: " + userDAO.add(user1));
        User updUser1 = userDAO.update(user1);
        System.out.println("Updated User 1: " + updUser1);

        System.out.println("User was add: " + userDAO.add(user2));
        User updUser2 = userDAO.update(user2);
        System.out.println("Updated User 2: " + updUser2);

        // System.out.println("Get user by id: " + userDAO.getById(8));

        System.out.println("All users:\n" + userDAO.getAll());


        // UserManager userManager = new UserManager();
        // userManager.init();
        // System.out.println(userManager.saveUser(user1));
        // List<User> users = userManager.getAllUsers();
        // System.out.println(users);

        // User userForUpdate = userManager.getUserById(3);
        // userForUpdate.setUserType(UserType.USER);
        // System.out.println(userForUpdate.getUserId());
        // System.out.println(userForUpdate.getUserType());
        // userManager.updateUser(userForUpdate);

        // userManager.removeUser(userManager.getUserById(11));
    }
}