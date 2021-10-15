import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
public class Filter {
void filter3x3(int[][] H, int c) {
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

    void filter5x5(int[][] H, int c) {
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

    void filter7x7(int[][] H, int c) {
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
}
