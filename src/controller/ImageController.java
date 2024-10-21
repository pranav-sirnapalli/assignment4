package controller;

import java.util.HashMap;
import java.util.Map;
import model.Image;
import model.ImageModel;

public class ImageController {

  private Map<String, Image> images;
  private ImageModel imageModel;

  public ImageController() {

    this.images = new HashMap<>();
    this.imageModel = new ImageModel();
  }

  public void processCommand(String command) {
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
        imageModel.splitImage(tokens[1], tokens[2],tokens[3],tokens[4]);
        break;
      case "rgb-combine":
        imageModel.combineImage(tokens[1], tokens[2],tokens[3],tokens[4]);
        break;
      default:
        System.out.println("Invalid command!");
        break;
    }
  }
}
