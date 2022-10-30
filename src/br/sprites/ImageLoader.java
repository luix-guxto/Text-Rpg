package br.sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ImageLoader {

    // Carrega um arquivo de foto
    public BufferedImage loadImage(String path) throws IOException{
        return ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
    }
}