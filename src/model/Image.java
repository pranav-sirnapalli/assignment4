package model;

/**
 * Image class which include the width, height and a 3d array to hold pixels.
 */
public class Image {

  protected int width;
  protected int height;
  protected int[][][] pixels;

  /**
   * Constructor of the image.
   * @param width width of the image.
   * @param height height of the image.
   */
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new int[height][width][3];
  }


  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int[] getPixel(int row, int col) {
    return pixels[row][col];
  }

  public void setPixel(int row, int col, int[] rgb) {
    pixels[row][col] = rgb;
  }

}
