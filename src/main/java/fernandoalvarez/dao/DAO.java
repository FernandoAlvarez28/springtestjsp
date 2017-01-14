package fernandoalvarez.dao;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;

import fernandoalvarez.dto.Thing;

public class DAO {
	private final String className = "fernandoalvarez.dao.DAO: ";
	public final String root = "C:\\FernandoAlvarez\\springjsp\\"; // maybe File
																	// is better
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

	}

	public void writeInXml(String xmlContent, File file) throws IOException, DocumentException {
		FileWriter fileWriter = new FileWriter(file);
		XMLWriter writer = new XMLWriter();
		writer.setWriter(fileWriter);
		writer.write(DocumentHelper.parseText(xmlContent));
		writer.flush();
		writer.close();
	}

	public void toGson(List<Thing> things) {
		Gson gson = new GsonBuilder().create();
		List<String> strings = new ArrayList<>();
		try (Writer writer = new FileWriter(root + "Output.json")) {
			gson.toJson(things, writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Thing> fromGson() throws FileNotFoundException {
		if (new File(root + "Output.json").exists()) {
			Gson gson = new Gson();
			Type collectionType = new TypeToken<List<Thing>>() {
			}.getType();
			BufferedReader br = new BufferedReader(new FileReader(root + "Output.json"));
			return gson.fromJson(br, collectionType);
		} else {
			return new ArrayList<>();
		}
	}

	public void createFolder(String name) {
		String fullName;
		if (!name.contains(root)) {
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
		if (!name.contains(root)) {
			name = root + name;
		}
		File folder = new File(name);
		if (folder.exists() /* || folder.list().length == 0 */) {
			System.out.println(className + "Folder do exists: " + folder.getAbsolutePath());
			return true;
		} else {
			System.out.println(className + "Folder DOES NOT exists: " + folder.getAbsolutePath());
			return false;
		}
	}

}
