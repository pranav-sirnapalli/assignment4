package utils;

import model.Image;

import java.io.*;

/**
 * PPMImageHandler is a concrete implementation of the ImageHandler interface
 * specifically for handling PPM (Portable Pixmap) images in P3 format.
 */
public class PPMImageHandler implements ImageHandler {

  @Override
  public Image loadImage(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String header = reader.readLine();
      if (!header.equals("P3")) {
        System.out.println("Unsupported PPM format: " + header);
        return null;
      }
      String line;
      while ((line = reader.readLine()) != null && line.startsWith("#"));

      String[] dimensions = line.split(" ");
      int width = Integer.parseInt(dimensions[0]);
      int height = Integer.parseInt(dimensions[1]);
      reader.readLine(); // Skip maxVal (assuming 255)

      Image img = new Image(width, height);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = Integer.parseInt(reader.readLine());
          int g = Integer.parseInt(reader.readLine());
          int b = Integer.parseInt(reader.readLine());
          img.setPixel(i, j, new int[]{r, g, b});
        }
      }
      return img;
    } catch (IOException e) {
      System.out.println("Error loading PPM image: " + e.getMessage());
      return null;
    }
  }

  @Override
  public void saveImage(String filePath, Image img) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("P3\n");
      writer.write(img.getWidth() + " " + img.getHeight() + "\n");
      writer.write("255\n");
      for (int i = 0; i < img.getHeight(); i++) {
        for (int j = 0; j < img.getWidth(); j++) {
          int[] pixel = img.getPixel(i, j);
          writer.write(pixel[0] + "\n" + pixel[1] + "\n" + pixel[2] + "\n");
        }
      }
    } catch (IOException e) {
      System.out.println("Error saving PPM image: " + e.getMessage());
    }
  }
}
