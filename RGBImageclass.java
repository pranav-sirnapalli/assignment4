package model;

public class RGBImage extends Image {

    public RGBImage(int width, int height) {
        super(width, height);
    }

    public Image redComponent() {
        Image result = new RGBImage(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int[] pixel = getPixel(i, j);
                int[] newPixel = {pixel[0], 0, 0};
                result.setPixel(i, j, newPixel);
            }
        }
        return result;
    }

    public Image greenComponent() {
        Image result = new RGBImage(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int[] pixel = getPixel(i, j);
                int[] newPixel = {0, pixel[1], 0};
                result.setPixel(i, j, newPixel);
            }
        }
        return result;
    }

    public Image blueComponent() {
        Image result = new RGBImage(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int[] pixel = getPixel(i, j);
                int[] newPixel = {0, 0, pixel[2]};
                result.setPixel(i, j, newPixel);
            }
        }
        return result;
    }
}
