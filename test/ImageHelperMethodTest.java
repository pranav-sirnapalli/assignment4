import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import model.ImageModel;
import org.junit.Before;
import org.junit.Test;

public class ImageHelperMethodTest {
  ImageModel imageModel;

  @Before
  public void setUp() {
    imageModel = new ImageModel();
  }

  @Test
  public void haarTest(){
    double[] input = {5,2,1,4};
    double[] result =imageModel.haarWaveletTransform(input);
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i]);
    }
    double[] expect ={7/Math.sqrt(2),5/Math.sqrt(2),3/Math.sqrt(2),-3/Math.sqrt(2)};
    assertArrayEquals(expect,result,0.01);

  }

  @Test
  public void haarTest_invert(){
    double[] expect = {5,2,1,4};
    double[] input ={7/Math.sqrt(2),5/Math.sqrt(2),3/Math.sqrt(2),-3/Math.sqrt(2)};
    double[] result =imageModel.invertHaarWaveletTransform(input);
    for (int i = 0; i < result.length; i++) {
      System.out.println(result[i]);
    }
    assertArrayEquals(expect,result,0.01);

  }
}
