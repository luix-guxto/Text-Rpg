package br.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    private BufferedImage image;

    // Carrega um arquivo de foto
    public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(getClass().getResource(path));
        return image;
    }
}