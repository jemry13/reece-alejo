package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    AddressBookService addressBookService;

    @Override
    public List<Contact> getAllUniqueContacts() {
        return addressBookService.getAllAddressBooks()
                .stream()
                .map(AddressBook::getContacts)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactsFromBookById(Integer addressBookId) throws EntityNotFoundException {
        AddressBook addressBook = addressBookService.getAddressBookById(addressBookId);
        return addressBook.getContacts();
    }
}
