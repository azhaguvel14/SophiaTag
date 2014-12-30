package edu.ntust.csie.se.mdfk.sophiatag.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import edu.ntust.csie.se.mdfk.sophiatag.data.Material;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class RecordStorage {
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private static final String FILE_PATH = getJarDir() + "/record.dat";
	
	private File file;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	RecordStorage() {
		file = new File(FILE_PATH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void saveRecord(NecessaryRecord record) {
		if (!this.hasSavedRecord()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
			outStream.writeObject(record);
			outStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @generated
	 * @ordered
	 */
	
	public NecessaryRecord loadRecord() throws IOException, ClassNotFoundException {
		if (!this.hasSavedRecord()) {
			return null;
		}
		
		NecessaryRecord record = null;
		
		ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
		record = (NecessaryRecord) inStream.readObject();
		inStream.close();
		
		return record;
	}
	
	public boolean hasSavedRecord() {
		return this.file.exists();
	}
	
	public static String getJarDir() {
		Path jarPath = Paths.get(RecordStorage.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		return jarPath.getParent().toFile().getPath();
	}
	
	public static class NecessaryRecord implements Serializable{
		private final String rootDirectory;
		private final MaterialList list;
		private final MaterialSearcher.TagDatabase tagDatabase;
		
		public NecessaryRecord(String rootDirectory, MaterialList list, MaterialSearcher.TagDatabase database) {
			this.rootDirectory = rootDirectory;
			this.list = list;
			this.tagDatabase = database;
			
		}

		public String getRootDirectory() {
			return rootDirectory;
		}

		public MaterialList getMaterialList() {
			return list;
		}

		public MaterialSearcher.TagDatabase getTagDatabase() {
			return tagDatabase;
		}
	}
	
	
}

