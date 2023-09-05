package dz_4.Services;

import dz_4.Interfaces.ICashRepo;
import dz_4.Models.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class CashRepository implements ICashRepo {
    private static CashRepository cashRepository;

    private List<BankAccount> clients;

    public List<BankAccount> getClients() {
        return clients;
    }

    private CashRepository() {
        //имитация работы банка
        clients = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            clients.add(new BankAccount());
        }

    }

    public static CashRepository getCashRepository() {
        if (cashRepository == null) {
            cashRepository = new CashRepository();
        }
        return cashRepository;
    }

    @Override
    public boolean transaction(int payment, long cardFrom, long carrierСard) throws RuntimeException {

        BankAccount from = null;
        BankAccount to = null;
        for (var client : clients) {
            if (client.getCard() == cardFrom) {
                from = client;
            }
            if (client.getCard() == carrierСard) {
                to = client;
            }
        }

        if (from == null) {
            throw new RuntimeException("Нет счета для вывода средств.");
        }
        if (to == null) {
            throw new RuntimeException("Нет денежного счета.");
        }
        // Проверяем баланс средств на картах
        if (from.getBalance() - payment < 0) {
            throw new RuntimeException("Недостаточно средств.");
        }
        if (to.getBalance() > Integer.MAX_VALUE - payment) {
            throw new RuntimeException("Слишком много.");
        }

        try {
        } finally {
            clients.remove(from);
            clients.remove(to);
            from.setBalance(from.getBalance() - payment);
            to.setBalance(to.getBalance() + payment);
            clients.add(from);
            clients.add(to);
        }
        return true;
    }
}