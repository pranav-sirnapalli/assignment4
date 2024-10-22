package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import model.ImageModel;

public class ImageController {

  private ImageModel imageModel;

  public ImageController() {

    this.imageModel = new ImageModel();
  }

  public void run(String[] args, Scanner scanner) {
    System.out.println("Current working directory: " + System.getProperty("user.dir"));

    while (true) {
      System.out.print("Enter command: ");
      String command = scanner.nextLine();
      this.processCommand(command);
    }

  }

  private void processCommand(String command) {
    // Skip empty lines or comments
    if (command.isEmpty() || command.startsWith("#")) {
      return;
    }
    String[] tokens = command.split(" ");
    switch (tokens[0]) {
      case "load":
        if (tokens.length != 3) {
          System.out.println("Usage: load <input_file_path> <reference_name>");
        } else {
          imageModel.loadImage(tokens[1], tokens[2]);
        }
        break;
      case "save":
        if (tokens.length != 3) {
          System.out.println("Usage: save <save_path> <output_name>");
        } else {
          imageModel.saveImage(tokens[1], tokens[2]);
        }
        break;
      case "horizontal-flip":
        if (tokens.length != 3) {
          System.out.println("Usage: horizontal-flip <reference_name> <output_name>");
        } else {
          imageModel.flipHorizontal(tokens[1], tokens[2]);
        }
        break;
      case "vertical-flip":
        if (tokens.length != 3) {
          System.out.println("Usage: horizontal-flip <reference_name> <output_name>");
        } else {
          imageModel.flipVertical(tokens[1], tokens[2]);
        }
        break;
      case "brighten":
        int increment = Integer.parseInt(tokens[3]);
        imageModel.brighten(increment, tokens[1], tokens[2]);
        break;
      case "blur":
        imageModel.blur(tokens[1], tokens[2]);
        break;
      case "sepia":
        imageModel.sepia(tokens[1], tokens[2]);
        break;
      case "sharpen":
        imageModel.sharpen(tokens[1], tokens[2]);
        break;
      case "value-component":
        imageModel.greyScale(tokens[1], tokens[2]);
        break;
      case "rgb-split":
        imageModel.splitImage(tokens[1], tokens[2], tokens[3], tokens[4]);
        break;
      case "rgb-combine":
        imageModel.combineImage(tokens[1], tokens[2], tokens[3], tokens[4]);
        break;
      case "run":
        runScript(tokens[1]);
        break;
      default:
        System.out.println("Invalid command!");
        break;
    }
  }

  private void runScript(String scriptPath) {
    try {
      String path = "script.txt";
      BufferedReader reader = new BufferedReader(new FileReader(scriptPath));
      String line;
      while ((line = reader.readLine()) != null) {
        this.processCommand(line);
      }
      reader.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
