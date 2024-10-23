import controller.ImageController;
import model.Image;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;
import utils.ImageIOHelper;

public class ImageModelTest {

  ImageModel model;
  ImageIOHelper helper;
  Image testImage;
  @Before
  public void setup() {
    model = new ImageModel();
    helper = new ImageIOHelper();
    Image img = helper.loadImage("src/img/test.png");
    testImage = ImageIOHelper.loadImage("src/img/test.png");

  }

  @Test
  public void testLoadImage() {
    Image bright = model.brighten(testImage,10);
  }
}
