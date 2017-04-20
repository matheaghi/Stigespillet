package stigespillet;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 60, height = 60;
	public static BufferedImage like, heart, laugh, smile, oops, tear, angry, grin, wink, loveeyes;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/emoticons2.png"));
		
		like = sheet.crop(0, 0, width, height);
		heart = sheet.crop(width, 0, width, height);
		laugh = sheet.crop(width*2, 0, width, height);
		smile = sheet.crop(width*3, 0, width, height);
		oops = sheet.crop(width*4, 0, width, height);
		tear = sheet.crop(width*5, 0, width, height);
		angry = sheet.crop(width*6, 0, width, height);
		grin = sheet.crop(width*7, 0, width, height);
		wink = sheet.crop(width*8, 0, width, height);
		loveeyes = sheet.crop(width*9, 0, width, height);
		
	}
	
}
