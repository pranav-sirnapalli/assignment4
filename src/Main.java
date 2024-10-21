import controller.ImageController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ImageController controller = new ImageController();

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            controller.processCommand(command);
        }
    }
}
