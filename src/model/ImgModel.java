package model;

/**
 * ImgModel interface defines a set of image manipulation operations
 * that can be implemented to load, save, and modify images in various ways.
 *
 * The model supports different transformations such as flipping, brightening,
 * blurring, sharpening, applying sepia and greyscale filters, as well as
 * splitting and combining images based on color channels.
 *
 * Implementations of this interface should provide functionality for the following:
 * - Loading and saving images.
 * - Applying transformations like horizontal/vertical flips, brightness adjustment,
 *   blurring, sharpening, sepia tone, and greyscale.
 * - Color channel manipulation via image splitting and combining.
 */
public interface ImgModel {

  /**
   * Loads an image from the specified file path and stores it with a reference name.
   *
   * @param imagePath The file path to the image.
   * @param imageName The name to assign to the loaded image.
   */
  void loadImage(String imagePath, String imageName);

  /**
   * Saves the specified image to the given file path.
   *
   * @param imagePath The file path where the image will be saved.
   * @param imageName The name of the image to be saved.
   */
  void saveImage(String imagePath, String imageName);

  /**
   * Flips the specified image horizontally and stores the result as a new image.
   *
   * @param imageName The name of the image to flip.
   * @param destImageName The name of the resulting flipped image.
   */
  void flipHorizontal(String imageName, String destImageName);

  /**
   * Flips the specified image vertically and stores the result as a new image.
   *
   * @param imageName The name of the image to flip.
   * @param destImageName The name of the resulting flipped image.
   */
  void flipVertical(String imageName, String destImageName);

  /**
   * Adjusts the brightness of the specified image by a given increment
   * and stores the result as a new image.
   *
   * @param increment The value by which to increase or decrease the brightness.
   * @param imageName The name of the image to brighten.
   * @param destImageName The name of the resulting brightened image.
   */
  void brighten(int increment, String imageName, String destImageName);

  /**
   * Applies a blur effect to the specified image and stores the result as a new image.
   *
   * @param imageName The name of the image to blur.
   * @param destImageName The name of the resulting blurred image.
   */
  void blur(String imageName, String destImageName);

  /**
   * Sharpens the specified image and stores the result as a new image.
   *
   * @param imageName The name of the image to sharpen.
   * @param destImageName The name of the resulting sharpened image.
   */
  void sharpen(String imageName, String destImageName);

  /**
   * Applies a sepia filter to the specified image and stores the result as a new image.
   *
   * @param imageName The name of the image to apply sepia to.
   * @param destImageName The name of the resulting sepia-toned image.
   */
  void sepia(String imageName, String destImageName);

  /**
   * Converts the specified image to greyscale and stores the result as a new image.
   *
   * @param imageName The name of the image to convert to greyscale.
   * @param destImageName The name of the resulting greyscale image.
   */
  void greyScale(String imageName, String destImageName);

  /**
   * Splits the specified image into its red, green, and blue color channels,
   * and stores each channel as a separate image.
   *
   * @param imageName The name of the image to split.
   * @param red The name of the resulting red channel image.
   * @param green The name of the resulting green channel image.
   * @param blue The name of the resulting blue channel image.
   */
  void splitImage(String imageName, String red, String green, String blue);

  /**
   * Combines the red, green, and blue channel images into a single image and stores it.
   *
   * @param imageName The name of the resulting combined image.
   * @param red The name of the red channel image.
   * @param green The name of the green channel image.
   * @param blue The name of the blue channel image.
   */
  void combineImage(String imageName, String red, String green, String blue);

}
