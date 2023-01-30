package com.zoho.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zoho.entities.Contacts;
import com.zoho.entities.Lead;
import com.zoho.services.ContactServices;
import com.zoho.services.LeadServices;

@Controller
public class LeadController {
	@Autowired
	private LeadServices leadServices;
	@Autowired
	private ContactServices contactservice;
	@RequestMapping(value="/create",method=RequestMethod.GET)
public String viewCreateLeadForm() {
		return"create_lead";
}
	@RequestMapping(value="/save",method =RequestMethod.POST )
	public String saveOneLead(@ModelAttribute("lead")Lead lead,Model model) {
		leadServices.saveLeadInformation(lead);
model.addAttribute("lead",lead);
model.addAttribute("msg","Lead is saved!!");
return"lead_info";
	}

	@RequestMapping("/listleads")
	public String listLeads(Model model) {
	List<Lead> leads = leadServices.getAllleads();
	model.addAttribute("leads", leads);

		return"list_leads";
}
	@RequestMapping("/LeadInfo")
	public String leadInfo(@RequestParam("id")long id,Model model) {
		Lead lead = leadServices.findLeadById(id);
		model.addAttribute("lead",lead);
		return"lead_info";
}
	@RequestMapping("/convertLead")
	public String convertLead(@RequestParam("id") long id,Model model){
	    Lead lead=leadServices.findLeadById(id);	
	    Contacts contact=new Contacts();
	    contact.setFirstName(lead.getFirstName());
	    contact.setLastName(lead.getLastName());
	    contact.setEmail(lead.getEmail());
	    contact.setMobile(lead.getMobile());
	    contact.setSource(lead.getSource());
	    contactservice.saveContactInformation(contact);
	    leadServices.deleteOneLead(id);
	    List<Contacts>contacts=contactservice.getAllContacts();
	    model.addAttribute("contacts",contacts);
	    return "list_contacts";
	}
	}
	
