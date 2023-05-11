package application.main;

import application.dao.UserDAOImpl;
import application.exceptions.DAOException;
import application.models.*;

import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        UserDAOImpl userDAO = new UserDAOImpl();

        try {
            userDAO.deleteAll();
            System.out.println("ALL users was DELETE \n");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("users was NOT delete \n");
        }

        User user1 = new User(UserType.GUEST);
        user1.setUserBalance(1_000.0);

        UserData user1Data = new UserData();
        user1Data.setAge(26);
        user1Data.setName("Arthur");
        user1Data.setSurname("Yasak");
        user1.setUserData(user1Data);

        // Optional.ofNullable(user1).ifPresentOrElse(
        //             u -> Optional.ofNullable(u.getUserData()).ifPresentOrElse(
        //                     ud -> System.out.printf("POJO user %s : %s\n", ud.getName(), u),
        //                     () -> System.out.println("userData is NULL")
        //             ),
        //             () -> System.out.println("user is NULL")
        // );

        var value1 = Optional.ofNullable(user1)
                .map(User::getUserData)
                .map(u -> String.format("POJO user %s is: %s\n", u.getName(), u))
                .orElse("POJO user 1 is NULL");
        System.out.println(value1);

        try {
            userDAO.add(user1);
            System.out.println("User was add: " + user1 + '\n');
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User was NOT add " + '\n');
        }

        user1.setUserType(UserType.ADMIN);

        AuthorizationData user1Authorization = new AuthorizationData();
        user1Authorization.setLogin("root");
        user1Authorization.setPassword("root");
        user1.setAuthorizationData(user1Authorization);

        try {
            user1 = userDAO.update(user1);
            System.out.printf("User with id %d was update: %s\n", user1.getUserId(), user1);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user1.getUserId() + " was NOT update \n ");
        }

        // user1.setUserType(UserType.USER);
        // userDAO.update(user1);   // If CascadeType.ALL: EntityNotFoundException deleted object would be re-saved by cascade (remove deleted object from associations): [application.models.UserData#81]

        User user2 = new User(UserType.USER);
        user2.setUserBalance(12_000.0);

        UserData user2Data = new UserData();
        user2Data.setAge(25);
        user2Data.setName("Ivan");
        user2Data.setSurname("Bogush");
        user2.setUserData(user2Data);

        AuthorizationData user2Authorization = new AuthorizationData();
        user2Authorization.setLogin("root");
        user2Authorization.setPassword("root");
        user2.setAuthorizationData(user2Authorization);


        // Optional.ofNullable(user2).ifPresentOrElse(
        //         u -> Optional.ofNullable(u.getUserData()).ifPresentOrElse(
        //                 ud -> System.out.printf("POJO user %s : %s\n", ud.getName(), u),
        //                 () -> System.out.println("userData is NULL")
        //         ),
        //         () -> System.out.println("user is NULL")
        // );
        var value2 = Optional.ofNullable(user2)
                .map(User::getUserData)
                .map(u -> String.format("POJO user %s is: %s\n", u.getName(), u))
                .orElse("POJO user 2 is NULL");
        System.out.println(value2);

        try {
            userDAO.add(user2);
            System.out.println("User was add: " + user2 + '\n');
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User was NOT add " + '\n');
        }

        try {
            user2 = userDAO.update(user2);
            System.out.printf("User with id %d was update: %s\n", user2.getUserId(), user2);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user2.getUserId() + " was NOT update \n ");
        }

        Lot lot1 = new Lot();
        lot1.setMinPrice(100.0);
        user1.addUserLot(lot1);

        try {
            user1 = userDAO.update(user1);
            System.out.printf("User with id %d was update: %s\n", user1.getUserId(), user1);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user1.getUserId() + " was NOT update \n ");
        }

        LotProperty lot1Property = new LotProperty();
        lot1Property.setDescription("JBL Flip 5");
        lot1Property.setWeight(600);
        lot1.setProperty(lot1Property);

        try {
            user1 = userDAO.update(user1);
            System.out.printf("User with id %d was update: %s\n", user1.getUserId(), user1);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user1.getUserId() + " was NOT update \n ");
        }

        Commission lot1Commission = new Commission();
        lot1Commission.setCommissionPercent(12.34);
        lot1.setCommission(lot1Commission);

        try {
            user1 = userDAO.update(user1);
            System.out.printf("User with id %d was update: %s\n", user1.getUserId(), user1);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user1.getUserId() + " was NOT update \n ");
        }

        Lot lot2 = new Lot();
        lot2.setCurrentPrice(2_000.0);
        user2.addUserLot(lot2);

        try {
            user2 = userDAO.update(user2);
            System.out.printf("User with id %d was update: %s\n", user2.getUserId(), user2);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user2.getUserId() + " was NOT update \n ");
        }

        Bet bet1 = new Bet(200.0);
        user1.addUserBet(bet1);

        // Checking that user1 will not an owner of bet when user 2 will be owner of it
        System.out.println("USER 1: " + user1);
        user2.addUserBet(bet1);
        System.out.println("USER 1: " + user1);

        try {
            user2 = userDAO.update(user2);
            System.out.printf("User with id %d was update: %s\n", user2.getUserId(), user2);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user2.getUserId() + " was NOT update \n ");
        }

        user1.stayLastCustomerIn(lot2);

        try {
            user1 = userDAO.update(user1);
            System.out.printf("User with id %d was update: %s\n", user1.getUserId(), user1);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user1.getUserId() + " was NOT update \n ");
        }

        // Checking that last customer was updated
        System.out.println("USER 2: " + user2);

        try {
            user2 = userDAO.update(user2);
            System.out.printf("User with id %d was update: %s\n", user2.getUserId(), user2);
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User: " + user2.getUserId() + " was NOT update \n ");
        }

        int requiredId = 18;
        try {
            User userById = userDAO.getById(requiredId);
            System.out.println(userById != null ?
                    ("User by id: " + requiredId + ": " + userById + '\n') :
                    ("There's NO user with id: " + requiredId + '\n'));
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("Can NOT find user with id: " + requiredId + '\n');
        }

        int deletedId = 19;
        try {
            userDAO.deleteById(deletedId);
        } catch(DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("User with id: " + deletedId + " was NOT deleted \n");
        }

        try {
            userDAO.getFirstUser();
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("Can NOT return first user \n");
        }

        try {
            userDAO.getLastUser();
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("Can NOT return last user \n");
        }

        try {
            userDAO.getAll();
        } catch (DAOException e) {
            e.getMessage();
            e.printStackTrace();
            System.out.println("Can NOT print all users");
        }

    }
}