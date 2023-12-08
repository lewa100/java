import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.Map.Entry.comparingByValue;

class PhoneBook {
    private static final HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String name, String phoneNum) {
        var list = phoneBook.get(name);
        if (list != null) {
            list.add(phoneNum);
            phoneBook.put(name, list);
            return;
        }
        var tmp = new ArrayList<String>();
        tmp.add(phoneNum);
        phoneBook.put(name,tmp);
    }

    public ArrayList<String> find(String name) {
        var list = phoneBook.get(name);
        if (list != null) {
            return list;
        }
        return new ArrayList<>();
    }

    public static HashMap<String, ArrayList<String>> getPhoneBook() {
// Введите свое решение ниже
        return phoneBook;
    }

    public static void sortPhoneBook(){
        var sorted = phoneBook.entrySet().stream()
                .sorted(comparingByValue(comparingInt(List::size)));
        System.out.println(Arrays.toString(Arrays.stream(sorted.toArray()).toArray()));
    }
}

public class Printer {
    public static void main(String[] args) {
        String name1,name2, name3;
        String  phone1,phone2,phone3, phone4;

        name1 = "Ivanov";
        name2 = "Petrov";
        name3 = "Sidorov";

        phone1 = "123456";
        phone2 = "654321";
        phone3 = "654321";
        phone4 = "654321";

        PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.add(name3, phone1);
        myPhoneBook.add(name3, phone2);
        myPhoneBook.add(name3, phone3);
        myPhoneBook.add(name3, phone4);

        myPhoneBook.add(name1, phone1);
        myPhoneBook.add(name2, phone2);
        myPhoneBook.add(name2, phone2);

        myPhoneBook.add(name1, phone3);
        myPhoneBook.add(name1, phone4);

        System.out.println(myPhoneBook.find(name1));
        System.out.println(PhoneBook.getPhoneBook());
        System.out.println(myPhoneBook.find("Petrov"));

        PhoneBook.sortPhoneBook();
    }
}
