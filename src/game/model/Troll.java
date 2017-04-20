package game.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;
import world.model.MusicBox;
import world.model.Room;
import world.model.Tile;

public class Troll extends Monster
{
	private Random rand;
	private int count;
	private MusicBox growl;
	public Troll()
	{
		super(60, 60, 15, (int) (Math.random() * 100 + 1), 40, 20, 0, 20, 20, "Pictures/Troll.jpg", "Troll");
		count = 0;
		rand = new Random();
		growl = new MusicBox("Troll.wav",false);
	}
	@Override
	public Color getColor()
	{
		return Color.yellow;
	}
	
	@Override
	public Dimension move(Room room,Dimension Position)
	{
		if(count == 2){
			count = 0;
		ArrayList<Dimension> validPositions = new ArrayList<Dimension>();
		if(checkDimension(new Dimension(Position.width+1,Position.height),room))
		{
			validPositions.add(new Dimension(Position.width+1,Position.height));
		}
		if(checkDimension(new Dimension(Position.width-1,Position.height),room))
		{
			validPositions.add(new Dimension(Position.width-1,Position.height));
		}
		if(checkDimension(new Dimension(Position.width,Position.height+1),room))
		{
			validPositions.add(new Dimension(Position.width,Position.height+1));
		}
		if(checkDimension(new Dimension(Position.width,Position.height-1),room))
		{
			validPositions.add(new Dimension(Position.width,Position.height-1));
		}
		
		if(validPositions.size()!=0)
		{
			Dimension moveTo = validPositions.get(rand.nextInt(validPositions.size()));
			growl.startThread();
			return moveTo;
		}
		else
		{
			return null;
		}}
		else
		{
			count ++;
			return null;
		}
		
		
		
	}
	private boolean checkDimension(Dimension pos,Room room)
	{
		Tile tile = room.getTile(pos);
		return tile!=null&&tile.canCross()&&!tile.gethasMonster()&&!tile.isInhabited()&&!tile.getIsExit();
	}
}
