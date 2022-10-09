import java.util.*;


public class Main {
    static int CAPACITY = 10;
    static int MAX_INT = 15;
    static boolean ASCENDING_SORT = true;
    static int FILTER_MULTIPLIES_OF = 3;

    static String[] main_menu = {
            "Задание 1",
            "Задание 2",
            "Выход"
    };

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        Scanner scanner = new Scanner(System.in);
        int number;
        boolean flag = true;
        while (flag) {
            printMenuOptions(main_menu);
            try {
                number = scanner.nextInt();
                scanner.nextLine();
                switch (number) {
                    case 1 -> task1(CAPACITY, MAX_INT, ASCENDING_SORT, FILTER_MULTIPLIES_OF);
                    case 2 -> task2(CAPACITY, MAX_INT);
                    case 3 -> {
                        flag = false;
                        System.out.println("До свидания!");
                    }
                    default -> System.out.println("Введите целое число в промежутке от 0 до " + (main_menu.length - 1));
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Введите целое число");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void task1(int capacity, int max_int, boolean ascending_sort, int filter_num) {
        ArrayList<Integer> random_numbers = listGenerator(capacity, max_int);
        Collections.addAll(random_numbers);
        random_numbers.forEach(element -> System.out.print(element + " "));
        System.out.println();
        if (ascending_sort) {
            random_numbers.stream()
                    .sorted()
                    .forEach(element -> System.out.print(element + " "));
        } else {
            random_numbers.stream()
                    .sorted(Collections.reverseOrder())
                    .forEach(element -> System.out.print(element + " "));
        }
        System.out.println();
        random_numbers.stream()
                .filter(element -> element % filter_num != 0)
                .forEach(element -> System.out.print(element + " "));
        System.out.println();
        List<String> toStr = random_numbers.stream()
                .map(element -> element.toString())
                .toList();
        toStr.forEach(element -> System.out.print(element + " "));
        System.out.println();
    }

    private static void task2(int capacity, int max_int) {
        int x = (int) (Math.random() * (max_int * 2 + 1)) - max_int;
        ArrayList<Integer> random_list = listGenerator(capacity, max_int);
        System.out.println("X = " + x);
        System.out.println("До:\n" + random_list);
        for (int i = 0; i < capacity - 1; i++) {
            for (int j = i + 1; j < capacity; j++) {
                if (random_list.get(j) < random_list.get(i)) {
                    random_list.set(j, random_list.get(j) + random_list.get(i));
                    random_list.set(i, random_list.get(j) - random_list.get(i));
                    random_list.set(j, random_list.get(j) - random_list.get(i));
                }
            }
        }
        /*for (int i = 0; i < capacity - 1; i++) {
            for (int j = i + 1; j < capacity; j++) {
                if (random_list.get(j) < x) {
                    random_list.set(j, random_list.get(j) + random_list.get(i));
                    random_list.set(i, random_list.get(j) - random_list.get(i));
                    random_list.set(j, random_list.get(j) - random_list.get(i));
                }
            }
        }*/
        System.out.println("После:\n" + random_list);
    }

    private static ArrayList<Integer> listGenerator(int capacity, int max_int) {
        ArrayList<Integer> generated_list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            generated_list.add(i, (int) (Math.random() * (max_int * 2 + 1)) - max_int);
        }
        return generated_list;
    }

    private static void printMenuOptions(String[] options) {
        System.out.println("Выберите пункт меню:");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ") " + options[i]);
        }
    }
}
