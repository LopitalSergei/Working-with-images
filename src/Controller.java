import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
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

public class Controller {

    @FXML
    public MenuItem openFile;
    public MenuItem saveFile;
    public ImageView imgView;
    public VBox MainScene;
    public MenuItem binary;
    public MenuItem halftone;
    public MenuItem negative;
    public MenuItem brightness;
    public MenuItem contrast;

    static Image sourceImg = null;
    static double u = 1;

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
            System.out.println("Процесс открытия файла");
            Image image = new Image(file.toURI().toString());
            imgView.setImage(image);
            sourceImg = image;

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
            System.out.println("Процесс сохранения файла");

            if (imgView.getImage() != null) {

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

    private void binaryFoo(int brightnessCoef) {
        BufferedImage source = SwingFXUtils.fromFXImage(sourceImg, null);
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        int black = 0;
        int white = 255;

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
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
    }

    @FXML
    public void halftone() {
        if (sourceImg != null) {
            BufferedImage source = SwingFXUtils.fromFXImage(sourceImg, null);
            BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

            for (int x = 0; x < source.getWidth(); x++) {
                for (int y = 0; y < source.getHeight(); y++) {
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

            System.out.println("Полутоновое изображение\n");
        } else {
            System.out.println("Изображение не найдено\n");
        }
    }

    @FXML
    public void negative() {
        if (sourceImg != null) {
            BufferedImage source = SwingFXUtils.fromFXImage(sourceImg, null);
            BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

            for (int x = 0; x < source.getWidth(); x++) {
                for (int y = 0; y < source.getHeight(); y++) {

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
            sourceImg = pict;
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
        BufferedImage source = SwingFXUtils.fromFXImage(sourceImg, null);
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
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
        BufferedImage source = SwingFXUtils.fromFXImage(sourceImg, null);
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {
                Color color = new Color(source.getRGB(x, y));

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newRed = (int) (c * Math.pow(red, u));
                if (newRed > 255)
                    newRed = 255;
                int newGreen = (int) (c * Math.pow(green, u));
                if (newGreen > 255)
                    newGreen = 255;
                int newBlue = (int) (c * Math.pow(blue, u));
                if (newBlue > 255)
                    newBlue = 255;

                Color newColor = new Color(newRed, newGreen, newBlue);

                result.setRGB(x, y, newColor.getRGB());
            }
        }
        Image pict = SwingFXUtils.toFXImage(result, null);
        imgView.setImage(pict);
    }

    private void openSliderBinary() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Бинаризация");
        stage.getIcons().add(new Image("icon.png"));
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
        stage.getIcons().add(new Image("icon.png"));
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
        stage.getIcons().add(new Image("icon.png"));
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
}