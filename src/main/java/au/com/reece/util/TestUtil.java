package au.com.reece.util;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import au.com.reece.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestUtil {

    private static AddressBookService addressBookService;

    @Autowired
    public TestUtil(AddressBookService addressBookService) {
        TestUtil.addressBookService = addressBookService;
    }

    public static AddressBook createAddressBookWithContacts() throws EntityNotFoundException {
        AddressBook addressBook = addressBookService.createAddressBook();
        Contact contact1 = Contact.builder().name("test 1").phoneNumber("0490719486").build();
        Contact contact2 = Contact.builder().name("test 2").phoneNumber("0490719487").build();
        Contact contact3 = Contact.builder().name("test 3").phoneNumber("0490719488").build();
        addressBookService.insertContactToAddressBook(addressBook.getId(), contact1);
        addressBookService.insertContactToAddressBook(addressBook.getId(), contact2);
        addressBookService.insertContactToAddressBook(addressBook.getId(), contact3);
        return addressBookService.getAddressBookById(1);
    }

}
