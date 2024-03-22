package chat;

import java.util.Scanner;

public final class Command {
    public static Scanner sc = new Scanner(System.in);

    public static String name() {
        String name = sc.nextLine();
        while(name == null || name == "") {
            clearScreen();
            System.out.println("Digite um nome válido: ");
            name = sc.nextLine();
        }
        while(name.length() < 3) {
            clearScreen();
            System.out.println("Seu nome precisa ter 3 ou mais caracteres. ");
            System.out.println("Digite um nome válido: ");
            name = sc.nextLine();
        }
        return name;
    }

    public static String message() {
        String message = sc.nextLine();
        return message;
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
