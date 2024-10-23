import model.Image;
import model.ImageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageModelTest {

  private ImageModel imageModel;
  private Image testImage;

  @BeforeEach
  public void setUp() {
    imageModel = new ImageModel();
    // Creating a 3x3 test image with known pixel values
    testImage = new Image(3, 3);
    testImage.setPixel(0, 0, new int[]{255, 0, 0}); // Red
    testImage.setPixel(0, 1, new int[]{0, 255, 0}); // Green
    testImage.setPixel(0, 2, new int[]{0, 0, 255}); // Blue
    testImage.setPixel(1, 0, new int[]{255, 255, 0}); // Yellow
    testImage.setPixel(1, 1, new int[]{255, 0, 255}); // Magenta
    testImage.setPixel(1, 2, new int[]{0, 255, 255}); // Cyan
    testImage.setPixel(2, 0, new int[]{128, 128, 128}); // Grey
    testImage.setPixel(2, 1, new int[]{0, 0, 0}); // Black
    testImage.setPixel(2, 2, new int[]{255, 255, 255}); // White
  }

  @Test
  public void testFlipHorizontal() {
    Image flipped = imageModel.flipHorizontal(testImage);
    // Expected result after flipping horizontally
    assertArrayEquals(new int[]{0, 0, 255}, flipped.getPixel(0, 0)); // Blue
    assertArrayEquals(new int[]{0, 255, 0}, flipped.getPixel(0, 1)); // Green
    assertArrayEquals(new int[]{255, 0, 0}, flipped.getPixel(0, 2)); // Red
    assertArrayEquals(new int[]{0, 255, 255}, flipped.getPixel(1, 0)); // Cyan
    assertArrayEquals(new int[]{255, 0, 255}, flipped.getPixel(1, 1)); // Magenta
    assertArrayEquals(new int[]{255, 255, 0}, flipped.getPixel(1, 2)); // Yellow
    assertArrayEquals(new int[]{255, 255, 255}, flipped.getPixel(2, 0)); // White
    assertArrayEquals(new int[]{0, 0, 0}, flipped.getPixel(2, 1)); // Black
    assertArrayEquals(new int[]{128, 128, 128}, flipped.getPixel(2, 2)); // Grey
  }

  @Test
  public void testFlipVertical() {
    Image flipped = imageModel.flipVertical(testImage);
    // Expected result after flipping vertically
    assertArrayEquals(new int[]{128, 128, 128}, flipped.getPixel(0, 0)); // Grey
    assertArrayEquals(new int[]{0, 0, 0}, flipped.getPixel(0, 1)); // Black
    assertArrayEquals(new int[]{255, 255, 255}, flipped.getPixel(0, 2)); // White
    assertArrayEquals(new int[]{255, 255, 0}, flipped.getPixel(1, 0)); // Yellow
    assertArrayEquals(new int[]{255, 0, 255}, flipped.getPixel(1, 1)); // Magenta
    assertArrayEquals(new int[]{0, 255, 255}, flipped.getPixel(1, 2)); // Cyan
    assertArrayEquals(new int[]{255, 0, 0}, flipped.getPixel(2, 0)); // Red
    assertArrayEquals(new int[]{0, 255, 0}, flipped.getPixel(2, 1)); // Green
    assertArrayEquals(new int[]{0, 0, 255}, flipped.getPixel(2, 2)); // Blue
  }

  @Test
  public void testBrighten() {
    Image brightened = imageModel.brighten(testImage, 50);
    // Expected result after brightening by 50
    assertArrayEquals(new int[]{255, 50, 50}, brightened.getPixel(0, 0)); // Red brightened
    assertArrayEquals(new int[]{50, 255, 50}, brightened.getPixel(0, 1)); // Green brightened
    assertArrayEquals(new int[]{50, 50, 255}, brightened.getPixel(0, 2)); // Blue brightened
    assertArrayEquals(new int[]{255, 255, 50}, brightened.getPixel(1, 0)); // Yellow brightened
    assertArrayEquals(new int[]{255, 50, 255}, brightened.getPixel(1, 1)); // Magenta brightened
    assertArrayEquals(new int[]{50, 255, 255}, brightened.getPixel(1, 2)); // Cyan brightened
    assertArrayEquals(new int[]{178, 178, 178}, brightened.getPixel(2, 0)); // Grey brightened
    assertArrayEquals(new int[]{50, 50, 50}, brightened.getPixel(2, 1)); // Black brightened
    assertArrayEquals(new int[]{255, 255, 255}, brightened.getPixel(2, 2)); // White (stays same, already maxed)
  }

  @Test
  public void testBlur() {
    Image blurred = imageModel.blur(testImage);
    // Expected result after blurring (manually calculated average values for each pixel)
    assertArrayEquals(new int[]{142, 113, 85}, blurred.getPixel(0, 0)); // Blurred top-left pixel
    assertArrayEquals(new int[]{113, 142, 142}, blurred.getPixel(0, 1)); // Blurred top-middle pixel
    assertArrayEquals(new int[]{85, 113, 142}, blurred.getPixel(0, 2)); // Blurred top-right pixel
    assertArrayEquals(new int[]{142, 170, 142}, blurred.getPixel(1, 0)); // Blurred middle-left pixel
    assertArrayEquals(new int[]{170, 113, 142}, blurred.getPixel(1, 1)); // Blurred middle pixel
    assertArrayEquals(new int[]{113, 142, 170}, blurred.getPixel(1, 2)); // Blurred middle-right pixel
    assertArrayEquals(new int[]{142, 113, 85}, blurred.getPixel(2, 0)); // Blurred bottom-left pixel
    assertArrayEquals(new int[]{113, 142, 113}, blurred.getPixel(2, 1)); // Blurred bottom-middle pixel
    assertArrayEquals(new int[]{85, 113, 142}, blurred.getPixel(2, 2)); // Blurred bottom-right pixel
  }

  @Test
  public void testRedComponent() {
    Image redComponent = imageModel.redComponent(testImage);
    // Expected result for red component (only red channel remains)
    assertArrayEquals(new int[]{255, 0, 0}, redComponent.getPixel(0, 0)); // Red pixel stays
    assertArrayEquals(new int[]{0, 0, 0}, redComponent.getPixel(0, 1)); // Green pixel becomes black
    assertArrayEquals(new int[]{0, 0, 0}, redComponent.getPixel(0, 2)); // Blue pixel becomes black
    assertArrayEquals(new int[]{255, 0, 0}, redComponent.getPixel(1, 0)); // Yellow pixel's red component
    assertArrayEquals(new int[]{255, 0, 0}, redComponent.getPixel(1, 1)); // Magenta pixel's red component
    assertArrayEquals(new int[]{0, 0, 0}, redComponent.getPixel(1, 2)); // Cyan pixel becomes black
    assertArrayEquals(new int[]{128, 0, 0}, redComponent.getPixel(2, 0)); // Grey's red component
    assertArrayEquals(new int[]{0, 0, 0}, redComponent.getPixel(2, 1)); // Black pixel stays black
    assertArrayEquals(new int[]{255, 0, 0}, redComponent.getPixel(2, 2)); // White pixel's red component
  }
}
