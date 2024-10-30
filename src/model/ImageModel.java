package model;

import model.image.Image;
import model.image.RGBImage;
import model.image.SimpleImage;

/**
 * ImageModel implemented ImgModel which provides various image manipulation methods such as
 * loading, saving, flipping, and applying filters like blur, sepia, and sharpen.
 */
public class ImageModel implements ImgModel {

  @Override
  public RGBImage splitImage(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    return new RGBImage(image.getWidth(), image.getHeight());
  }

  @Override
  public Image combineImage(Image red, Image green, Image blue) {
    if (red == null || green == null || blue == null) {
      throw new IllegalArgumentException("None of the color images can be null");
    }
    Image result = new SimpleImage(red.getWidth(), red.getHeight());
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

  @Override
  public Image flipHorizontal(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(row, col, img.getPixel(row, img.getWidth() - 1 - col));
      }
    }
    return result;
  }

  @Override
  public Image flipVertical(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(img.getHeight() - 1 - row, col, img.getPixel(row, col));
      }
    }
    return result;
  }

  @Override
  public Image brighten(Image img, int increment) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());
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

  @Override
  public Image toGreyscale(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());
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

  @Override
  public Image blur(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());
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

  @Override
  public Image sepia(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());

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

  @Override
  public Image sharpen(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());

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

  @Override
  public Image value(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] rgb = img.getPixel(row, col);
        int value = Math.max(rgb[0], Math.max(rgb[1], rgb[2]));

        int[] grayscale = {value, value, value};
        result.setPixel(row, col, grayscale);
      }
    }

    return result;
  }

  @Override
  public Image intensity(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] rgb = img.getPixel(row, col);
        int intensity = (rgb[0] + rgb[1] + rgb[2]) / 3;

        int[] grayscale = {intensity, intensity, intensity};
        result.setPixel(row, col, grayscale);
      }
    }

    return result;
  }

  @Override
  public Image luma(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    Image result = new SimpleImage(img.getWidth(), img.getHeight());

    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        int[] rgb = img.getPixel(row, col);
        int luma = (int) (0.299 * rgb[0] + 0.587 * rgb[1] + 0.114 * rgb[2]);

        int[] grayscale = {luma, luma, luma};
        result.setPixel(row, col, grayscale);
      }
    }

    return result;
  }

  @Override
  public Image redComponent(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    RGBImage rgbImage = new RGBImage(img.getWidth(), img.getHeight());
    return rgbImage.redComponent(img);
  }

  @Override
  public Image greenComponent(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    RGBImage rgbImage = new RGBImage(img.getWidth(), img.getHeight());
    return rgbImage.greenComponent(img);
  }

  @Override
  public Image blueComponent(Image img) {
    if (img == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    RGBImage rgbImage = new RGBImage(img.getWidth(), img.getHeight());
    return rgbImage.blueComponent(img);
  }
}
