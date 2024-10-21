import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class SimpleImageModelTest {

  SimpleImageModel model;
  @Before
  public void setup() {
    model = new SimpleImageModel();
    try{
      model.loadImage("src/img/a.png","a");
    }catch(Exception e){
      e.printStackTrace();
    }
  }

  @Test
  public void saveTest(){
    model.saveImage("src/img/b1.png","a");
    model.print("a");
  }

  @Test
  public void splitTest(){
    model.rgbSplit("a","a_red","a_green","a_blue");
    model.saveImage("src/img/a_red.png","a_red");
    model.saveImage("src/img/a_green.png","a_green");
    model.saveImage("src/img/a_blue.png","a_blue");
  }

  @Test
  public void combineTest(){
    //Split first
    model.rgbSplit("a","a_red","a_green","a_blue");
    model.saveImage("src/img/a_red.png","a_red");
    model.saveImage("src/img/a_green.png","a_green");
    model.saveImage("src/img/a_blue.png","a_blue");

    try{
      model.loadImage("src/img/combine1.png","combine1");
    }catch(Exception e){
      e.printStackTrace();
    }
    //Combine part
    model.rgbCombine("combine1","a_red","a_green","a_blue");
  }

  @Test
  public void brightTest(){
    model.brighten("a","a_bright",-50);
    model.saveImage("src/img/a_bright.png","a_bright");

  }

  @Test
  public void brightTest1(){
    model.brighten("a","a_dark",-10);
    model.saveImage("src/img/a_dark.png","a_dark");

  }


}
