import model.ImageModel;
import org.junit.Before;
import org.junit.Test;

public class SimpleImageModelTest {

  ImageModel model;

  @Before
  public void setup() {
    model = new ImageModel();
    model.loadImage("src/img/a.png", "a");

  }

  @Test
  public void saveTest() {
    model.saveImage("src/img/b1.png", "a");
  }

  @Test
  public void splitTest() {
    model.splitImage("a", "a_red", "a_green", "a_blue");
    model.saveImage("src/img/a_red.png", "a_red");
    model.saveImage("src/img/a_green.png", "a_green");
    model.saveImage("src/img/a_blue.png", "a_blue");
  }

  @Test
  public void combineTest() {
    model.loadImage("src/img/a_red.png", "a_red");
    model.loadImage("src/img/a_green.png", "a_green");
    model.loadImage("src/img/a_blue.png", "a_blue");
    //Combine part
    model.combineImage("combine", "a_red", "a_green", "a_blue");

    model.saveImage("src/img/a_combine.png", "combine");
  }

  @Test
  public void brightTest() {
    model.brighten(-50,"a", "a_bright");
    model.saveImage("src/img/a_bright.png", "a_bright");

  }

  @Test
  public void brightTest1() {
    model.brighten(-10,"a", "a_dark");
    model.saveImage("src/img/a_dark.png", "a_dark");

  }


}
