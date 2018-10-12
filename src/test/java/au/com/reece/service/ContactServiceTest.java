package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import au.com.reece.service.AddressBookService;
import au.com.reece.service.ContactService;
import au.com.reece.util.TestUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceTest {

    @Autowired
    ContactService contactService;

    @Autowired
    AddressBookService addressBookService;

    @After
    public void finalize(){
        addressBookService.removeAllAddressBooks();
    }

    //Users should be able to maintain multiple address books
    //Users should be able to print a unique set of all contacts across multiple address books
    @Test
    public void getUniqueContactsOfMultipleAddressBooks() throws EntityNotFoundException {
        TestUtil.createAddressBookWithContacts(); //Create first Address book
        TestUtil.createAddressBookWithContacts(); //Create second Address book with same items

        List<Contact> contacts= contactService.getAllUniqueContacts().stream().filter(contact -> contact.getName().equals("test 1")).collect(Collectors.toList());
        assertThat(contacts, hasSize(1));//Assert that there is just one element {name: "test 1", phoneNumber: "0490719486"}
    }

    //Users should be able to print all contacts in an address book
    @Test
    public void getContactsGivenAddressBookId() throws EntityNotFoundException {
        AddressBook addressBookOne = TestUtil.createAddressBookWithContacts();
        AddressBook addressBookTwo =TestUtil.createAddressBookWithContacts();

        List<Contact> contactsAddressBookOne = contactService.getContactsFromBookById(addressBookOne.getId());
        assertThat(contactsAddressBookOne, hasSize(3));

        List<Contact> contactsAddressBookTwo = contactService.getContactsFromBookById(addressBookTwo.getId());
        assertThat(contactsAddressBookTwo, hasSize(3));
    }
}
