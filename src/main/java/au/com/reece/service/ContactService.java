package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllUniqueContacts();
    List<Contact> getContactsFromBookById(Integer addressBookId) throws EntityNotFoundException;
}
