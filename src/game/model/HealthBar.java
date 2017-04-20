package game.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class HealthBar
{
	private int width;
	private int height;
	private Color color;
	private int colorDraw;
	private int borderWidth;
	private int borderHeight;
	private static Color transparent;

	public HealthBar(int width, int height, int maxHealth, int health)
	{
		transparent = Color.white;
		this.width = (width / (maxHealth*3)) * maxHealth;
		borderWidth = this.width / 8;
		if (borderWidth == 0)
		{
			borderWidth = 1;
		}
		colorDraw = (width /(maxHealth*3)) * health;
		this.height = height / 25;
		borderHeight = this.height / 4;
		if (borderHeight == 0)
		{
			borderHeight = 1;
		}
		double healthPercent = ((double) health / (double) maxHealth);
		this.color = Color.RED;
		if (Double.compare(healthPercent, .8) > 0)
		{
			this.color = Color.GREEN;
		} else if (Double.compare(healthPercent, .4) > 0)
		{
			this.color = Color.YELLOW;
		}

	}

	public BufferedImage render()
	{
		BufferedImage healthBar = new BufferedImage(width + (borderWidth * 2), height + (borderHeight * 2), BufferedImage.TYPE_INT_ARGB);

		drawBorder(healthBar);
		for (int x = 0; x <width; x++)
		{
			for (int y = 0; y <= height; y++)
			{
				if (x <= colorDraw)
				{
					healthBar.setRGB(x + borderWidth, y + borderHeight, color.getRGB());
				} else
				{
					healthBar.setRGB(x + borderWidth, y + borderHeight, transparent.getRGB());
				}
			}
		}
		return healthBar;
	}

	private void drawBorder(BufferedImage healthBar)
	{
		for(int pos = 0; pos<borderWidth;pos++)
		{
			for(int y =0; y<healthBar.getHeight();y++)
			{
				healthBar.setRGB(pos,y,Color.gray.getRGB());
			}
		}
		for(int pos = 0; pos<borderWidth;pos++)
		{
			for(int y =0; y<healthBar.getHeight();y++)
			{
				healthBar.setRGB(pos+width+borderWidth,y,Color.gray.getRGB());
			}
		}
		for(int pos = 0; pos<borderHeight;pos++)
		{
			for(int y =0; y<healthBar.getWidth();y++)
			{
				healthBar.setRGB(y,pos,Color.gray.getRGB());
			}
		}
		for(int pos = 0; pos<borderHeight;pos++)
		{
			for(int y =0; y<healthBar.getWidth();y++)
			{
				healthBar.setRGB(y,pos+height+borderHeight,Color.gray.getRGB());
			}
		}
	}

	public static boolean isTransparent(Color color)
	{
		boolean isTransparent = false;
		if (color.getRGB() == transparent.getRGB())
		{
			isTransparent = true;
		}
		return isTransparent;
	}

}
