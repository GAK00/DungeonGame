package world.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MiniMap
{
	private Map map;
	private int xDimension;
	private int yDimension;
	private int roomDimension;

	public MiniMap(Map map, Dimension screen)
	{
		
		this.map = map;
		int y = (int) screen.getHeight();
		int x = (int) screen.getWidth();
		if ((x/(int)map.getSize().getWidth()) > (y/(int)map.getSize().getHeight()))
		{
			roomDimension = (y / (int) map.getSize().getHeight());
			roomDimension = (roomDimension / 22) * 7;
			yDimension = roomDimension * (int) map.getSize().getHeight();
			xDimension = roomDimension * (int) map.getSize().getWidth();
		} else
		{
			roomDimension = (x / (int) map.getSize().getWidth());
			roomDimension = (roomDimension / 22) * 7;
			yDimension = roomDimension * (int) map.getSize().getHeight();
			xDimension = roomDimension * (int) map.getSize().getWidth();
		}
	}
	public MiniMap(Map map, Dimension screen, int size)
	{
		this.map = map;
		int y = (int) screen.getHeight();
		int x = (int) screen.getWidth();
		if ((x/(int)map.getSize().getWidth()) > (y/(int)map.getSize().getHeight()))
		{
			roomDimension = (y / (int) map.getSize().getHeight());
			roomDimension = (roomDimension / size) * 7;
			yDimension = roomDimension * (int) map.getSize().getHeight();
			xDimension = roomDimension * (int) map.getSize().getWidth();
			
		} else
		{
			roomDimension = (x / (int) map.getSize().getWidth());
			roomDimension = (roomDimension / size) * 7;
			yDimension = roomDimension * (int) map.getSize().getHeight();
			xDimension = roomDimension * (int) map.getSize().getWidth();
		}
	}

	public BufferedImage render()
	{

		BufferedImage miniMap = new BufferedImage(xDimension, yDimension, BufferedImage.TYPE_INT_ARGB);
		// int currentX = (int) map.getCurrentPos().getWidth()-1;
		// int currentY = (int) map.getCurrentPos().getHeight()-1;
		for (int x = 0; x < (int) map.getSize().getWidth(); x++)
		{
			for (int y = 0; y < (int) map.getSize().getHeight(); y++)
			{
				int currentX = roomDimension * x;
				int currentY = roomDimension * y;
				for (int roomx = 0; roomx < 7; roomx++)
				{
					for (int roomy = 0; roomy< 7; roomy++)
					{
						int Rx = currentX+(roomx*(roomDimension/7));
						int Ry = currentY+(roomy*(roomDimension/7));
						Color color;
						if(((int)map.getCurrentPos().getWidth()-1==x&&(int)map.getCurrentPos().getHeight()-1==y)&&map.getCurrentRoom().getTile(new Dimension(roomx+1,roomy+1)).isInhabited())
						{
							color = Color.RED;
						}
						else if(map.getRoomFromDimensinon(new Dimension(x,y)).isDiscovered())
						{
							color = map.getRoomFromDimensinon(new Dimension(x,y)).getTile(new Dimension(roomx+1,roomy+1)).getColor();
						}
						else
						{
							color = Color.LIGHT_GRAY;
						}
						for (int tilex = 0; tilex < roomDimension/7; tilex++)
						{
							for (int tiley = 0; tiley <roomDimension/7;tiley++)
							{
								
						miniMap.setRGB(Rx+tilex, Ry+tiley, color.getRGB());
						}
						}
					}
				}
			}
		}
//		try{
//		ImageIO.write(miniMap, "PNG", new File("/Users/gkun9931/Desktop/Pictures/Cancer"+"test"+".png"));}
//		
//		
//	
//	catch(IOException ie)
//	{
//		ie.printStackTrace();
//	}
		return miniMap;
	}

}
