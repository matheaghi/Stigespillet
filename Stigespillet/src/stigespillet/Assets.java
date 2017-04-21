package stigespillet;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Assets {

	private static final int width = 60, height = 60;
	public static BufferedImage like, heart, laugh, smile, oops, tear, angry, grin, wink, loveeyes;
	private List<BufferedImage> emojis = new ArrayList<BufferedImage>();
	
	
	public Assets(){
		makeList();
	}
	
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
	
	public void makeList(){
		emojis.add(smile);
		emojis.add(wink);
		emojis.add(loveeyes);
		emojis.add(grin);
		emojis.add(angry);
		emojis.add(tear);
		emojis.add(oops);
		emojis.add(laugh);
		emojis.add(heart);
		emojis.add(like);
		
	}
	
	public BufferedImage getIcon(){
		int nummer = (int)(Math.random() * 10);
		BufferedImage emoji = emojis.get(nummer);
		emojis.remove(nummer);
		return emoji;
		
	}
	
}
