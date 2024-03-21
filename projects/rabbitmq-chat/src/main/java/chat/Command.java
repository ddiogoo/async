package chat;

import java.util.Scanner;

public final class Command {
    /**
     * Obtain a user's valid name.
     * @return User name [String].
     */
    public static String getName() {
        Scanner sc = new Scanner(System.in);
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
        sc.close();
        return name;
    }

    /**
     * Cleans the terminal.
     */
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
