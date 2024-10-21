package model;

public class Image {
    protected int width;
    protected int height;
    protected int[][][] pixels; // 3D array for RGB components

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[height][width][3]; // RGB components: [row][col][RGB]
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