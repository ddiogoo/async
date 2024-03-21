package chat;

public class Client {
    public static void main(String[] args) {
        System.out.print("Digite seu nome: ");
        String name = Command.getName();
        System.out.println("Nome informado: " + name);
    }
}
