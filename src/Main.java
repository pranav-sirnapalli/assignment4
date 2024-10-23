import controller.ImageController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ImageController controller = new ImageController();
    controller.run(scanner);
  }
}
