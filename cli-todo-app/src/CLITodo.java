import java.io.*;
import java.util.*;

public class CLITodo {

    static final String FILE_NAME = "todo.txt";
    static Scanner scanner = new Scanner(System.in);
    static List<String> todos = new ArrayList<>();

    public static void main(String[] args) {
        loadTodos();

        while (true) {
            menu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addTodo();
                case 2 -> listTodos();
                case 3 -> deleteTodo();
                case 4 -> completeTodo();
                case 0 -> {
                    saveTodos();
                    System.out.println("Çıkış yapıldı.");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    static void menu() {
        System.out.println("\n--- CLI Todo Uygulaması ---");
        System.out.println("1- Görev Ekle");
        System.out.println("2- Görevleri Listele");
        System.out.println("3- Görev Sil");
        System.out.println("4- Görev Tamamla");
        System.out.println("0- Çıkış");
        System.out.print("Seçiminiz: ");
    }

    static void addTodo() {
        System.out.print("Görev gir: ");
        String todo = "[ ] " + scanner.nextLine();
        todos.add(todo);
        System.out.println("Görev eklendi.");
    }

    static void listTodos() {
        if (todos.isEmpty()) {
            System.out.println("Görev yok.");
            return;
        }
        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i));
        }
    }

    static void deleteTodo() {
        listTodos();
        System.out.print("Silinecek görev numarası: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < todos.size()) {
            todos.remove(index);
            System.out.println("Görev silindi.");
        } else {
            System.out.println("Hatalı numara!");
        }
    }

    static void completeTodo() {
        listTodos();
        System.out.print("Tamamlanan görev numarası: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < todos.size()) {
            String task = todos.get(index).replace("[ ]", "[✔]");
            todos.set(index, task);
            System.out.println("Görev tamamlandı.");
        } else {
            System.out.println("Hatalı numara!");
        }
    }

    static void loadTodos() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                todos.add(line);
            }
        } catch (IOException ignored) {}
    }

    static void saveTodos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String todo : todos) {
                bw.write(todo);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Dosya kaydedilemedi!");
        }
    }
}
