package utils.compression;

import java.util.Arrays;

/**
 * Helper class for compression.
 */
public class HaarTransform {


  public static double[] haarWaveletTransform(double[] sequence) {
    int reqlen = sequence.length;
    double[] net_res = Arrays.copyOf(sequence, reqlen);

    while (reqlen > 1) {
      double[] avgDiff = new double[reqlen];
      for (int i = 0; i < reqlen; i += 2) {
        avgDiff[i / 2] = (net_res[i] + net_res[i + 1]) / Math.sqrt(2);
        avgDiff[reqlen / 2 + i / 2] = (net_res[i] - net_res[i + 1]) / Math.sqrt(2);
      }
      System.arraycopy(avgDiff, 0, net_res, 0, reqlen);
      reqlen /= 2;
    }
    return net_res;
  }

  public static double[] invHaarWaveTransf(double[] transformedSequence) {
    int length = transformedSequence.length;
    double[] result = Arrays.copyOf(transformedSequence, length);
    int m = 2;

    while (m <= length) {
      double[] originalSeq = new double[m];
      for (int i = 0; i < m / 2; i++) {
        double avg = result[i];
        double diff = result[m / 2 + i];
        originalSeq[2 * i] = (avg + diff) / Math.sqrt(2);
        originalSeq[2 * i + 1] = (avg - diff) / Math.sqrt(2);
      }
      System.arraycopy(originalSeq, 0, result, 0, m);
      m *= 2;
    }
    return result;
  }

  public static double[][] haarWaveTransf2D(double[][] res_matrix) {
    int size = res_matrix.length;

    for (int i = 0; i < size; i++) {
      res_matrix[i] = haarWaveletTransform(res_matrix[i]);
    }

    for (int j = 0; j < size; j++) {
      double[] column = new double[size];
      for (int i = 0; i < size; i++) {
        column[i] = res_matrix[i][j];
      }
      column = haarWaveletTransform(column);
      for (int i = 0; i < size; i++) {
        res_matrix[i][j] = column[i];
      }
    }

    return res_matrix;
  }

  public static double[][] invHaarWaveTransf2D(double[][] res_matrix) {
    int size = res_matrix.length;

    for (int j = 0; j < size; j++) {
      double[] column = new double[size];
      for (int i = 0; i < size; i++) {
        column[i] = res_matrix[i][j];
      }
      column = invHaarWaveTransf(column);
      for (int i = 0; i < size; i++) {
        res_matrix[i][j] = column[i];
      }
    }

    for (int i = 0; i < size; i++) {
      res_matrix[i] = invHaarWaveTransf(res_matrix[i]);
    }

    return res_matrix;
  }
}

