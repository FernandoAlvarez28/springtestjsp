package fernandoalvarez.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;

import com.thoughtworks.xstream.XStream;

public class DAO {
	private final String className = "fernandoalvarez.dao.DAO: ";
	public final String root = "C:\\FernandoAlvarez\\springjsp\\"; //maybe File is better
	public final String xmlFolder = "XMLdatabase";
	XStream stream;

	public DAO() {
		System.out.println(className + "constructor");
		this.stream = new XStream();
		this.stream.ignoreUnknownElements();
		this.stream.autodetectAnnotations(true);

		createFolder(xmlFolder);
	}

	public void init() {
		System.out.println(className + "init");
	}

	public void writeInXml(String xmlContent, File file) throws IOException, DocumentException {
		FileWriter fileWriter = new FileWriter(file);
		XMLWriter writer = new XMLWriter();
		writer.setWriter(fileWriter);
		writer.write(DocumentHelper.parseText(xmlContent));
		writer.flush();
		writer.close();
	}

	public void createFolder(String name) {
		String fullName;
		if (!name.contains(root)){
			fullName = root + name;			
		} else {
			fullName = name;
		}
		File folder = new File(fullName);
		System.out.println(className + "Attempt to create " + name + "folder at " + root);
		if (!verifyFolder(fullName)) {
			folder.mkdirs();
			System.out.println(className + "Folder created: " + folder.getAbsolutePath());
		}
	}

	public boolean verifyFolder(String name) {
		if (!name.contains(root)){
			name = root + name;			
		}
		File folder = new File(name);
		if (folder.exists() /*|| folder.list().length == 0*/) {
			System.out.println(className + "Folder do exists: " + folder.getAbsolutePath());
			return true;
		} else {
			System.out.println(className + "Folder DOES NOT exists: " + folder.getAbsolutePath());
			return false;
		}
	}

}
