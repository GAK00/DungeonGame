package game.model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

import world.model.MusicBox;
import world.model.Room;
import world.model.Tile;

public class Goblin extends Monster {
	private Dimension playerPos;
	private Random rand;
	private int count;
	private MusicBox sound;

	public Goblin() {
		super(40, 40, 10, (int) (Math.random() * 100 + 1), 20, 40, 40, 70, 10, "Picture/Goblin.jpg", "Goblin");
		sound = new MusicBox("goblin.wav", false, -15.0f);
		count = 0;
		rand = new Random();
	}

	@Override
	public Color getColor() {
		return Color.blue;
	}

	@Override
	public Dimension move(Room room, Dimension Position) {

		if (count == 1) {
			count = 0;
			playerPos = room.getOccupyiedTile();

			ArrayList<Dimension> validPositions = new ArrayList<Dimension>();
			if (checkDimension(new Dimension(Position.width + 1, Position.height), room, Position)) {
				validPositions.add(new Dimension(Position.width + 1, Position.height));
			}
			if (checkDimension(new Dimension(Position.width - 1, Position.height), room, Position)) {
				validPositions.add(new Dimension(Position.width - 1, Position.height));
			}
			if (checkDimension(new Dimension(Position.width, Position.height + 1), room, Position)) {
				validPositions.add(new Dimension(Position.width, Position.height + 1));
			}
			if (checkDimension(new Dimension(Position.width, Position.height - 1), room, Position)) {
				validPositions.add(new Dimension(Position.width, Position.height - 1));
			}

			if (validPositions.size() != 0) {
				Dimension moveTo = validPositions.get(rand.nextInt(validPositions.size()));
				sound.startThread();
				return moveTo;
			} else {
				return null;
			}

		} else {
			count++;
			return null;
		}

	}

	private boolean checkDimension(Dimension pos, Room room, Dimension ogPos) {
		Tile tile = room.getTile(pos);
		return tile != null && tile.canCross() && !tile.gethasMonster() && !tile.isInhabited() && !tile.getIsExit()
				&& (Math.abs((int) (playerPos.getWidth() - pos.getWidth()))
						+ Math.abs((int) (playerPos.getHeight() - pos.getHeight())) < (Math
								.abs((int) (playerPos.getWidth() - ogPos.getWidth())) + Math
										.abs((int) (playerPos.getHeight() - ogPos.getHeight()))));
	}

	private boolean checkDimensionEasier(Dimension pos, Room room, Dimension ogPos) {
		Tile tile = room.getTile(pos);
		return tile != null && tile.canCross() && !tile.gethasMonster() && !tile.isInhabited() && !tile.getIsExit()
				&& (Math.abs((int) (playerPos.getWidth() - pos.getWidth()))
						+ Math.abs((int) (playerPos.getHeight() - pos.getHeight())) <= (Math
								.abs((int) (playerPos.getWidth() - ogPos.getWidth())) + Math
										.abs((int) (playerPos.getHeight() - ogPos.getHeight()))));
	}

}
