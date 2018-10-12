package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;

import java.util.List;

public interface AddressBookService {

    AddressBook createAddressBook();
    AddressBook getAddressBookById(Integer id) throws EntityNotFoundException;
    void insertContactToAddressBook(Integer addressBookId, Contact contact) throws EntityNotFoundException;
    void removeContactFromAddressBook(Integer addressBookId, Contact contact) throws EntityNotFoundException;
    void removeAllAddressBooks();
    List<AddressBook> getAllAddressBooks();

}
