package br.sprites;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	@SuppressWarnings("all")
	private BufferedImage image; // variavel da imagem usada para o recorte de sprites

	// Coleta uma foto carregada pelo construtor
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}

	// Retira um sprite da foto
	public BufferedImage getSprite(int x, int y, int width, int heigth) {
		return image.getSubimage(x, y, width, heigth);
	}
}