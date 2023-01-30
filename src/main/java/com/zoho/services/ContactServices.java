package com.zoho.services;

import java.util.List;

import com.zoho.entities.Contacts;

public interface ContactServices {
	public void saveContactInformation(Contacts contacts);

	public List<Contacts> getAllContacts();

	public Contacts findContactsById(long id);

}
