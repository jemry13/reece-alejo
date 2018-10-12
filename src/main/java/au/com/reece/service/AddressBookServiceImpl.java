package au.com.reece.service;

import au.com.reece.exception.EntityNotFoundException;
import au.com.reece.model.AddressBook;
import au.com.reece.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService{

    private List<AddressBook>  addressBooks = new ArrayList<>();

    @Override
    public AddressBook createAddressBook() {
        AddressBook addressBook = AddressBook.builder()
                .id(addressBooks.size()+1)
                .contacts(new ArrayList<>())
                .build();
        addAddressBookToList(addressBook);
        return addressBook;
    }

    @Override
    public AddressBook getAddressBookById(Integer id) throws EntityNotFoundException {
        AddressBook addressBookReturned = addressBooks.stream().filter(addressBook -> addressBook.getId().equals(id)).findFirst().orElse(null);
        if(addressBookReturned == null){
            throw new EntityNotFoundException(AddressBook.class, "id", id.toString());
        }
        return addressBookReturned;
    }

    @Override
    public void insertContactToAddressBook(Integer addressBookId, Contact contact) throws EntityNotFoundException {
        AddressBook addressBook = getAddressBookById(addressBookId);
        addressBook.getContacts().add(contact);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void removeContactFromAddressBook(Integer addressBookId, Contact contact) throws EntityNotFoundException {
        AddressBook addressBook = getAddressBookById(addressBookId);
        if(!addressBook.getContacts().contains(contact)){
            throw new EntityNotFoundException(Contact.class, "id", contact.toString());
        }
        addressBook.getContacts().remove(contact);
    }

    @Override
    public void removeAllAddressBooks() {
        addressBooks.clear();
    }

    @Override
    public List<AddressBook> getAllAddressBooks() {
        return addressBooks;
    }

    private void addAddressBookToList(AddressBook addressBook) {
        addressBooks.add(addressBook);
    }
}
