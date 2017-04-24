package world.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.controller.GameController;
//My class
public class Rooms {

	private int[] left = { -1, 0 };
	private int[] up = { 0, -1 };
	private int[] right = { 1, 0 };
	private int[] down = { 0, 1 };
	private Dimension standered = new Dimension(7, 7);
	private Floor floor = new Floor(new Color(109, 109, 109));
	private GameController control;

	public Rooms(GameController control) {
		this.control = control;
	}

	private Tile wall() {
		Wall wall = new Wall(new Color(61, 61, 61));
		return wall;
	}

	private Tile floor() {

		Floor floor = new Floor(new Color(109, 109, 109));
		return floor;
	}

	private Tile leftDoor() {
		Exit leftDoor = new Exit(left, floor);
		return leftDoor;
	}

	private Tile upDoor() {
		Exit upDoor = new Exit(up, floor);
		return upDoor;
	}

	private Tile rightDoor() {
		Exit rightDoor = new Exit(right, floor);
		return rightDoor;
	}

	private Tile downDoor() {
		Exit downDoor = new Exit(down, floor);
		return downDoor;
	}
	
	
	
	
//startAbstraction
	public Room getRoomFromTemplate(String templateName) throws IOException {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		BufferedImage template = ImageIO.read(Rooms.class.getResource("templates/" + templateName));
		Tile currentTile = null;
		for (int x = 0; x < (int) standered.getWidth(); x++) {
			for (int y = 0; y < (int) standered.getWidth(); y++) {
				Color currentColor = new Color(template.getRGB(x, y));
				if (currentColor.getRGB() == floor().getColor().getRGB()) {
					currentTile = floor();
				} else if (currentColor.getRGB() == wall().getColor().getRGB()) {
					currentTile = wall();
				} else if (currentColor.getRGB() == Color.BLACK.getRGB()) {

					if (x == 6) {
						currentTile = rightDoor();
					} else if (x == 0) {
						currentTile = leftDoor();
					} else if (y == 6) {
						currentTile = downDoor();
					} else if (y == 0) {
						currentTile = upDoor();
					} else {
						currentTile = floor();
					}

				}
				tiles.add(currentTile);
			}
		}

		Room room = new Room(tiles, standered, control);
		return room;
	}
	//endAbstraction
}
