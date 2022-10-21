package br.sprites;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;

	// Coleta uma foto carregada
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}

	// Retira um sprite da foto
	public BufferedImage getSprite(int x, int y, int width, int heigth) {
		BufferedImage sprite = image.getSubimage(x, y, width, heigth);
		return sprite;
	}
}