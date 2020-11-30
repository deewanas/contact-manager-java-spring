package spring.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.app.dao.ContactDAO;
import spring.app.models.Contact;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
	
	//@Autowired
	private ContactDAO contactDAO;
	
	
	  @Autowired 
	  public ContactsController(ContactDAO contactDAO) {
		  this.contactDAO = contactDAO; 
	  }
	 
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("contacts", contactDAO.index());
		return "contacts/index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable ("id") int id, Model model) {
		model.addAttribute("contact", contactDAO.show(id));
		return "contacts/show";
	}
	
	@GetMapping("/new")
	public String newContact(Model model) {
		model.addAttribute("contact", new Contact());
		return "contacts/new";
	}

	@PostMapping
	public String create(@ModelAttribute("contact") @Valid Contact contact,
						 BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return "contacts/new";
		
		contactDAO.save(contact);
		return "redirect:/contacts";
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model model, @PathVariable("id") int id) {
	    model.addAttribute("contact", contactDAO.show(id));
	    return "contacts/edit";
	}
	
	@PatchMapping("/{id}")
    public String update(@ModelAttribute("contact") @Valid Contact person, BindingResult bindingResult,
    					 @PathVariable("id") int id) {
		
		if(bindingResult.hasErrors())
			return "contacts/edit";
		
		contactDAO.update(id, person);
        return "redirect:/contacts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
    	contactDAO.delete(id);
        return "redirect:/contacts";
    }
	
}
