package world.model;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

import game.controller.GameController;

public class Map implements java.io.Serializable
{
	private transient Rooms rooms;
	private Room currentRoom;
	private Dimension currentPos;
	private Dimension size;
	private ArrayList<Room> areas;
	private transient GameController control;

	public Map(GameController control)
	{
		rooms = new Rooms(control);
		size = new Dimension(8, 5);
		areas = new ArrayList<Room>();
		currentPos = new Dimension(5, 3);
		buildAreas();
		currentRoom = this.getRoom(currentPos);
	}

	private void buildAreas()
	{

		try
		{
			// row1
			areas.add(rooms.getRoomFromTemplate("DeadEnd90.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd90.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor90.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn270.png"));
			//row2
			areas.add(rooms.getRoomFromTemplate("threeDoor180.png"));
			areas.add(rooms.getRoomFromTemplate("fourDoor.png"));
			areas.add(rooms.getRoomFromTemplate("fourDoor.png"));
			areas.add(rooms.getRoomFromTemplate("fourDoor.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor.png"));
			//row
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd270.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor180.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor270.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor.png"));
			//row4
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor270.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn270.png"));
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			//row5
			areas.add(rooms.getRoomFromTemplate("threeDoor180.png"));
			areas.add(rooms.getRoomFromTemplate("fourDoor.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd.png"));
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			//row6
			areas.add(rooms.getRoomFromTemplate("threeDoor180.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd180.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn180.png"));
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			//row7
			areas.add(rooms.getRoomFromTemplate("straight90.png"));
			areas.add(rooms.getRoomFromTemplate("RightTurn90.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor90.png"));
			areas.add(rooms.getRoomFromTemplate("straight.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor.png"));
			//row8
			areas.add(rooms.getRoomFromTemplate("DeadEnd270.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd180.png"));
			areas.add(rooms.getRoomFromTemplate("threeDoor270.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd.png"));
			areas.add(rooms.getRoomFromTemplate("DeadEnd270.png"));
			
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public void setControl(GameController controller)
	{
		this.control = controller;
		for(Room room : areas)
		{
			room.setControlller(controller);
		}
	}
	private Room getRoom(Dimension currentPos)
	{
		int index = (int) ((currentPos.getWidth() - 1) * (size.getHeight()) + (currentPos.getHeight() - 1));
		return areas.get(index);
	}

	public void setCurrentPos(Dimension newPosition)
	{
		currentPos = newPosition;
		this.currentRoom = this.getRoom(newPosition);
		currentRoom.resetRest();
	}
	public Room getRoomFromDimensinon(Dimension dimension)
	{
		return this.getRoom(new Dimension(((int)dimension.getWidth()+1),((int)dimension.getHeight()+1)));
	}
	{
		
	}
	public Dimension getCurrentPos()
	{
		return currentPos;
	}

	public Room getCurrentRoom()
	{
		return currentRoom;
	}
	public Dimension getSize(){
		return size;
	}
}
