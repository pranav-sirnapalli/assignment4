import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import controller.ImageController;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import model.image.Image;
import model.ImageModel;
import org.junit.Before;
import org.junit.Test;
import utils.ImageIOHelper;

public class ImageControllerTest {
  private ImageModel model;
  private ImageController controller;
  private ByteArrayOutputStream outputStream;

  @Before
  public void setUp() throws Exception {
    model = new ImageModel();
    controller = new ImageController();
    outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  public void testLoadImage_PPM() throws Exception {
    File ppmFile = new File("src/res/a.ppm");

    Image loadImage = ImageIOHelper.loadImage(ppmFile.getAbsolutePath());

    assertNotNull("The loaded image should not be null", loadImage);
    assertEquals("The width of the image should be 4",1600, loadImage.getWidth());
    assertEquals("The height of the image should be 4",1600, loadImage.getHeight());

    assertArrayEquals("Check the first pixels",new int[]{218,93,71},loadImage.getPixel(0,0));
    assertArrayEquals("Check the last pixels",new int[]{218,93,71},loadImage.getPixel(3,3));

  }

  @Test
  public void testInvalidCase() {
    Scanner scanner = new Scanner("invalid a a exit");
    controller.processCommand(scanner.nextLine());

    String output = outputStream.toString();
    assertTrue("Output should be 'Invalid command!'",output.contains("Invalid command!"));

  }

  @Test
  public void testMissingParam(){
    Scanner scanner = new Scanner("load pathWithoutSecondParam");
    controller.processCommand(scanner.nextLine());

    String output = outputStream.toString();
    assertTrue("Output should give a prompt related to load'",output.contains("Usage: load <input_file_path> <reference_name>"));

  }
}
