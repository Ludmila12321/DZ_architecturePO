package dz_4.UserInteraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class EnterData {
    protected int inputInt(int minVariant, int maxVariant) throws RuntimeException {
        int num = 0;
        Scanner in = new Scanner(System.in);
        try {
            num = in.nextInt();
            in.close();
        } catch (InputMismatchException ex) {
            throw new RuntimeException("Это не число.");
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так...");
        }
        if (num < minVariant || num > maxVariant) {
            throw new RuntimeException("Вы ввели недопустимое значение. Не надо так.");
        }
        return num;
        }

    protected long inputLong(long minVariant, long maxVariant) throws RuntimeException {
        long num = 0;
        Scanner in = new Scanner(System.in);
        try {
            num = in.nextLong();
            in.close();
        } catch (InputMismatchException ex) {
            throw new RuntimeException("Это не число.");
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так...");
        }
        if (num < minVariant || num > maxVariant) {
            throw new RuntimeException("Вы ввели недопустимое значение. Не надо так.");
        }
        return num;
    }

    protected String inputString() throws RuntimeException {
        Scanner in = new Scanner(System.in);
        String str;
        try {
            str = in.next();
            in.close();
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так...");
        }
        if (str.isEmpty()) {
            throw new RuntimeException("Здесь должно что-то быть.");
        }
        return str;
    }

    protected Date inputDate() throws RuntimeException {
        SimpleDateFormat ft = new SimpleDateFormat("гггг-мм-дд");
        Scanner in = new Scanner(System.in);
        String str;
        Date date;
        try {
            str = in.nextLine();
            in.close();
        } catch (Exception ex) {
            throw new RuntimeException("Что-то пошло не так...");
        }
        if (str.isEmpty()) {
            throw new RuntimeException("Здесь должно что-то быть.");
        }
        try {
            date = ft.parse(str);
        } catch (ParseException ex) {
            throw new RuntimeException("Введите дату.");
        }
        return date;
    }
    
}
