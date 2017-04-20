package game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import world.model.Map;

public class FileHandler
{

	private File mainDirectory;

	public FileHandler()
	{
		try
		{
			String parentPath = getParentDirectory();
			String Path = parentPath +"/"+"GameData"+"/";
			if(Files.exists(Paths.get(Path)))
			{
				mainDirectory = new File(Path);
			}
			else
			{
				mainDirectory = new File(Path);
				mainDirectory.mkdir();
			}
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
	}

	public FileHandler(String path)
	{
		String parentPath = path;
		String Path = parentPath +"/"+"GameData"+"/";
		if(Files.exists(Paths.get(Path)))
		{
			mainDirectory = new File(Path);
		}
		else
		{
			mainDirectory = new File(Path);
			mainDirectory.mkdir();
		}
	}
	
	public String getDirectory()
	{
		try
		{
			return getParentDirectory();
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	private String getParentDirectory() throws UnsupportedEncodingException
	{
		String parentPath = "";
		URL childPath = FileHandler.class.getProtectionDomain().getCodeSource().getLocation();
		String childFilePath = URLDecoder.decode(childPath.getFile(), "UTF-8");
		parentPath = new File(childFilePath).getParentFile().getPath();
		return parentPath;
	}
	public void writeData(String fileName,String stringData)
	{
		byte data[] = stringData.getBytes();
		Path path = Paths.get(mainDirectory.getPath()+"/"+fileName+".txt");
		try
		{
			Files.write(path, data, StandardOpenOption.CREATE);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public String readData(String fileName)
	{
		String Data = "";
		Path path = Paths.get(mainDirectory.getPath()+"/"+fileName+".txt");
		try
		{
			Data = new String(Files.readAllBytes(path),"UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			Data = null;
		}
		return Data;
	}
	public void writeMap(Map map)
	{
		try
		{
			FileOutputStream mapOut = new FileOutputStream(mainDirectory.getPath()+"/"+"Map.map");
			ObjectOutputStream out = new ObjectOutputStream(mapOut);
			out.writeObject(map);
			out.close();
			mapOut.close();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Map readMap()
	{
		Map map = null;
		try
		{
			FileInputStream mapIn = new FileInputStream(mainDirectory.getPath()+"/"+"Map.map");
			ObjectInputStream in = new ObjectInputStream(mapIn);
			map = (Map) in.readObject();
			in.close();
			mapIn.close();
		} catch (FileNotFoundException e)
		{
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return map;
	}

}
