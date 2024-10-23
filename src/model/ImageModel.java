package model;

import java.util.HashMap;
import java.util.Map;

/**
 * ImageModel
 */
public class ImageModel implements ImgModel {

  private Map<String, Image> images;
  private ImageOperations imageOperations;

  /**
   * Constructor initializes the images map and the ImageOperations instance.
   */
  public ImageModel() {
    images = new HashMap<>();
    imageOperations = new ImageOperations();
  }

  @Override
  public void loadImage(String path, String input) {
    images.put(input, imageOperations.loadImage(path));
  }

  @Override
  public void saveImage(String path, String input) {
    imageOperations.saveImage(path, images.get(input));
  }

  @Override
  public void flipHorizontal(String input, String output) {
    images.put(output, imageOperations.flipHorizontal(images.get(input)));
  }

  @Override
  public void flipVertical(String input, String output) {
    images.put(output, imageOperations.flipVertical(images.get(input)));
  }

  @Override
  public void brighten(int increment, String input, String output) {
    images.put(output, imageOperations.brighten(images.get(input), increment));
  }

  @Override
  public void blur(String input, String output) {
    images.put(output, imageOperations.blur(images.get(input)));
  }

  @Override
  public void sepia(String input, String output) {
    images.put(output, imageOperations.sepia(images.get(input)));
  }

  @Override
  public void greyScale(String input, String output) {
    images.put(output, imageOperations.toGreyscale(images.get(input)));
  }

  @Override
  public void splitImage(String input, String red, String green, String blue) {
    RGBImage splitImage = imageOperations.splitImage(images.get(input));
    images.put(red, splitImage.redComponent(images.get(input)));
    images.put(green, splitImage.greenComponent(images.get(input)));
    images.put(blue, splitImage.blueComponent(images.get(input)));
  }

  @Override
  public void combineImage(String output, String red, String green, String blue) {
    images.put(output,
        imageOperations.combineImage(images.get(red), images.get(green), images.get(blue)));
  }

  @Override
  public void sharpen(String input, String output) {
    images.put(output, imageOperations.sharpen(images.get(input)));
  }

}
