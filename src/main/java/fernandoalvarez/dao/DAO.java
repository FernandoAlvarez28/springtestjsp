package fernandoalvarez.dao;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import fernandoalvarez.dto.Thing;

@Repository
public class DAO {
	private final String className = "fernandoalvarez.dao.DAO: ";
	public final String root = "C:\\FernandoAlvarez\\springjsp\\"; // maybe File
																	// is better
	private Session session;
	private SessionFactory sessionFactory;

	public DAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}

	public void init() {

	}

	public void toSQL(Thing thing) {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(thing);
		session.getTransaction().commit();
	}

	public List<Thing> fromSQL() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Thing> things = session.createCriteria(Thing.class).list();
		session.close();
		return things;
	}

	/*
	 * public void toGson(List<Thing> things) { Gson gson = new
	 * GsonBuilder().create(); List<String> strings = new ArrayList<>(); try
	 * (Writer writer = new FileWriter(root + "Output.json")) {
	 * gson.toJson(things, writer); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * public List<Thing> fromGson() throws FileNotFoundException { if (new
	 * File(root + "Output.json").exists()) { Gson gson = new Gson(); Type
	 * collectionType = new TypeToken<List<Thing>>() { }.getType();
	 * BufferedReader br = new BufferedReader(new FileReader(root +
	 * "Output.json")); return gson.fromJson(br, collectionType); } else {
	 * return new ArrayList<>(); } }
	 */

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
