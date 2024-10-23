package model;

import utils.ImageIOHelper;

/**
 * Helper class of ImageModel provides various image manipulation methods such as loading, saving,
 * flipping, and applying filters like blur, sepia, and sharpen.
 */
public class ImageOperations {

  /**
   * Loads an image from the specified file path using the ImageIOHelper utility.
   *
   * @param path the file path of the image to be loaded
   * @return the loaded Image object
   */
  public Image loadImage(String path) {
    Image image = ImageIOHelper.loadImage(path);
    return ImageIOHelper.loadImage(path);

  }

  /**
   * Saves an image to the specified file path using the ImageIOHelper utility.
   *
   * @param path  the destination file path where the image will be saved
   * @param image the Image object to be saved
   */
  public void saveImage(String path, Image image) {
    ImageIOHelper.saveImage(path, image);
  }

  /**
   * Splits an image into its RGB components and returns an RGBImage object.
   *
   * @param image the Image to be split
   * @return an RGBImage object containing separate channels for red, green, and blue
   */
  public RGBImage splitImage(Image image) {
    return new RGBImage(image.width, image.height);
  }


  /**
   * Combines the red, green, and blue components of separate images into one final image.
   *
   * @param red   the Image containing the red channel
   * @param green the Image containing the green channel
   * @param blue  the Image containing the blue channel
   * @return the combined Image
   */
  public Image combineImage(Image red, Image green, Image blue) {
    Image result = new Image(red.getWidth(), red.getHeight());
    for (int row = 0; row < red.getHeight(); row++) {
      for (int col = 0; col < red.getWidth(); col++) {
        int r = red.getPixel(row, col)[0];
        int g = green.getPixel(row, col)[1];
        int b = blue.getPixel(row, col)[2];
        int[] rgb = {r, g, b};
        result.setPixel(row, col, rgb);
      }
    }
    return result;
  }

  /**
   * Flips an image horizontally (left to right).
   *
   * @param img the Image to be flipped
   * @return the horizontally flipped Image
   */
  public Image flipHorizontal(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(row, col, img.getPixel(row, img.getWidth() - 1 - col));
      }
    }
    return result;
  }

  /**
   * Flips the given image vertically.
   *
   * @param img the image to be flipped
   * @return a new Image object that is vertically flipped
   */
  public Image flipVertical(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(img.getHeight() - 1 - row, col, img.getPixel(row, col));
      }
    }
    return result;
  }

  /**
   * Brightens the given image by a specified increment.
   *
   * @param img       the image to brighten
   * @param increment the amount to increase the brightness
   * @return a new Image object that is brightened
   */
  public Image brighten(Image img, int increment) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] pixel = img.getPixel(row, col);
        int[] newPixel = new int[3];
        for (int i = 0; i < 3; i++) {
          newPixel[i] = Math.min(255, Math.max(0, pixel[i] + increment));
        }
        result.setPixel(row, col, newPixel);
      }
    }
    return result;
  }

  /**
   * Converts the given image to greyscale.
   *
   * @param img the image to convert
   * @return a new Image object that is in greyscale
   */
  public Image toGreyscale(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] pixel = img.getPixel(row, col);
        int grey = (int) (pixel[0] * 0.299 + pixel[1] * 0.587 + pixel[2] * 0.114);
        int[] newPixel = {grey, grey, grey};
        result.setPixel(row, col, newPixel);
      }
    }
    return result;
  }

  /**
   * Applies a blur effect to the given image.
   *
   * @param img the image to blur
   * @return a new Image object that is blurred
   */
  public Image blur(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    int[][] kernel = {
        {1, 1, 1},
        {1, 1, 1},
        {1, 1, 1}
    };
    int kernelSize = 3;
    int kernelSum = 9;

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] newPixel = new int[3];
        for (int i = 0; i < 3; i++) {
          int total = 0;
          for (int ki = 0; ki < kernelSize; ki++) {
            for (int kj = 0; kj < kernelSize; kj++) {
              int pixelRow = Math.min(Math.max(row + ki - 1, 0), img.getHeight() - 1);
              int pixelCol = Math.min(Math.max(col + kj - 1, 0), img.getWidth() - 1);
              total += img.getPixel(pixelRow, pixelCol)[i] * kernel[ki][kj];
            }
          }
          newPixel[i] = total / kernelSum;
        }
        result.setPixel(row, col, newPixel);
      }
    }
    return result;
  }

  /**
   * Applies a sepia filter to the given image.
   *
   * @param img the image to apply the filter on
   * @return a new Image object with the sepia effect applied
   */
  public Image sepia(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] pixel = img.getPixel(row, col);
        int red = pixel[0];
        int green = pixel[1];
        int blue = pixel[2];

        // Apply sepia formula
        int newR = (int) Math.min((0.393 * red + 0.769 * green + 0.189 * blue), 255);
        int newG = (int) Math.min((0.349 * red + 0.686 * green + 0.168 * blue), 255);
        int newB = (int) Math.min((0.272 * red + 0.534 * green + 0.131 * blue), 255);
        result.setPixel(row, col, new int[]{newR, newG, newB});
      }
    }
    return result;
  }

  /**
   * Applies a sharpening filter to the given image.
   *
   * @param img the image to sharpen
   * @return a new Image object with the sharpened effect applied
   */
  public Image sharpen(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());

    double[][] kernel = {
        {-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}
    };

    int kernelSize = 5;
    int halfKernel = kernelSize / 2;

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] newPixel = new int[3];

        for (int i = 0; i < 3; i++) {
          int pixelValue = 0;

          for (int ki = 0; ki < kernelSize; ki++) {
            for (int kj = 0; kj < kernelSize; kj++) {
              int pixelRow = Math.min(Math.max(row + ki - halfKernel, 0), img.getHeight() - 1);
              int pixelCol = Math.min(Math.max(col + kj - halfKernel, 0), img.getWidth() - 1);

              pixelValue += (int) (img.getPixel(pixelRow, pixelCol)[i] * kernel[ki][kj]);
            }
          }

          newPixel[i] = Math.min(Math.max(pixelValue, 0), 255);
        }

        result.setPixel(row, col, newPixel);
      }
    }

    return result;
  }
}
