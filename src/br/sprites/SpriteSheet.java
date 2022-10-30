package br	.sprites														;
import 	java.awt.image.BufferedImage										;

@SuppressWarnings("all")
public class SpriteSheet
{
	private BufferedImage image												;
	public 	SpriteSheet				(BufferedImage image)
	{
		this.image=image													;
	}
	public 	BufferedImage getSprite	(int x, int y, int width, int heigth)
	{
		return image.getSubimage	(x, y, width, heigth)					;
	}
}