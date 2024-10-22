package utils;

import model.Image;

public class ImageIOHelper {

    public static Image loadImage(String filePath) {
        ImageHandler handler = ImageHandlerFactory.getHandler(filePath);
        return handler.loadImage(filePath);
    }

    public static void saveImage(String filePath, Image img) {
        ImageHandler handler = ImageHandlerFactory.getHandler(filePath);
        handler.saveImage(filePath, img);
    }
}
