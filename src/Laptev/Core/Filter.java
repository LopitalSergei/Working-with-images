/*
package Laptev.Core;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;

public class Filter {
    private void filter3x3(int[][] H, int c) {
        if (sourceImg != null) {
            for (int y = 1; y < sourceImgHeight - 1; y++) {
                for (int x = 1; x < sourceImgWidth - 1; x++) {
                    int newRed = (H[0][0] * redMatrix[y - 1][x - 1] + H[0][1] * redMatrix[y - 1][x] + H[0][2] * redMatrix[y - 1][x + 1]
                            + H[1][0] * redMatrix[y][x - 1] + H[1][1] * redMatrix[y][x] + H[1][2] * redMatrix[y][x + 1]
                            + H[2][0] * redMatrix[y + 1][x - 1] + H[2][1] * redMatrix[y + 1][x] + H[2][2] * redMatrix[y + 1][x + 1]) / c;
                    if (newRed > 255)
                        newRed = 255;
                    else if (newRed < 0)
                        newRed = 0;

                    int newGreen = (H[0][0] * greenMatrix[y - 1][x - 1] + H[0][1] * greenMatrix[y - 1][x] + H[0][2] * greenMatrix[y - 1][x + 1]
                            + H[1][0] * greenMatrix[y][x - 1] + H[1][1] * greenMatrix[y][x] + H[1][2] * greenMatrix[y][x + 1]
                            + H[2][0] * greenMatrix[y + 1][x - 1] + H[2][1] * greenMatrix[y + 1][x] + H[2][2] * greenMatrix[y + 1][x + 1]) / c;
                    if (newGreen > 255)
                        newGreen = 255;
                    else if (newGreen < 0)
                        newGreen = 0;

                    int newBlue = (H[0][0] * blueMatrix[y - 1][x - 1] + H[0][1] * blueMatrix[y - 1][x] + H[0][2] * blueMatrix[y - 1][x + 1]
                            + H[1][0] * blueMatrix[y][x - 1] + H[1][1] * blueMatrix[y][x] + H[1][2] * blueMatrix[y][x + 1]
                            + H[2][0] * blueMatrix[y + 1][x - 1] + H[2][1] * blueMatrix[y + 1][x] + H[2][2] * blueMatrix[y + 1][x + 1]) / c;
                    if (newBlue > 255)
                        newBlue = 255;
                    else if (newBlue < 0)
                        newBlue = 0;

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            javafx.scene.image.Image pict = SwingFXUtils.toFXImage(result, null);

            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 3х3\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter5x5(int[][] H, int c) {
        if (sourceImg != null) {
            for (int y = 2; y < sourceImgHeight - 2; y++) {
                for (int x = 2; x < sourceImgWidth - 2; x++) {
                    int newRed = (H[0][0] * redMatrix[y - 2][x - 2] + H[0][1] * redMatrix[y - 2][x - 1] + H[0][2] * redMatrix[y - 2][x] + H[0][3] * redMatrix[y - 2][x + 1] + H[0][4] * redMatrix[y - 2][x + 2]
                            + H[1][0] * redMatrix[y - 1][x - 2] + H[1][1] * redMatrix[y - 1][x - 1] + H[1][2] * redMatrix[y - 1][x] + H[1][3] * redMatrix[y - 1][x + 1] + H[1][4] * redMatrix[y - 1][x + 2]
                            + H[2][0] * redMatrix[y][x - 2] + H[2][1] * redMatrix[y][x - 1] + H[2][2] * redMatrix[y][x] + H[2][3] * redMatrix[y][x + 1] + H[2][4] * redMatrix[y][x + 2]
                            + H[3][0] * redMatrix[y + 1][x - 2] + H[3][1] * redMatrix[y + 1][x - 1] + H[3][2] * redMatrix[y + 1][x] + H[3][3] * redMatrix[y + 1][x + 1] + H[3][4] * redMatrix[y + 1][x + 2]
                            + H[4][0] * redMatrix[y + 2][x - 2] + H[4][1] * redMatrix[y + 2][x - 1] + H[4][2] * redMatrix[y + 2][x] + H[4][3] * redMatrix[y + 2][x + 1] + H[4][4] * redMatrix[y + 2][x + 2]) / c;
                    if (newRed > 255)
                        newRed = 255;
                    else if (newRed < 0)
                        newRed = 0;

                    int newGreen = (H[0][0] * greenMatrix[y - 2][x - 2] + H[0][1] * greenMatrix[y - 2][x - 1] + H[0][2] * greenMatrix[y - 2][x] + H[0][3] * greenMatrix[y - 2][x + 1] + H[0][4] * greenMatrix[y - 2][x + 2]
                            + H[1][0] * greenMatrix[y - 1][x - 2] + H[1][1] * greenMatrix[y - 1][x - 1] + H[1][2] * greenMatrix[y - 1][x] + H[1][3] * greenMatrix[y - 1][x + 1] + H[1][4] * greenMatrix[y - 1][x + 2]
                            + H[2][0] * greenMatrix[y][x - 2] + H[2][1] * greenMatrix[y][x - 1] + H[2][2] * greenMatrix[y][x] + H[2][3] * greenMatrix[y][x + 1] + H[2][4] * greenMatrix[y][x + 2]
                            + H[3][0] * greenMatrix[y + 1][x - 2] + H[3][1] * greenMatrix[y + 1][x - 1] + H[3][2] * greenMatrix[y + 1][x] + H[3][3] * greenMatrix[y + 1][x + 1] + H[3][4] * greenMatrix[y + 1][x + 2]
                            + H[4][0] * greenMatrix[y + 2][x - 2] + H[4][1] * greenMatrix[y + 2][x - 1] + H[4][2] * greenMatrix[y + 2][x] + H[4][3] * greenMatrix[y + 2][x + 1] + H[4][4] * greenMatrix[y + 2][x + 2]) / c;
                    if (newGreen > 255)
                        newGreen = 255;
                    else if (newGreen < 0)
                        newGreen = 0;

                    int newBlue = (H[0][0] * blueMatrix[y - 2][x - 2] + H[0][1] * blueMatrix[y - 2][x - 1] + H[0][2] * blueMatrix[y - 2][x] + H[0][3] * blueMatrix[y - 2][x + 1] + H[0][4] * blueMatrix[y - 2][x + 2]
                            + H[1][0] * blueMatrix[y - 1][x - 2] + H[1][1] * blueMatrix[y - 1][x - 1] + H[1][2] * blueMatrix[y - 1][x] + H[1][3] * blueMatrix[y - 1][x + 1] + H[1][4] * blueMatrix[y - 1][x + 2]
                            + H[2][0] * blueMatrix[y][x - 2] + H[2][1] * blueMatrix[y][x - 1] + H[2][2] * blueMatrix[y][x] + H[2][3] * blueMatrix[y][x + 1] + H[2][4] * blueMatrix[y][x + 2]
                            + H[3][0] * blueMatrix[y + 1][x - 2] + H[3][1] * blueMatrix[y + 1][x - 1] + H[3][2] * blueMatrix[y + 1][x] + H[3][3] * blueMatrix[y + 1][x + 1] + H[3][4] * blueMatrix[y + 1][x + 2]
                            + H[4][0] * blueMatrix[y + 2][x - 2] + H[4][1] * blueMatrix[y + 2][x - 1] + H[4][2] * blueMatrix[y + 2][x] + H[4][3] * blueMatrix[y + 2][x + 1] + H[4][4] * blueMatrix[y + 2][x + 2]) / c;
                    if (newBlue > 255)
                        newBlue = 255;
                    else if (newBlue < 0)
                        newBlue = 0;

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            javafx.scene.image.Image pict = SwingFXUtils.toFXImage(result, null);
            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 5х5\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter7x7(int[][] H, int c) {
        if (sourceImg != null) {
            for (int y = 3; y < sourceImgHeight - 3; y++) {
                for (int x = 3; x < sourceImgWidth - 3; x++) {
                    int newRed = (H[0][0] * redMatrix[y - 3][x - 3] + H[0][1] * redMatrix[y - 3][x - 2] + H[0][2] * redMatrix[y - 3][x - 1] + H[0][3] * redMatrix[y - 3][x] + H[0][4] * redMatrix[y - 3][x + 1] + H[0][5] * redMatrix[y - 3][x + 2] + H[0][6] * redMatrix[y - 3][x + 3]
                            + H[1][0] * redMatrix[y - 2][x - 3] + H[1][1] * redMatrix[y - 2][x - 2] + H[1][2] * redMatrix[y - 2][x - 1] + H[1][3] * redMatrix[y - 2][x] + H[1][4] * redMatrix[y - 2][x + 1] + H[1][5] * redMatrix[y - 2][x + 2] + H[1][6] * redMatrix[y - 2][x + 3]
                            + H[2][0] * redMatrix[y - 1][x - 3] + H[2][1] * redMatrix[y - 1][x - 2] + H[2][2] * redMatrix[y - 1][x - 1] + H[2][3] * redMatrix[y - 1][x] + H[2][4] * redMatrix[y - 1][x + 1] + H[2][5] * redMatrix[y - 1][x + 2] + H[2][6] * redMatrix[y - 1][x + 3]
                            + H[3][0] * redMatrix[y][x - 3] + H[3][1] * redMatrix[y][x - 2] + H[3][2] * redMatrix[y][x - 1] + H[3][3] * redMatrix[y][x] + H[3][4] * redMatrix[y][x + 1] + H[3][5] * redMatrix[y][x + 2] + H[3][6] * redMatrix[y][x + 3]
                            + H[4][0] * redMatrix[y + 1][x - 3] + H[4][1] * redMatrix[y + 1][x - 2] + H[4][2] * redMatrix[y + 1][x - 1] + H[4][3] * redMatrix[y + 1][x] + H[4][4] * redMatrix[y + 1][x + 1] + H[4][5] * redMatrix[y + 1][x + 2] + H[4][6] * redMatrix[y + 1][x + 3]
                            + H[5][0] * redMatrix[y + 2][x - 3] + H[5][1] * redMatrix[y + 2][x - 2] + H[5][2] * redMatrix[y + 2][x - 1] + H[5][3] * redMatrix[y + 2][x] + H[5][4] * redMatrix[y + 2][x + 1] + H[5][5] * redMatrix[y + 2][x + 2] + H[5][6] * redMatrix[y + 2][x + 3]
                            + H[6][0] * redMatrix[y + 3][x - 3] + H[6][1] * redMatrix[y + 3][x - 2] + H[6][2] * redMatrix[y + 3][x - 1] + H[6][3] * redMatrix[y + 3][x] + H[6][4] * redMatrix[y + 3][x + 1] + H[6][5] * redMatrix[y + 3][x + 2] + H[6][6] * redMatrix[y + 3][x + 3]) / c;
                    if (newRed > 255)
                        newRed = 255;
                    else if (newRed < 0)
                        newRed = 0;

                    int newGreen = (H[0][0] * greenMatrix[y - 3][x - 3] + H[0][1] * greenMatrix[y - 3][x - 2] + H[0][2] * greenMatrix[y - 3][x - 1] + H[0][3] * greenMatrix[y - 3][x] + H[0][4] * greenMatrix[y - 3][x + 1] + H[0][5] * greenMatrix[y - 3][x + 2] + H[0][6] * greenMatrix[y - 3][x + 3]
                            + H[1][0] * greenMatrix[y - 2][x - 3] + H[1][1] * greenMatrix[y - 2][x - 2] + H[1][2] * greenMatrix[y - 2][x - 1] + H[1][3] * greenMatrix[y - 2][x] + H[1][4] * greenMatrix[y - 2][x + 1] + H[1][5] * greenMatrix[y - 2][x + 2] + H[1][6] * greenMatrix[y - 2][x + 3]
                            + H[2][0] * greenMatrix[y - 1][x - 3] + H[2][1] * greenMatrix[y - 1][x - 2] + H[2][2] * greenMatrix[y - 1][x - 1] + H[2][3] * greenMatrix[y - 1][x] + H[2][4] * greenMatrix[y - 1][x + 1] + H[2][5] * greenMatrix[y - 1][x + 2] + H[2][6] * greenMatrix[y - 1][x + 3]
                            + H[3][0] * greenMatrix[y][x - 3] + H[3][1] * greenMatrix[y][x - 2] + H[3][2] * greenMatrix[y][x - 1] + H[3][3] * greenMatrix[y][x] + H[3][4] * greenMatrix[y][x + 1] + H[3][5] * greenMatrix[y][x + 2] + H[3][6] * greenMatrix[y][x + 3]
                            + H[4][0] * greenMatrix[y + 1][x - 3] + H[4][1] * greenMatrix[y + 1][x - 2] + H[4][2] * greenMatrix[y + 1][x - 1] + H[4][3] * greenMatrix[y + 1][x] + H[4][4] * greenMatrix[y + 1][x + 1] + H[4][5] * greenMatrix[y + 1][x + 2] + H[4][6] * greenMatrix[y + 1][x + 3]
                            + H[5][0] * greenMatrix[y + 2][x - 3] + H[5][1] * greenMatrix[y + 2][x - 2] + H[5][2] * greenMatrix[y + 2][x - 1] + H[5][3] * greenMatrix[y + 2][x] + H[5][4] * greenMatrix[y + 2][x + 1] + H[5][5] * greenMatrix[y + 2][x + 2] + H[5][6] * greenMatrix[y + 2][x + 3]
                            + H[6][0] * greenMatrix[y + 3][x - 3] + H[6][1] * greenMatrix[y + 3][x - 2] + H[6][2] * greenMatrix[y + 3][x - 1] + H[6][3] * greenMatrix[y + 3][x] + H[6][4] * greenMatrix[y + 3][x + 1] + H[6][5] * greenMatrix[y + 3][x + 2] + H[6][6] * greenMatrix[y + 3][x + 3]) / c;
                    if (newGreen > 255)
                        newGreen = 255;
                    else if (newGreen < 0)
                        newGreen = 0;

                    int newBlue = (H[0][0] * blueMatrix[y - 3][x - 3] + H[0][1] * blueMatrix[y - 3][x - 2] + H[0][2] * blueMatrix[y - 3][x - 1] + H[0][3] * blueMatrix[y - 3][x] + H[0][4] * blueMatrix[y - 3][x + 1] + H[0][5] * blueMatrix[y - 3][x + 2] + H[0][6] * blueMatrix[y - 3][x + 3]
                            + H[1][0] * blueMatrix[y - 2][x - 3] + H[1][1] * blueMatrix[y - 2][x - 2] + H[1][2] * blueMatrix[y - 2][x - 1] + H[1][3] * blueMatrix[y - 2][x] + H[1][4] * blueMatrix[y - 2][x + 1] + H[1][5] * blueMatrix[y - 2][x + 2] + H[1][6] * blueMatrix[y - 2][x + 3]
                            + H[2][0] * blueMatrix[y - 1][x - 3] + H[2][1] * blueMatrix[y - 1][x - 2] + H[2][2] * blueMatrix[y - 1][x - 1] + H[2][3] * blueMatrix[y - 1][x] + H[2][4] * blueMatrix[y - 1][x + 1] + H[2][5] * blueMatrix[y - 1][x + 2] + H[2][6] * blueMatrix[y - 1][x + 3]
                            + H[3][0] * blueMatrix[y][x - 3] + H[3][1] * blueMatrix[y][x - 2] + H[3][2] * blueMatrix[y][x - 1] + H[3][3] * blueMatrix[y][x] + H[3][4] * blueMatrix[y][x + 1] + H[3][5] * blueMatrix[y][x + 2] + H[3][6] * blueMatrix[y][x + 3]
                            + H[4][0] * blueMatrix[y + 1][x - 3] + H[4][1] * blueMatrix[y + 1][x - 2] + H[4][2] * blueMatrix[y + 1][x - 1] + H[4][3] * blueMatrix[y + 1][x] + H[4][4] * blueMatrix[y + 1][x + 1] + H[4][5] * blueMatrix[y + 1][x + 2] + H[4][6] * blueMatrix[y + 1][x + 3]
                            + H[5][0] * blueMatrix[y + 2][x - 3] + H[5][1] * blueMatrix[y + 2][x - 2] + H[5][2] * blueMatrix[y + 2][x - 1] + H[5][3] * blueMatrix[y + 2][x] + H[5][4] * blueMatrix[y + 2][x + 1] + H[5][5] * blueMatrix[y + 2][x + 2] + H[5][6] * blueMatrix[y + 2][x + 3]
                            + H[6][0] * blueMatrix[y + 3][x - 3] + H[6][1] * blueMatrix[y + 3][x - 2] + H[6][2] * blueMatrix[y + 3][x - 1] + H[6][3] * blueMatrix[y + 3][x] + H[6][4] * blueMatrix[y + 3][x + 1] + H[6][5] * blueMatrix[y + 3][x + 2] + H[6][6] * blueMatrix[y + 3][x + 3]) / c;
                    if (newBlue > 255)
                        newBlue = 255;
                    else if (newBlue < 0)
                        newBlue = 0;

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            Image pict = SwingFXUtils.toFXImage(result, null);
            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 7х7\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }
    private void filter3x3() {
        if (sourceImg != null) {
            int[] tmpArr = new int[9];
            for (int y = 1; y < sourceImgHeight - 1; y++) {
                for (int x = 1; x < sourceImgWidth - 1; x++) {
                    tmpArr[0] = redMatrix[y - 1][x - 1];
                    tmpArr[1] = redMatrix[y - 1][x];
                    tmpArr[2] = redMatrix[y - 1][x + 1];
                    tmpArr[3] = redMatrix[y][x - 1];
                    tmpArr[4] = redMatrix[y][x];
                    tmpArr[5] = redMatrix[y][x + 1];
                    tmpArr[6] = redMatrix[y + 1][x - 1];
                    tmpArr[7] = redMatrix[y + 1][x];
                    tmpArr[8] = redMatrix[y + 1][x + 1];

                    Arrays.sort(tmpArr);
                    int newRed = tmpArr[4];

                    tmpArr[0] = greenMatrix[y - 1][x - 1];
                    tmpArr[1] = greenMatrix[y - 1][x];
                    tmpArr[2] = greenMatrix[y - 1][x + 1];
                    tmpArr[3] = greenMatrix[y][x - 1];
                    tmpArr[4] = greenMatrix[y][x];
                    tmpArr[5] = greenMatrix[y][x + 1];
                    tmpArr[6] = greenMatrix[y + 1][x - 1];
                    tmpArr[7] = greenMatrix[y + 1][x];
                    tmpArr[8] = greenMatrix[y + 1][x + 1];

                    Arrays.sort(tmpArr);
                    int newGreen = tmpArr[4];

                    tmpArr[0] = blueMatrix[y - 1][x - 1];
                    tmpArr[1] = blueMatrix[y - 1][x];
                    tmpArr[2] = blueMatrix[y - 1][x + 1];
                    tmpArr[3] = blueMatrix[y][x - 1];
                    tmpArr[4] = blueMatrix[y][x];
                    tmpArr[5] = blueMatrix[y][x + 1];
                    tmpArr[6] = blueMatrix[y + 1][x - 1];
                    tmpArr[7] = blueMatrix[y + 1][x];
                    tmpArr[8] = blueMatrix[y + 1][x + 1];

                    Arrays.sort(tmpArr);
                    int newBlue = tmpArr[4];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            javafx.scene.image.Image pict = SwingFXUtils.toFXImage(result, null);

            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 3х3\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter5x5() {
        if (sourceImg != null) {
            int[] tmpArr = new int[25];
            for (int y = 2; y < sourceImgHeight - 2; y++) {
                for (int x = 2; x < sourceImgWidth - 2; x++) {
                    tmpArr[0] = redMatrix[y - 2][x - 2];
                    tmpArr[1] = redMatrix[y - 2][x - 1];
                    tmpArr[2] = redMatrix[y - 2][x];
                    tmpArr[3] = redMatrix[y - 2][x + 1];
                    tmpArr[4] = redMatrix[y - 2][x + 2];
                    tmpArr[5] = redMatrix[y - 1][x - 2];
                    tmpArr[6] = redMatrix[y - 1][x - 1];
                    tmpArr[7] = redMatrix[y - 1][x];
                    tmpArr[8] = redMatrix[y - 1][x + 1];
                    tmpArr[9] = redMatrix[y - 1][x + 2];
                    tmpArr[10] = redMatrix[y][x - 2];
                    tmpArr[11] = redMatrix[y][x - 1];
                    tmpArr[12] = redMatrix[y][x];
                    tmpArr[13] = redMatrix[y][x + 1];
                    tmpArr[14] = redMatrix[y][x + 2];
                    tmpArr[15] = redMatrix[y + 1][x - 2];
                    tmpArr[16] = redMatrix[y + 1][x - 1];
                    tmpArr[17] = redMatrix[y + 1][x];
                    tmpArr[18] = redMatrix[y + 1][x + 1];
                    tmpArr[19] = redMatrix[y + 1][x + 2];
                    tmpArr[20] = redMatrix[y + 2][x - 2];
                    tmpArr[21] = redMatrix[y + 2][x - 1];
                    tmpArr[22] = redMatrix[y + 2][x];
                    tmpArr[23] = redMatrix[y + 2][x + 1];
                    tmpArr[24] = redMatrix[y + 2][x + 2];

                    Arrays.sort(tmpArr);
                    int newRed = tmpArr[4];

                    tmpArr[0] = greenMatrix[y - 2][x - 2];
                    tmpArr[1] = greenMatrix[y - 2][x - 1];
                    tmpArr[2] = greenMatrix[y - 2][x];
                    tmpArr[3] = greenMatrix[y - 2][x + 1];
                    tmpArr[4] = greenMatrix[y - 2][x + 2];
                    tmpArr[5] = greenMatrix[y - 1][x - 2];
                    tmpArr[6] = greenMatrix[y - 1][x - 1];
                    tmpArr[7] = greenMatrix[y - 1][x];
                    tmpArr[8] = greenMatrix[y - 1][x + 1];
                    tmpArr[9] = greenMatrix[y - 1][x + 2];
                    tmpArr[10] = greenMatrix[y][x - 2];
                    tmpArr[11] = greenMatrix[y][x - 1];
                    tmpArr[12] = greenMatrix[y][x];
                    tmpArr[13] = greenMatrix[y][x + 1];
                    tmpArr[14] = greenMatrix[y][x + 2];
                    tmpArr[15] = greenMatrix[y + 1][x - 2];
                    tmpArr[16] = greenMatrix[y + 1][x - 1];
                    tmpArr[17] = greenMatrix[y + 1][x];
                    tmpArr[18] = greenMatrix[y + 1][x + 1];
                    tmpArr[19] = greenMatrix[y + 1][x + 2];
                    tmpArr[20] = greenMatrix[y + 2][x - 2];
                    tmpArr[21] = greenMatrix[y + 2][x - 1];
                    tmpArr[22] = greenMatrix[y + 2][x];
                    tmpArr[23] = greenMatrix[y + 2][x + 1];
                    tmpArr[24] = greenMatrix[y + 2][x + 2];

                    Arrays.sort(tmpArr);
                    int newGreen = tmpArr[4];

                    tmpArr[0] = blueMatrix[y - 2][x - 2];
                    tmpArr[1] = blueMatrix[y - 2][x - 1];
                    tmpArr[2] = blueMatrix[y - 2][x];
                    tmpArr[3] = blueMatrix[y - 2][x + 1];
                    tmpArr[4] = blueMatrix[y - 2][x + 2];
                    tmpArr[5] = blueMatrix[y - 1][x - 2];
                    tmpArr[6] = blueMatrix[y - 1][x - 1];
                    tmpArr[7] = blueMatrix[y - 1][x];
                    tmpArr[8] = blueMatrix[y - 1][x + 1];
                    tmpArr[9] = blueMatrix[y - 1][x + 2];
                    tmpArr[10] = blueMatrix[y][x - 2];
                    tmpArr[11] = blueMatrix[y][x - 1];
                    tmpArr[12] = blueMatrix[y][x];
                    tmpArr[13] = blueMatrix[y][x + 1];
                    tmpArr[14] = blueMatrix[y][x + 2];
                    tmpArr[15] = blueMatrix[y + 1][x - 2];
                    tmpArr[16] = blueMatrix[y + 1][x - 1];
                    tmpArr[17] = blueMatrix[y + 1][x];
                    tmpArr[18] = blueMatrix[y + 1][x + 1];
                    tmpArr[19] = blueMatrix[y + 1][x + 2];
                    tmpArr[20] = blueMatrix[y + 2][x - 2];
                    tmpArr[21] = blueMatrix[y + 2][x - 1];
                    tmpArr[22] = blueMatrix[y + 2][x];
                    tmpArr[23] = blueMatrix[y + 2][x + 1];
                    tmpArr[24] = blueMatrix[y + 2][x + 2];

                    Arrays.sort(tmpArr);
                    int newBlue = tmpArr[4];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            javafx.scene.image.Image pict = SwingFXUtils.toFXImage(result, null);

            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 5х5\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter7x7() {
        if (sourceImg != null) {
            int[] tmpArr = new int[49];
            for (int y = 3; y < sourceImgHeight - 3; y++) {
                for (int x = 3; x < sourceImgWidth - 3; x++) {
                    tmpArr[0] = redMatrix[y - 3][x - 3];
                    tmpArr[1] = redMatrix[y - 3][x - 2];
                    tmpArr[2] = redMatrix[y - 3][x - 1];
                    tmpArr[3] = redMatrix[y - 3][x];
                    tmpArr[4] = redMatrix[y - 3][x + 1];
                    tmpArr[5] = redMatrix[y - 3][x + 2];
                    tmpArr[6] = redMatrix[y - 3][x + 3];
                    tmpArr[7] = redMatrix[y - 2][x - 3];
                    tmpArr[8] = redMatrix[y - 2][x - 2];
                    tmpArr[9] = redMatrix[y - 2][x - 1];
                    tmpArr[10] = redMatrix[y - 2][x];
                    tmpArr[11] = redMatrix[y - 2][x + 1];
                    tmpArr[12] = redMatrix[y - 2][x + 2];
                    tmpArr[13] = redMatrix[y - 2][x + 3];
                    tmpArr[14] = redMatrix[y - 1][x - 3];
                    tmpArr[15] = redMatrix[y - 1][x - 2];
                    tmpArr[16] = redMatrix[y - 1][x - 1];
                    tmpArr[17] = redMatrix[y - 1][x];
                    tmpArr[18] = redMatrix[y - 1][x + 1];
                    tmpArr[19] = redMatrix[y - 1][x + 2];
                    tmpArr[20] = redMatrix[y - 1][x + 3];
                    tmpArr[21] = redMatrix[y][x - 3];
                    tmpArr[22] = redMatrix[y][x - 2];
                    tmpArr[23] = redMatrix[y][x - 1];
                    tmpArr[24] = redMatrix[y][x];
                    tmpArr[25] = redMatrix[y][x + 1];
                    tmpArr[26] = redMatrix[y][x + 2];
                    tmpArr[27] = redMatrix[y][x + 3];
                    tmpArr[28] = redMatrix[y + 1][x - 3];
                    tmpArr[29] = redMatrix[y + 1][x - 2];
                    tmpArr[30] = redMatrix[y + 1][x - 1];
                    tmpArr[31] = redMatrix[y + 1][x];
                    tmpArr[32] = redMatrix[y + 1][x + 1];
                    tmpArr[33] = redMatrix[y + 1][x + 2];
                    tmpArr[34] = redMatrix[y + 1][x + 3];
                    tmpArr[35] = redMatrix[y + 2][x - 3];
                    tmpArr[36] = redMatrix[y + 2][x - 2];
                    tmpArr[37] = redMatrix[y + 2][x - 1];
                    tmpArr[38] = redMatrix[y + 2][x];
                    tmpArr[39] = redMatrix[y + 2][x + 1];
                    tmpArr[40] = redMatrix[y + 2][x + 2];
                    tmpArr[41] = redMatrix[y + 2][x + 3];
                    tmpArr[42] = redMatrix[y + 3][x - 3];
                    tmpArr[43] = redMatrix[y + 3][x - 2];
                    tmpArr[44] = redMatrix[y + 3][x - 1];
                    tmpArr[45] = redMatrix[y + 3][x];
                    tmpArr[46] = redMatrix[y + 3][x + 1];
                    tmpArr[47] = redMatrix[y + 3][x + 2];
                    tmpArr[48] = redMatrix[y + 3][x + 3];

                    Arrays.sort(tmpArr);
                    int newRed = tmpArr[4];

                    tmpArr[0] = greenMatrix[y - 3][x - 3];
                    tmpArr[1] = greenMatrix[y - 3][x - 2];
                    tmpArr[2] = greenMatrix[y - 3][x - 1];
                    tmpArr[3] = greenMatrix[y - 3][x];
                    tmpArr[4] = greenMatrix[y - 3][x + 1];
                    tmpArr[5] = greenMatrix[y - 3][x + 2];
                    tmpArr[6] = greenMatrix[y - 3][x + 3];
                    tmpArr[7] = greenMatrix[y - 2][x - 3];
                    tmpArr[8] = greenMatrix[y - 2][x - 2];
                    tmpArr[9] = greenMatrix[y - 2][x - 1];
                    tmpArr[10] = greenMatrix[y - 2][x];
                    tmpArr[11] = greenMatrix[y - 2][x + 1];
                    tmpArr[12] = greenMatrix[y - 2][x + 2];
                    tmpArr[13] = greenMatrix[y - 2][x + 3];
                    tmpArr[14] = greenMatrix[y - 1][x - 3];
                    tmpArr[15] = greenMatrix[y - 1][x - 2];
                    tmpArr[16] = greenMatrix[y - 1][x - 1];
                    tmpArr[17] = greenMatrix[y - 1][x];
                    tmpArr[18] = greenMatrix[y - 1][x + 1];
                    tmpArr[19] = greenMatrix[y - 1][x + 2];
                    tmpArr[20] = greenMatrix[y - 1][x + 3];
                    tmpArr[21] = greenMatrix[y][x - 3];
                    tmpArr[22] = greenMatrix[y][x - 2];
                    tmpArr[23] = greenMatrix[y][x - 1];
                    tmpArr[24] = greenMatrix[y][x];
                    tmpArr[25] = greenMatrix[y][x + 1];
                    tmpArr[26] = greenMatrix[y][x + 2];
                    tmpArr[27] = greenMatrix[y][x + 3];
                    tmpArr[28] = greenMatrix[y + 1][x - 3];
                    tmpArr[29] = greenMatrix[y + 1][x - 2];
                    tmpArr[30] = greenMatrix[y + 1][x - 1];
                    tmpArr[31] = greenMatrix[y + 1][x];
                    tmpArr[32] = greenMatrix[y + 1][x + 1];
                    tmpArr[33] = greenMatrix[y + 1][x + 2];
                    tmpArr[34] = greenMatrix[y + 1][x + 3];
                    tmpArr[35] = greenMatrix[y + 2][x - 3];
                    tmpArr[36] = greenMatrix[y + 2][x - 2];
                    tmpArr[37] = greenMatrix[y + 2][x - 1];
                    tmpArr[38] = greenMatrix[y + 2][x];
                    tmpArr[39] = greenMatrix[y + 2][x + 1];
                    tmpArr[40] = greenMatrix[y + 2][x + 2];
                    tmpArr[41] = greenMatrix[y + 2][x + 3];
                    tmpArr[42] = greenMatrix[y + 3][x - 3];
                    tmpArr[43] = greenMatrix[y + 3][x - 2];
                    tmpArr[44] = greenMatrix[y + 3][x - 1];
                    tmpArr[45] = greenMatrix[y + 3][x];
                    tmpArr[46] = greenMatrix[y + 3][x + 1];
                    tmpArr[47] = greenMatrix[y + 3][x + 2];
                    tmpArr[48] = greenMatrix[y + 3][x + 3];

                    Arrays.sort(tmpArr);
                    int newGreen = tmpArr[4];

                    tmpArr[0] = blueMatrix[y - 3][x - 3];
                    tmpArr[1] = blueMatrix[y - 3][x - 2];
                    tmpArr[2] = blueMatrix[y - 3][x - 1];
                    tmpArr[3] = blueMatrix[y - 3][x];
                    tmpArr[4] = blueMatrix[y - 3][x + 1];
                    tmpArr[5] = blueMatrix[y - 3][x + 2];
                    tmpArr[6] = blueMatrix[y - 3][x + 3];
                    tmpArr[7] = blueMatrix[y - 2][x - 3];
                    tmpArr[8] = blueMatrix[y - 2][x - 2];
                    tmpArr[9] = blueMatrix[y - 2][x - 1];
                    tmpArr[10] = blueMatrix[y - 2][x];
                    tmpArr[11] = blueMatrix[y - 2][x + 1];
                    tmpArr[12] = blueMatrix[y - 2][x + 2];
                    tmpArr[13] = blueMatrix[y - 2][x + 3];
                    tmpArr[14] = blueMatrix[y - 1][x - 3];
                    tmpArr[15] = blueMatrix[y - 1][x - 2];
                    tmpArr[16] = blueMatrix[y - 1][x - 1];
                    tmpArr[17] = blueMatrix[y - 1][x];
                    tmpArr[18] = blueMatrix[y - 1][x + 1];
                    tmpArr[19] = blueMatrix[y - 1][x + 2];
                    tmpArr[20] = blueMatrix[y - 1][x + 3];
                    tmpArr[21] = blueMatrix[y][x - 3];
                    tmpArr[22] = blueMatrix[y][x - 2];
                    tmpArr[23] = blueMatrix[y][x - 1];
                    tmpArr[24] = blueMatrix[y][x];
                    tmpArr[25] = blueMatrix[y][x + 1];
                    tmpArr[26] = blueMatrix[y][x + 2];
                    tmpArr[27] = blueMatrix[y][x + 3];
                    tmpArr[28] = blueMatrix[y + 1][x - 3];
                    tmpArr[29] = blueMatrix[y + 1][x - 2];
                    tmpArr[30] = blueMatrix[y + 1][x - 1];
                    tmpArr[31] = blueMatrix[y + 1][x];
                    tmpArr[32] = blueMatrix[y + 1][x + 1];
                    tmpArr[33] = blueMatrix[y + 1][x + 2];
                    tmpArr[34] = blueMatrix[y + 1][x + 3];
                    tmpArr[35] = blueMatrix[y + 2][x - 3];
                    tmpArr[36] = blueMatrix[y + 2][x - 2];
                    tmpArr[37] = blueMatrix[y + 2][x - 1];
                    tmpArr[38] = blueMatrix[y + 2][x];
                    tmpArr[39] = blueMatrix[y + 2][x + 1];
                    tmpArr[40] = blueMatrix[y + 2][x + 2];
                    tmpArr[41] = blueMatrix[y + 2][x + 3];
                    tmpArr[42] = blueMatrix[y + 3][x - 3];
                    tmpArr[43] = blueMatrix[y + 3][x - 2];
                    tmpArr[44] = blueMatrix[y + 3][x - 1];
                    tmpArr[45] = blueMatrix[y + 3][x];
                    tmpArr[46] = blueMatrix[y + 3][x + 1];
                    tmpArr[47] = blueMatrix[y + 3][x + 2];
                    tmpArr[48] = blueMatrix[y + 3][x + 3];

                    Arrays.sort(tmpArr);
                    int newBlue = tmpArr[4];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            javafx.scene.image.Image pict = SwingFXUtils.toFXImage(result, null);

            imgView.setImage(pict);
            sourceImg = pict;
            arrayImgList.add(pict);
            count++;

            System.out.println("Фильтрация выполнена 7х7\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }
    private void openSliderBinary() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Бинаризация");
        stage.getIcons().add(new Image("Laptev/Visual/icon.png"));
        stage.setMinWidth(600);
        stage.setMinHeight(100);
        stage.setX(475);
        stage.setY(613);

        Slider slider = new Slider(0, 255, 200);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setBlockIncrement(10);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(4);

        VBox layout = new VBox();
        layout.getChildren().addAll(slider);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        slider.valueProperty().addListener((observable, oldValue, newValue) -> binaryFoo(newValue.intValue()));
    }

    private void openSliderBrightness() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Яркость");
        stage.getIcons().add(new Image("Laptev/Visual/icon.png"));
        stage.setMinWidth(600);
        stage.setMinHeight(100);
        stage.setX(475);
        stage.setY(613);

        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setBlockIncrement(5);
        slider.setMajorTickUnit(25);
        slider.setMinorTickCount(4);

        VBox layout = new VBox();
        layout.getChildren().addAll(slider);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        slider.valueProperty().addListener((observable, oldValue, newValue) -> brightnessFoo(newValue.intValue()));
    }

    private void openSliderContrast() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Котрастность");
        stage.getIcons().add(new Image("Laptev/Visual/icon.png"));
        stage.setMinWidth(600);
        stage.setMinHeight(100);
        stage.setX(475);
        stage.setY(613);

        Slider slider1 = new Slider(0, 10, 5);

        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setSnapToTicks(true);
        slider1.setBlockIncrement(0.2);
        slider1.setMajorTickUnit(1);
        slider1.setMinorTickCount(4);

        Slider slider2 = new Slider(0, 2, 1);
        slider2.setShowTickLabels(true);
        slider2.setShowTickMarks(true);
        slider2.setSnapToTicks(true);
        slider2.setBlockIncrement(0.1);
        slider2.setMajorTickUnit(0.5);
        slider2.setMinorTickCount(4);

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);
        layout.getChildren().addAll(slider1, slider2);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        slider1.valueProperty().addListener((observable, oldValue, newValue) -> contrastFoo(newValue.doubleValue()));
        slider2.valueProperty().addListener((observable, oldValue, newValue) -> u = newValue.doubleValue());
    }
}

*/
