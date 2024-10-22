package utils;

import model.Image;

public interface ImageHandler {
  Image loadImage(String filePath);
  void saveImage(String filePath, Image img);
}
