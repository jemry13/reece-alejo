package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import au.com.reece.service.AddressBookService;
import au.com.reece.util.TestUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookServiceTest {

    @Autowired
    AddressBookService addressBookService;

    @After
    public void finalize(){
        addressBookService.removeAllAddressBooks();
    }

    @Test
    public void createAddressBook() throws EntityNotFoundException {
        addressBookService.createAddressBook();
        AddressBook addressBook = addressBookService.getAddressBookById(1);
        assertNotNull(addressBook);
        assertThat(addressBook.getContacts(), hasSize(0));
    }

    @Test(expected = EntityNotFoundException.class)
    public void noAddressBookFoundThrwoException() throws EntityNotFoundException {
        addressBookService.getAddressBookById(1);
    }

    //Address book will hold name and phone numbers of contact entries
    @Test
    public void addressBookHoldContactData() throws EntityNotFoundException {
        AddressBook addressBook = TestUtil.createAddressBookWithContacts();
        assertThat(addressBook.getContacts(), hasSize(3));
        assertThat(addressBook.getContacts(), containsInAnyOrder(
                Contact.builder().name("test 1").phoneNumber("0490719486").build(),
                Contact.builder().name("test 2").phoneNumber("0490719487").build(),
                Contact.builder().name("test 3").phoneNumber("0490719488").build()
        ));
    }

    //Users should be able to add new contact entries
    @Test
    public void addNewContactToAddressBook() {
        AddressBook addressBook = addressBookService.createAddressBook();
        addressBook.getContacts().add(Contact.builder().name("test 1").phoneNumber("0490719486").build());
        assertThat(addressBook.getContacts(), contains(
                Contact.builder().name("test 1").phoneNumber("0490719486").build()
        ));
    }

    //Users should be able to remove existing contact entries
    @Test
    public void removeExistingContact() throws EntityNotFoundException {
        AddressBook addressBook = TestUtil.createAddressBookWithContacts();
        Contact contactToRemove = Contact.builder().name("test 1").phoneNumber("0490719486").build();
        addressBookService.removeContactFromAddressBook(1, contactToRemove);
        assertThat(addressBook.getContacts(), hasSize(2));
        List<Contact> shouldNotBeInList = Arrays.asList(contactToRemove);
        assertThat(addressBook.getContacts(), everyItem(not(isIn(shouldNotBeInList))));
    }
}
