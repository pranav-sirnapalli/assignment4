package model;

import java.util.HashMap;
import java.util.Map;

public class ImageModel {

  private Map<String, Image> images;
  private ImageOperations imageOperations;

  public ImageModel() {
    images = new HashMap<>();
    imageOperations = new ImageOperations();
  }

  public void loadImage(String path, String input) {
    images.put(input, imageOperations.loadImage(path));
  }

  public void saveImage(String path, String input) {
    imageOperations.saveImage(path, images.get(input));
  }

  public void flipHorizontal(String input, String output) {
    images.put(output, imageOperations.flipHorizontal(images.get(input)));
  }

  public void flipVertical(String input, String output) {
    images.put(output, imageOperations.flipVertical(images.get(input)));
  }

  public void brighten(String input, String output, int increment) {
    images.put(output, imageOperations.brighten(images.get(input), increment));
  }

  public void blur(String input, String output) {
    images.put(output, imageOperations.blur(images.get(input)));
  }

  public void greyScale(String input, String output) {
    images.put(output, imageOperations.toGreyscale(images.get(input)));
  }

  public void splitImage(String input, String red, String green, String blue) {
    RGBImage splitImage = imageOperations.splitImage(images.get(input));
    images.put(red, splitImage.redComponent(images.get(input)));
    images.put(green, splitImage.greenComponent(images.get(input)));
    images.put(blue, splitImage.blueComponent(images.get(input)));
  }

  public void combineImage(String output, String red, String green, String blue) {
    images.put(output,
        imageOperations.combineImage(images.get(red), images.get(green), images.get(blue)));
  }

}
