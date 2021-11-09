package Laptev.Core;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import static Laptev.Core.Matrix.*;

public class Controller {
    @FXML
    public ImageView imgView;
    public MenuItem save;
    public MenuItem undo_id;
    public MenuItem redo_id;
    public TextField text1;
    public TextField text2;
    public TextField text3;
    public TextField text4;
    public TextField text5;
    public TextField text6;
    public TextField text7;
    public TextField text8;
    public TextField text9;
    public Button btn;

    static ArrayList<Image> arrayImgList = new ArrayList<>();
    static int count;

    static File fileImg;

    static Image sourceImg;
    static BufferedImage source;
    static BufferedImage result;
    static int sourceImgWidth;
    static int sourceImgHeight;
    static double u = 1;

    static int[][] redMatrix;
    static int[][] greenMatrix;
    static int[][] blueMatrix;

    private static final int[][] matrix = new int[3][3];

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
    public void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Picture");
        fileChooser.setInitialDirectory(new File("D:\\pict"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileImg = fileChooser.showOpenDialog(null);

        if (fileImg != null) {
            System.out.println("Процесс открытия файла...");
            Image image = new Image(fileImg.toURI().toString());
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
    public void saveFile() throws IOException {
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
            createSlider("Бинаризация", 0, 255, 200, 10, 50, 4);
            System.out.println("Бинарное изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void binaryFoo(int brightnessCoef) {
        int black = 0;
        int white = 255;
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                int gray = (redMatrix[y][x] + greenMatrix[y][x] + blueMatrix[y][x]) / 3;

                Color newColor;
                if (gray >= brightnessCoef) {
                    newColor = new Color(white, white, white);
                } else {
                    newColor = new Color(black, black, black);
                }
                result.setRGB(x, y, newColor.getRGB());
            }
        }
        setImage(result);
    }

    private void setImage(BufferedImage result) {
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
                    int grey = (int) (redMatrix[y][x] * 0.299 + greenMatrix[y][x] * 0.587 + blueMatrix[y][x] * 0.114);

                    Color newColor = new Color(grey, grey, grey);
                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            setImage(result);

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
                    int newRed = 255 - redMatrix[y][x];
                    int newGreen = 255 - greenMatrix[y][x];
                    int newBlue = 255 - blueMatrix[y][x];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            setImage(result);
            sourceImg = SwingFXUtils.toFXImage(result, null);

            System.out.println("Негативное изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    @FXML
    public void brightness() {
        if (sourceImg != null) {
            createSlider("Яркость", 0, 100, 50, 5, 25, 4);
            System.out.println("Яркость изменена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void brightnessFoo(int c) {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                int newRed = (int) (c * Math.log(redMatrix[y][x] + 1));
                if (newRed > 255)
                    newRed = 255;

                int newGreen = (int) (c * Math.log(greenMatrix[y][x] + 1));
                if (newGreen > 255)
                    newGreen = 255;

                int newBlue = (int) (c * Math.log(blueMatrix[y][x] + 1));
                if (newBlue > 255)
                    newBlue = 255;

                Color newColor = new Color(newRed, newGreen, newBlue);

                result.setRGB(x, y, newColor.getRGB());
            }
        }
        setImage(result);
    }

    @FXML
    public void contrast() {
        if (sourceImg != null) {
            createSlider("Котрастность", 0, 10, 5, 0.2, 1, 4);
            System.out.println("Контрастность изменена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void contrastFoo(double c) {
        for (int y = 0; y < sourceImgHeight; y++) {
            for (int x = 0; x < sourceImgWidth; x++) {
                int newRed = (int) (c * Math.pow(redMatrix[y][x], u));
                if (newRed > 255)
                    newRed = 255;
                else if (newRed < 0)
                    newRed = 0;

                int newGreen = (int) (c * Math.pow(greenMatrix[y][x], u));
                if (newGreen > 255)
                    newGreen = 255;
                else if (newGreen < 0)
                    newGreen = 0;

                int newBlue = (int) (c * Math.pow(blueMatrix[y][x], u));
                if (newBlue > 255)
                    newBlue = 255;
                else if (newBlue < 0)
                    newBlue = 0;

                Color newColor = new Color(newRed, newGreen, newBlue);

                result.setRGB(x, y, newColor.getRGB());
            }
        }
        setImage(result);
    }

    private void createSlider(String title, int v, int v1, int v2, double inc, int major, int minor) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.getIcons().add(new Image("Laptev/Visual/icon.png"));
        stage.setMinWidth(600);
        stage.setMinHeight(100);
        stage.setX(475);
        stage.setY(613);

        Slider slider = new Slider(v, v1, v2);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setSnapToTicks(true);
        slider.setBlockIncrement(inc);
        slider.setMajorTickUnit(major);
        slider.setMinorTickCount(minor);

        VBox layout = new VBox();
        switch (title) {
            case "Бинаризация":
                layout = new VBox();
                layout.getChildren().addAll(slider);
                layout.setAlignment(Pos.CENTER);

                slider.valueProperty().addListener((observable, oldValue, newValue) -> binaryFoo(newValue.intValue()));
                break;
            case "Яркость":
                layout = new VBox();
                layout.getChildren().addAll(slider);
                layout.setAlignment(Pos.CENTER);

                slider.valueProperty().addListener((observable, oldValue, newValue) -> brightnessFoo(newValue.intValue()));
                break;
            case "Котрастность":
                Slider slider2 = new Slider(0, 2, 1);
                slider2.setShowTickLabels(true);
                slider2.setShowTickMarks(true);
                slider2.setSnapToTicks(true);
                slider2.setBlockIncrement(0.1);
                slider2.setMajorTickUnit(0.5);
                slider2.setMinorTickCount(4);

                layout = new VBox(10);
                layout.setAlignment(Pos.CENTER);
                layout.setSpacing(10);
                layout.getChildren().addAll(slider, slider2);

                slider.valueProperty().addListener((observable, oldValue, newValue) -> contrastFoo(newValue.doubleValue()));
                slider2.valueProperty().addListener((observable, oldValue, newValue) -> u = newValue.doubleValue());
                break;
        }
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void undo() {
        if (count > 0) {
            count--;
            imgView.setImage(arrayImgList.get(count));
        } else {
            System.out.println("Конец массива\n");
        }
    }

    @FXML
    public void redo() {
        if (count < arrayImgList.size() - 1) {
            count++;
            imgView.setImage(arrayImgList.get(count));
        } else {
            System.out.println("Конец массива\n");
        }
    }

    @FXML
    public void Filter() throws IOException {
        openMasks();
    }

    @FXML
    public void medianFilter() {
        filter(H1_5x5);
    }

    @FXML
    public void medianFilterPlus() {
        filterPlus(H1_5x5);
    }

    @FXML
    public void DFT() {
        if (sourceImg != null) {

           /* Mat image = new Mat();
            Mat bmat = new Mat();
            Mat m = new Mat();
            image = Imgcodecs.imread(fileImg.getAbsolutePath(), Imgcodecs.IMREAD_GRAYSCALE);

            Imgproc.cvtColor(m, image, Imgproc.COLOR_RGB2GRAY);
            image.convertTo(image, CvType.CV_64F);
            Core.dft(image,bmat);

            Core.idft(bmat,bmat);
            Core.normalize(bmat, bmat, 0, 255, Core.NORM_MINMAX);
            image.convertTo(bmat, CvType.CV_8U);*/

            System.out.println("Преобразование Фурье\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void openMasks() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Выбор маски");
        stage.getIcons().add(new Image("Laptev/Visual/icon.png"));

        File file1 = new File("D:/Masks/H1.png");
        File file2 = new File("D:/Masks/H1_5x5.png");
        File file3 = new File("D:/Masks/H1_7x7.png");
        File file4 = new File("D:/Masks/H4.png");
        File file5 = new File("D:/Masks/H5.png");
        File file6 = new File("D:/Masks/H6.jpg");

        ImageView img1 = img(file1);
        ImageView img2 = img(file2);
        ImageView img3 = img(file3);
        ImageView img4 = img(file4);
        ImageView img5 = img(file5);
        ImageView img6 = img(file6);

        GridPane layout = new GridPane();

        layout.getColumnConstraints().add(new ColumnConstraints(200));
        layout.getColumnConstraints().add(new ColumnConstraints(200));
        layout.getColumnConstraints().add(new ColumnConstraints(200));
        layout.getColumnConstraints().add(new ColumnConstraints(200));
        layout.getColumnConstraints().add(new ColumnConstraints(200));
        layout.getRowConstraints().add(new RowConstraints(200));
        layout.getRowConstraints().add(new RowConstraints(200));
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(30, 30, 30, 30));

        Label label1 = new Label("Низкочастотные маски:");
        Label label2 = new Label("Высокочастотные маски:");
        Label label3 = new Label("Своя маска");
        label1.setFont(new Font(16));
        label2.setFont(new Font(16));
        label3.setFont(new Font(16));

        Button button = new Button("Своя маска");
        button.setFont(new Font(16));
        button.setMinSize(100, 60);

        layout.add(label1, 0, 0);
        layout.add(img1, 1, 0);
        layout.add(img2, 2, 0);
        layout.add(img3, 3, 0);
        layout.add(label2, 0, 1);
        layout.add(img4, 1, 1);
        layout.add(img5, 2, 1);
        layout.add(img6, 3, 1);
        layout.add(button, 4, 1);

        stage.setScene(new Scene(layout));
        stage.show();

        img1.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H1\n");
            filter(H1, c1);
            stage.close();
        });
        img2.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H1_5х5\n");
            filter(H1_5x5, c1_5x5);
            stage.close();
        });
        img3.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H1_7х7\n");
            filter(H1_7x7, c1_7x7);
            stage.close();
        });
        img4.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H4\n");
            filter(H4, 1);
            stage.close();
        });
        img5.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H5\n");
            filter(H5, 1);
            stage.close();
        });
        img6.setOnMouseClicked(e -> {
            System.out.println("Выбрана маска H6\n");
            filter(H6, 1);
            stage.close();
        });
        button.setOnMouseClicked(e -> {
            System.out.println("Выбрана своя маска\n");
            try {
                creatingMask();
            } catch (IOException ioException) {
                System.out.println("Ошибка открытия окна для создание маски");
            }
            int x_y = matrix.length;
            int c = 0;
            for (int[] matrix : matrix) {
                for (int j = 0; j < x_y; j++) {
                    c += matrix[j];
                }
            }
            filter(matrix, c);
            stage.close();
        });
    }

    private ImageView img(File file) throws MalformedURLException {
        String localUrl = file.toURI().toURL().toString();
        Image image1 = new Image(localUrl, 150, 150, false, false);
        return new ImageView(image1);
    }

    private void filterPlus(int[][] H) {
        if (sourceImg != null) {
            System.out.println("Фильтрация выполняется");

            int board = (H.length - 1) / 2;
            int boardHeight = sourceImgHeight - board;
            int boardWidth = sourceImgWidth - board;
            int[] tmpArr1 = new int[H.length + H.length - 1];
            int[] tmpArr2 = new int[H.length + H.length - 1];
            int[] tmpArr3 = new int[H.length + H.length - 1];

            for (int y = board; y < boardHeight; y++) {
                for (int x = board; x < boardWidth; x++) {
                    int yMask = 0, xMask = 0;
                    int count = 0;
                    for (int yImg = y - board; yMask < H.length; yImg++, yMask++, count++) {
                        tmpArr1[count] = redMatrix[yImg][x];
                        tmpArr2[count] = greenMatrix[yImg][x];
                        tmpArr3[count] = blueMatrix[yImg][x];
                    }
                    for (int xImg = x - board; xMask < H.length / 2; xImg++, xMask++, count++) {
                        tmpArr1[count] = redMatrix[y][xImg];
                        tmpArr2[count] = greenMatrix[y][xImg];
                        tmpArr3[count] = blueMatrix[y][xImg];
                    }
                    for (int xImg = x; xMask < H.length / 2; xImg++, xMask++, count++) {
                        tmpArr1[count] = redMatrix[y][xImg];
                        tmpArr2[count] = greenMatrix[y][xImg];
                        tmpArr3[count] = blueMatrix[y][xImg];
                    }

                    Arrays.sort(tmpArr1);
                    int newRed = tmpArr1[tmpArr1.length / 2];
                    Arrays.sort(tmpArr2);
                    int newGreen = tmpArr2[tmpArr2.length / 2];
                    Arrays.sort(tmpArr3);
                    int newBlue = tmpArr3[tmpArr2.length / 2];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            setImage(result);
            sourceImg = SwingFXUtils.toFXImage(result, null);
            System.out.println("Фильтрация выполнена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter(int[][] H) {
        if (sourceImg != null) {
            System.out.println("Фильтрация выполняется");

            int board = (H.length - 1) / 2;
            int boardHeight = sourceImgHeight - board;
            int boardWidth = sourceImgWidth - board;
            int[] tmpArr1 = new int[H.length * H.length];
            int[] tmpArr2 = new int[H.length * H.length];
            int[] tmpArr3 = new int[H.length * H.length];

            for (int y = board; y < boardHeight; y++) {
                for (int x = board; x < boardWidth; x++) {
                    int yMask = 0, xMask = 0;

                    for (int yImg = y - board; yMask < H.length; yImg++, yMask++) {
                        for (int xImg = x - board; xMask < H.length; xImg++, xMask++) {
                            tmpArr1[yMask * xMask] = redMatrix[yImg][xImg];
                            tmpArr2[yMask * xMask] = greenMatrix[yImg][xImg];
                            tmpArr3[yMask * xMask] = blueMatrix[yImg][xImg];
                        }
                        xMask = 0;
                    }
                    Arrays.sort(tmpArr1);
                    int newRed = tmpArr1[tmpArr1.length / 2];
                    Arrays.sort(tmpArr2);
                    int newGreen = tmpArr2[tmpArr2.length / 2];
                    Arrays.sort(tmpArr3);
                    int newBlue = tmpArr3[tmpArr2.length / 2];

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            setImage(result);
            sourceImg = SwingFXUtils.toFXImage(result, null);

            System.out.println("Фильтрация выполнена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    private void filter(int[][] H, int c) {
        System.out.println("Фильтрация выполняется");
        if (sourceImg != null) {
            int board = (H.length - 1) / 2;
            int boardHeight = sourceImgHeight - board;
            int boardWidth = sourceImgWidth - board;

            for (int y = board; y < boardHeight; y++) {
                for (int x = board; x < boardWidth; x++) {
                    int yMask = 0, xMask = 0;
                    int newRed = 0;
                    int newGreen = 0;
                    int newBlue = 0;
                    for (int yImg = y - board; yMask < H.length; yImg++, yMask++) {
                        for (int xImg = x - board; xMask < H.length; xImg++, xMask++) {
                            newRed += redMatrix[yImg][xImg] * H[yMask][xMask];
                            newGreen += greenMatrix[yImg][xImg] * H[yMask][xMask];
                            newBlue += blueMatrix[yImg][xImg] * H[yMask][xMask];
                        }
                        xMask = 0;
                    }

                    newRed /= c;
                    newGreen /= c;
                    newBlue /= c;

                    if (newRed > 255)
                        newRed = 255;
                    else if (newRed < 0)
                        newRed = 0;

                    if (newGreen > 255)
                        newGreen = 255;
                    else if (newGreen < 0)
                        newGreen = 0;

                    if (newBlue > 255)
                        newBlue = 255;
                    else if (newBlue < 0)
                        newBlue = 0;

                    Color newColor = new Color(newRed, newGreen, newBlue);

                    result.setRGB(x, y, newColor.getRGB());
                }
            }
            setImage(result);
            sourceImg = SwingFXUtils.toFXImage(result, null);

            System.out.println("Фильтрация выполнена\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    public void creatingMask() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Laptev/Visual/Mask.fxml"));
        Parent root = loader.load();

        stage.getIcons().add(new Image("/Laptev/Visual/icon.png"));
        stage.setTitle("Создание маски");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void okAction() {
        Stage stage = (Stage) btn.getScene().getWindow();

        matrix[0][0] = Integer.parseInt(text1.getText());
        matrix[0][1] = Integer.parseInt(text2.getText());
        matrix[0][2] = Integer.parseInt(text3.getText());
        matrix[1][0] = Integer.parseInt(text4.getText());
        matrix[1][1] = Integer.parseInt(text5.getText());
        matrix[1][2] = Integer.parseInt(text6.getText());
        matrix[2][0] = Integer.parseInt(text7.getText());
        matrix[2][1] = Integer.parseInt(text8.getText());
        matrix[2][2] = Integer.parseInt(text9.getText());

        stage.close();
    }
}