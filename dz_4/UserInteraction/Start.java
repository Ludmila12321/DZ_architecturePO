package dz_4.UserInteraction;

import dz_4.Core.Customer;
import dz_4.Interfaces.ICustomer;
import dz_4.Models.Ticket;

import java.util.Date;
import java.util.List;

public class Start extends EnterData {
    // Связь с основной логикой осуществляется через интерфейс ICustomer.
    private ICustomer customer;
    private int ticketRouteNumber;
    private Date ticketDate;

    public void runLoginRegisterMenu() {
        boolean run = true;
        while (run) {
            printMessageLine("Приложение для покупки билетов");
            printMessageLine("Это тестовая версия. База данных недоступна в полном режиме.");
            printMessageLine("Для входа в систему\t\t\tНажмите 1\nДля регистрации\t\t\tНажмите 2\nДля выхода\t\t\t\tНажмите 0");
            System.out.print("Введите свой выбор > ");
            int choice = 0;
            try {
                choice = inputInt(0, 2);
            } catch (RuntimeException ex) {
                System.err.println(ex.getMessage());
                continue;
            }
            run = runLoginRegisterMenuChoiceLogic(choice);
        }
    }

    private boolean runLoginRegisterMenuChoiceLogic(int choice) {
        switch (choice) {
            case 1:
                login();
                if (customer.getUser() == null) {
                    break;
                } else {
                    runBuyingMenu();
                    break;
                }
            case 2:
                register();
                if (customer == null) {
                    break;
                } else {
                    runBuyingMenu();
                    break;
                }
            default:
                return false;
        }
        return true;
    }

    private void login() {
        printMessageLine("Это тестовая версия. База данных не подключена.");
        printMessageLine("Логин");
        System.out.print("Имя пользователя: ");
        String userName = inputString();
        System.out.print("Пароль: ");
        int passwordHash = inputString().hashCode();
        System.out.print("Войдите в систему... ");
        customer = new Customer();
        try {
            customer.setUser(Authentication.authentication(customer.getUserProvider(), userName, passwordHash));
        } catch (RuntimeException ex) {
            System.out.println("Ошибка!");
            System.out.println(ex.getMessage());
            return;
        }
        printMessageLine("OK");
    }

    private void register() {
        printMessageLine("Это тестовая версия. База данных не подключена.");
        printMessageLine("Регистрация");
        System.out.print("Введите имя пользователя: ");
        String userName = inputString();
        System.out.print("Введите пароль: ");
        int passwordHash = inputString().hashCode();
        System.out.print("Повторите пароль: ");
        int passwordHash2 = inputString().hashCode();
        if (passwordHash != passwordHash2) {
            printMessageLine("Пароли не совпадают. Повторите заново.");
            return;
        }
        System.out.print("Введите номер карты: ");
        long cardNumber = inputLong(1L, 9999_9999_9999_9999L);
        System.out.print("Регистрация в системе... ");
        customer = new Customer();
        int id;
        try {
            id = customer.getUserProvider().createClient(userName, passwordHash, cardNumber);
            customer.setUser(Authentication.authentication(customer.getUserProvider(), userName, passwordHash));
        } catch (RuntimeException ex) {
            System.out.println("Ошибка!");
            System.out.println(ex.getMessage());
            return;
        }
        printMessageLine("Всё хорошо, пользователь " + customer.getUser().getUserName() + " с ID " + id + "добавлен в базу.");
    }

    private void runBuyingMenu() {
        boolean run = true;
        while (run) {
            printMessageLine("Приложение для покупки билетов. | Пользователь " + customer.getUser().getUserName() + " |");
            printMessageLine("Чтобы выбрать номер маршрута и распечатать все билеты\tНажмите 1\n" +
                    "Для выхода из системы\t\t\t\t\t\t\t\t\t\tНажмите 0");
            System.out.print("Ваш выбор > ");
            int choice = 0;
            try {
                choice = inputInt(0, 1);
            } catch (RuntimeException ex) {
                printMessageLine(ex.getMessage());
                continue;
            }
            run = runBuyingMenuChoiceLogic(choice);
        }
    }

    private boolean runBuyingMenuChoiceLogic(int choice) {
        switch (choice) {
            case 1:
                ticketRouteNumber = runSelectRouteMenu();
                if (ticketRouteNumber > 0) {
                    ticketDate = runSelectDate();
                    if (ticketDate != null) {
                        try {
                            customer.setSelectedTickets(customer.searchTicket(ticketDate, ticketRouteNumber));
                        } catch (RuntimeException ex) {
                            printMessageLine(ex.getMessage());
                            return true;
                        }
                        printAllTickets(customer.getSelectedTickets());
                        buyTicketMenu();
                        return true;
                        //return;
                    }
                    return true;
                }
                return true;
            default:
                return false;
        }
    }

    private int runSelectRouteMenu() {
        printMessageLine("Введите номер маршрута и дату. | Пользователь " + customer.getUser().getUserName() + " |");
        System.out.print("Номер маршрута > ");
        //Здесь ограничиваем число маршрутов. У нас всего 2 маршрута.
        int numRoute;
        try {
            numRoute = inputInt(1, 2);
        } catch (RuntimeException ex) {
            printMessageLine(ex.getMessage());
            return -1;
        }
        return numRoute;
    }

    private Date runSelectDate() {
        System.out.print("Дата (формат: ГГГГ-MM-ДД) > ");
        //Здесь ограничиваем число маршрутов. У на всего 2 маршрута.
        Date date;
        try {
            date = inputDate();
        } catch (RuntimeException ex) {
            printMessageLine(ex.getMessage());
            return null;
        }
        return date;
    }

    private void printAllTickets(List<Ticket> ticks) {
        for (var t : ticks) {
            System.out.println(t.toString());
        }
    }

    private void buyTicketMenu() {
        printMessageLine("Подтвердите покупку. | Пользователь " + customer.getUser().getUserName() + " |");
        System.out.print("Чтобы купить билет " + ticketRouteNumber + " на дату " + ticketDate + " введите" +
                " \"Да\" > ");
        String answer = inputString();
        buyTicketMenuConfirmLogic(answer);
    }

    private void buyTicketMenuConfirmLogic(String answer) {
        if (answer.equalsIgnoreCase("ДА")) {
            for (var t : customer.getSelectedTickets()) {
                if (t.getDate().equals(ticketDate) && t.getRouteNumber() == ticketRouteNumber && t.getValid()) {
                    boolean flag = false;
                    try {
                        flag = customer.buyTicket(t);
                    } catch (RuntimeException ex) {
                        printMessageLine(ex.getMessage());
                        return;
                    }
                    if (flag) {
                        printMessageLine(t.toPrint());
                        return;
                    }
                }
            }
        }
    }

    private void printMessageLine(String message) {
        System.out.println(message);
    }
}