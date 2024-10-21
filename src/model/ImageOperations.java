package model;

import java.util.HashMap;
import java.util.Map;
import utils.ImageIOHelper;

public class ImageOperations {

  public Image loadImage(String path) {
    Image image =  ImageIOHelper.loadImage(path);
    return ImageIOHelper.loadImage(path);

  }

  public void saveImage(String path, Image image) {
    ImageIOHelper.saveImage(path, image);
  }

  public RGBImage splitImage(Image image) {
    return new RGBImage(image.width,image.height);
  }

  public Image combineImage(Image red, Image green, Image blue) {
    Image result = new Image(red.getWidth(), red.getHeight());
    for (int row = 0; row < red.getHeight(); row++) {
      for (int col = 0; col < red.getWidth(); col++) {
        int r = red.getPixel(row,col)[0];
        int g = red.getPixel(row,col)[1];
        int b = red.getPixel(row,col)[2];
        int[] rgb = {r,g,b};
        result.setPixel(row, col,rgb);
      }
    }
    return result;
  }

  public Image flipHorizontal(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(row, col, img.getPixel(row, img.getWidth() - 1 - col));
      }
    }
    return result;
  }

  public Image flipVertical(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    for (int row = 0; row < img.getHeight(); row++) {
      for (int col = 0; col < img.getWidth(); col++) {
        result.setPixel(img.getHeight() - 1 - row, col, img.getPixel(row, col));
      }
    }
    return result;
  }

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

  public Image blur(Image img) {
    Image result = new Image(img.getWidth(), img.getHeight());
    double[][] kernel1 = {
        {1/16, 1/8, 1/16},
        {1/8, 1/4, 1/8},
        {1/16, 1/8, 1/16}
    };

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
}
