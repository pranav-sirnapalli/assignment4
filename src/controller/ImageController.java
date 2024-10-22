package controller;

import model.ImageModel;

public class ImageController {

  private ImageModel imageModel;

  public ImageController() {

    this.imageModel = new ImageModel();
  }

  public void processCommand(String command) {
    // Skip empty lines or comments
    if (command.isEmpty() || command.startsWith("#")) {
      return;
    }
    String[] tokens = command.split(" ");
    switch (tokens[0]) {
      case "load":
        imageModel.loadImage(tokens[1], tokens[2]);
        break;
      case "save":
        imageModel.saveImage(tokens[1], tokens[2]);
        break;
      case "flip-horizontal":
        imageModel.flipHorizontal(tokens[1], tokens[2]);
        break;
      case "flip-vertical":
        imageModel.flipVertical(tokens[1], tokens[2]);
        break;
      case "brighten":
        int increment = Integer.parseInt(tokens[3]);
        imageModel.brighten(tokens[1], tokens[2], increment);
        break;
      case "blur":
        imageModel.blur(tokens[1], tokens[2]);
        break;
      case "greyScale":
        imageModel.greyScale(tokens[1], tokens[2]);
        break;
      case "rgb-split":
        imageModel.splitImage(tokens[1], tokens[2], tokens[3], tokens[4]);
        break;
      case "rgb-combine":
        imageModel.combineImage(tokens[1], tokens[2], tokens[3], tokens[4]);
        break;
      default:
        System.out.println("Invalid command!");
        break;
    }
  }
}
