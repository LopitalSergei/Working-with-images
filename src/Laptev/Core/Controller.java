package Laptev.Core;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Laptev.Core.Matrix.*;

public class Controller {
    @FXML
    public ImageView imgView;

    static ArrayList<Image> arrayImgList = new ArrayList<>();
    static int count;
    static Image sourceImg;
    static BufferedImage source;
    static BufferedImage result;
    static int sourceImgWidth;
    static int sourceImgHeight;
    static double u = 1;

    static int[][] redMatrix;
    static int[][] greenMatrix;
    static int[][] blueMatrix;

    public ImageView getImgView() {
        return imgView;
    }

    private void setMatrix() {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                Color color = new Color(source.getRGB(x, y));

                redMatrix[y][x] = color.getRed();
                greenMatrix[y][x] = color.getGreen();
                blueMatrix[y][x] = color.getBlue();
            }
        }
    }

    @FXML
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Picture");
        fileChooser.setInitialDirectory(new File("D:\\pict"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println("Процесс открытия файла...");
            Image image = new Image(file.toURI().toString());
            imgView.setImage(image);
            sourceImg = image;
            sourceImgWidth = (int) image.getWidth();
            sourceImgHeight = (int) image.getHeight();
            source = SwingFXUtils.fromFXImage(sourceImg, null);
            result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

            arrayImgList.clear();
            arrayImgList.add(image);
            count = 0;

            redMatrix = new int[sourceImgHeight][sourceImgWidth];
            greenMatrix = new int[sourceImgHeight][sourceImgWidth];
            blueMatrix = new int[sourceImgHeight][sourceImgWidth];

            setMatrix();

            System.out.println("Файл открыт\n");
        } else {
            System.out.println("Файл не удалось открыть\n");
        }
    }

    @FXML
    private void saveFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("D:\\save_pict"));
        fileChooser.setTitle("Save Picture");
        fileChooser.setInitialFileName("new picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            System.out.println("Процесс сохранения файла...");

            if (sourceImg != null) {

                ImageIO.write(SwingFXUtils.fromFXImage(imgView.getImage(), null), "png", file);

                System.out.println("Файл сохранен\n");

            } else System.out.println("Файл не найден\n");

        } else {
            System.out.println("Файл не удалось сохранить\n");
        }
    }

    @FXML
    public void binary() {
        if (sourceImg != null) {
            openSliderBinary();
            System.out.println("Бинарное изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    int black = 0;
    int white = 255;

    private void binaryFoo(int brightnessCoef) {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                Color color = new Color(source.getRGB(x, y));

                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color newColor;

                if (gray >= brightnessCoef) {
                    newColor = new Color(white, white, white);
                } else {
                    newColor = new Color(black, black, black);
                }
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        Image pict = SwingFXUtils.toFXImage(result, null);
        imgView.setImage(pict);
        arrayImgList.add(pict);
        count++;
    }

    @FXML
    public void halftone() {
        if (sourceImg != null) {
            for (int y = 0; y < sourceImgHeight; y++) {
                for (int x = 0; x < sourceImgWidth; x++) {
                    Color color = new Color(source.getRGB(x, y));

                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);

                    Color newColor = new Color(grey, grey, grey);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }

            Image pict = SwingFXUtils.toFXImage(result, null);
            imgView.setImage(pict);
            arrayImgList.add(pict);
            count++;

            System.out.println("Полутоновое изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    @FXML
    public void negative() {
        if (sourceImg != null) {
            for (int y = 0; y < sourceImgHeight; y++) {
                for (int x = 0; x < sourceImgWidth; x++) {
                    Color color = new Color(source.getRGB(x, y));

                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();

                    int newRed = 255 - red;
                    int newGreen = 255 - green;
                    int newBlue = 255 - blue;

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            Image pict = SwingFXUtils.toFXImage(result, null);
            imgView.setImage(pict);
            arrayImgList.add(pict);
            count++;

            System.out.println("Негативное изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    @FXML
    public void brightness() {
        if (sourceImg != null) {
            openSliderBrightness();
            System.out.println("Яркость изменена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void brightnessFoo(int c) {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                Color color = new Color(source.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newRed = (int) (c * Math.log(red + 1));
                if (newRed > 255)
                    newRed = 255;
                int newGreen = (int) (c * Math.log(green + 1));
                if (newGreen > 255)
                    newGreen = 255;
                int newBlue = (int) (c * Math.log(blue + 1));
                if (newBlue > 255)
                    newBlue = 255;

                Color newColor = new Color(newRed, newGreen, newBlue);

                result.setRGB(x, y, newColor.getRGB());
            }
        }
        Image pict = SwingFXUtils.toFXImage(result, null);
        imgView.setImage(pict);
        arrayImgList.add(pict);
        count++;
    }

    @FXML
    public void contrast() {
        if (sourceImg != null) {
            openSliderContrast();
            System.out.println("Контрастность изменена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void contrastFoo(double c) {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                Color color = new Color(source.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newRed = (int) (c * Math.pow(red, u));
                if (newRed > 255)
                    newRed = 255;
                else if (newRed < 0)
                    newRed = 0;

                int newGreen = (int) (c * Math.pow(green, u));
                if (newGreen > 255)
                    newGreen = 255;
                else if (newGreen < 0)
                    newGreen = 0;

                int newBlue = (int) (c * Math.pow(blue, u));
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
        arrayImgList.add(pict);
        count++;
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
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();

        slider1.valueProperty().addListener((observable, oldValue, newValue) -> contrastFoo(newValue.doubleValue()));
        slider2.valueProperty().addListener((observable, oldValue, newValue) -> u = newValue.doubleValue());
    }

    public void undo() {
        if (count > 0) {
            count--;
            imgView.setImage(arrayImgList.get(count));
        } else {
            System.out.println("Конец массива\n");
        }
    }

    public void redo() {
        if (count < arrayImgList.size() - 1) {
            count++;
            imgView.setImage(arrayImgList.get(count));
        } else {
            System.out.println("Конец массива\n");
        }
    }

    @FXML
    public void lfFilter() {
        filter3x3(H1, c1);
        //    filter5x5(H1_5x5, c1_5x5);
        //    filter7x7(H1_7x7, c1_7x7);
    }

    @FXML
    public void hfFilter() {
        filter3x3(H4, 1);
    }

    @FXML
    public void medianFilter(){
        filter3x3();
    }
    void filter3x3(){
        if (sourceImg != null) {
            int [] tmpArr = new int[9];
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