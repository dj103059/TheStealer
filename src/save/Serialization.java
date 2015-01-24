package save;
import java.io.*;

import main.Simulator;

public class Serialization {

	public static void saveSimulator(Simulator sim)
	{
		try{
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(
						new File("./save/save.txt"))));
			oos.writeObject(sim);
			oos.close();	
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static Simulator loadSimulator() {
		Simulator sim = new Simulator(32,32);
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File ("./save/save.txt"))));
			try {
				sim=(Simulator)ois.readObject();
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace ();
			}		
			ois.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace ();
			System.out.println("Erreur lors du chargement");
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur lors du chargement");
		}
		return sim;
	}
}
