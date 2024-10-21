package controller;

import model.Image;
import model.ImageOperations;
import utils.ImageIOHelper;

import java.util.HashMap;
import java.util.Map;

public class ImageController {

    private Map<String, Image> images;

    public ImageController() {
        this.images = new HashMap<>();
    }

    public void processCommand(String command) {
        String[] tokens = command.split(" ");
        switch (tokens[0]) {
            case "load":
                Image img = ImageIOHelper.loadImage(tokens[1]);
                images.put(tokens[2], img);
                break;
            case "save":
                ImageIOHelper.saveImage(tokens[1], images.get(tokens[2]));
                break;
            case "flip-horizontal":
                Image flippedH = ImageOperations.flipHorizontal(images.get(tokens[1]));
                images.put(tokens[2], flippedH);
                break;
            case "flip-vertical":
                Image flippedV = ImageOperations.flipVertical(images.get(tokens[1]));
                images.put(tokens[2], flippedV);
                break;
            case "brighten":
                int increment = Integer.parseInt(tokens[3]);
                Image brightened = ImageOperations.brighten(images.get(tokens[1]), increment);
                images.put(tokens[2], brightened);
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
}
