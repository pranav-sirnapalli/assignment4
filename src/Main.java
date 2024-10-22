import controller.ImageController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ImageController controller = new ImageController();
    System.out.println("Current working directory: "+System.getProperty("user.dir"));
    try{
      if (args.length == 1) {
        String scriptPath = args[0];
        runScript(scriptPath,controller);
      } else {
        while (true) {
          System.out.print("Enter command: ");
          String command = scanner.nextLine();
          controller.processCommand(command);
        }
      }
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
  }

  public static void runScript(String scriptPath, ImageController controller) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(scriptPath));
    String line;
    while ((line = reader.readLine()) != null) {
      controller.processCommand(line);
    }
    reader.close();
  }
}
