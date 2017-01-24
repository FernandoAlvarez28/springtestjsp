package fernandoalvarez.control;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fernandoalvarez.dao.DAO;
import fernandoalvarez.dto.Thing;

@Controller
public class ThingController {
	private List<Thing> things;
	// int id = 0;
	private DAO dao;

	public ThingController() {
		dao = new DAO();

		things = dao.fromSQL();

	}

	@RequestMapping(value = "/")
	public ModelAndView thing() {
		return new ModelAndView("thing", "command", new Thing());
	}

	@RequestMapping(value = "/addThing")
	public String addThing(Model model, String name, String number) {
		Thing thing = new Thing();
		// thing.setId(id);
		thing.setName(name);
		thing.setRandomNumber(Integer.parseInt(number));
		things.add(thing);
		// id = id + 1;
		return "thing";
	}

	@RequestMapping(value = "/viewThings")
	public String viewThings(Model model) {
		model.addAttribute("things", things);
		return "result";
	}

	@RequestMapping(value = "/save")
	public String toGson(Model model) {
		for (Thing thing : things) {
			dao.toSQL(thing);
		}
		// model.addAttribute("strings", dao.toGson(things));
		return "thing";
	}
}