package au.com.reece.controller;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import au.com.reece.service.AddressBookService;
import au.com.reece.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addressBook")
public class AddressBookController {

    private Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    ContactService contactService;

    @GetMapping(value = "/createAddressBook")
    public AddressBook createAddressBook() {
        logger.info("Creating Address Book");
        return addressBookService.createAddressBook();
    }

    @PostMapping("/addContact/{addressBookId}")
    public AddressBook addContactToAddressBook(@PathVariable Integer addressBookId, @RequestBody @Valid Contact contact) throws EntityNotFoundException {
        logger.info("Adding contact {} to Address Book {}",contact,addressBookId);
        addressBookService.insertContactToAddressBook(addressBookId, contact);
        return addressBookService.getAddressBookById(addressBookId);
    }

    @DeleteMapping("/removeContact/{addressBookId}")
    public void removeContactFromAddressBook(@PathVariable Integer addressBookId, @RequestBody @Valid Contact contact) throws EntityNotFoundException {
        logger.info("Removing contact {} to Address Book {}",contact,addressBookId);
        addressBookService.removeContactFromAddressBook(addressBookId, contact);
    }

    @GetMapping(value = "/getContacts/{addressBookId}")
    public List<Contact> getContactsFromAddressBook(@PathVariable Integer addressBookId) throws EntityNotFoundException {
        logger.info("Getting all contacts from Address Book {}",addressBookId);
        return contactService.getContactsFromBookById(addressBookId);
    }
}
