package utils;

public class ImageHandlerFactory {

  public static ImageHandler getHandler(String filePath) {
    String extension = getFileExtension(filePath);

    switch (extension.toLowerCase()) {
      case "jpg":
      case "png":
        return new StandardImageHandler();
      case "ppm":
        return new PPMImageHandler();
      default:
        throw new UnsupportedOperationException("Unsupported file format: " + extension);
    }
  }

  private static String getFileExtension(String filePath) {
    int lastIndexOfDot = filePath.lastIndexOf('.');
    if (lastIndexOfDot == -1) {
      return ""; // No extension found
    }
    return filePath.substring(lastIndexOfDot + 1);
  }
}
