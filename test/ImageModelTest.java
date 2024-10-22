import model.Image;
import model.ImageModel;
import model.ImageOperations;
import org.junit.Before;
import org.junit.Test;
import utils.ImageIOHelper;

public class ImageModelTest {

  ImageModel model;
  ImageOperations operations;
  Image testImage;
  @Before
  public void setup() {
    model = new ImageModel();
    operations = new ImageOperations();
    model.loadImage("src/img/test.png", "test");
    testImage = ImageIOHelper.loadImage("src/img/test.png");

  }

  @Test
  public void testLoadImage() {
    Image bright = operations.brighten(testImage,10);
  }
}
