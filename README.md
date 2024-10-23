# Introduction
In this assignment we are building an image processing application that supports both text-based and GUI-based user interfaces. This application will alllow you to perform
multiple functions like loading an image, manipulate the image(blur, horizonatal-flip, greyscale etc), and also save this resulting.
It has been designed in such a way additional operations can be added easily in the future.

## Features and their respective files:

The operations performed as well as the files used for it are written below to give a high-level overview.

## Loading and Saving Images below files are all in the package utils:
- This is done using the files present in the utils folder.
- The interface names ImageHandler() is used to hold the load and save images
  so that interface can be used to rewrite same method if required elsewhere.
- StandardImageHandler(): This file implements the ImageHandler methods for our assignment
  
      - loadImage(): after using ImageIO.read() to read the image from the file path and store it in an object.
                     It takes in the width and height and then extracts the rgb values using getRGB and then sets the
                     values obtained using setPixelloads image of specific size after making use of getRGB and setPixel.

      - saveImage(): used to save the custom image depending on type png or jpg. It then creates a image of the same type
                     with height and width and also retriving rgb values like loadImage() and then using bit shifting.
                     It also makes use of a helper function to get the required file-extension.

- PPMImageHandler(): Used to create the file implementation for ppm specifically. Majority of code already given by professor.
                       This implementation is to store pixel data in a text form. This class provides methods to convert them into a more manageable internal format.

- ImageIOHelper() : Used to provide static methods to load and save images.Mainly used to push tasks to ImageHandlerFactory.
                    It makes sure to handle different kinds of images that are being used.After getting handler from ImageHandlerFactory
                    and the necessary path it will return it to the save method.

- ImageHandlerFactory(): Used to create instances of Image Handler based on the input. It has two methods getFileextension and
                         getHandler. The getFileExtension is used to provide us with the image handler instance
                         whereas the image getFileExtension is needed to get the required file extension to the getHandler so
                         it can be returned to ImageIOHelper class which calls it in its methods.

## The model and the operations performed using it:
- ImageHandler(): Used to hold the dimensions of image and pixel data in 3D array. It has the methods getwidth(), getheight(),
                  getPixel() and setPixel(). This class is used for easy manipulation of an image's pixel data, which can be useful
                  for operations in ImageModel.
- ImgModel(): The interface is used to hold all the operations which are performed by model.
              This interface makes it easy to extend the current operations or for future operations.

- ImageModel(): This class contains methods to manipulate images in multiple ways by splitting them into RGB channels,
                combining channels, flipping, applying filters etc.
                It holds the method like luma, blur, flip horizontal etc.

- RGBImage(): This class is an extension of the current Image class. It provides the methods to extract the red,
              green and blue from the particular images. The results returned in the methods of this class
              are used to set the pixels.

## Supported Commands:

The following commands are supported by the Image Processing Program:

- **`load <input_file_path> <reference_name>`**  
  Loads an image from the specified file and stores it with the given reference name.

- **`save <save_path> <output_name>`**  
  Saves the image referenced by `<output_name>` to the specified path.

- **`horizontal-flip <reference_name> <output_name>`**  
  Creates a horizontally flipped version of the image referenced by `<reference_name>`.

- **`vertical-flip <reference_name> <output_name>`**  
  Creates a vertically flipped version of the image referenced by `<reference_name>`.

- **`brighten <reference_name> <output_name> <increment>`**  
  Brightens the image referenced by `<reference_name>` by the specified increment.

- **`blur <reference_name> <output_name>`**  
  Applies a blur effect to the image referenced by `<reference_name>`.

- **`sepia <reference_name> <output_name>`**  
  Applies a sepia tone to the image referenced by `<reference_name>`.

- **`sharpen <reference_name> <output_name>`**  
  Sharpens the image referenced by `<reference_name>`.

- **`greyScale <reference_name> <output_name>`**  
  Converts the image referenced by `<reference_name>` to greyscale.

- **`rgb-split <reference_name> <red_output> <green_output> <blue_output>`**  
  Splits the image into its RGB components.

- **`rgb-combine <output_name> <red_reference> <green_reference> <blue_reference>`**  
  Combines RGB components back into a single image.

- **`run <script_path>`**  
  Executes a series of commands from a script file.


## How to run script file:
- Go to the Main.java and click the run button.
- Go to the command line.
- A text file called `script.txt` has been pre-written under the src.
- And type this `run src/script.txt` in command line to run the script.

## Citation for picture: 
- (n.d.). https://pngtree.com/freepng/checkerboard-clipart-check-chess-game-checker-board_6166270.html