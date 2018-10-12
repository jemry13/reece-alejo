package au.com.reece.controller;

import au.com.reece.model.Contact;
import au.com.reece.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/getAllGlobalUniqueContacts")
    public List<Contact> getAllGlobalContacts(){
        return contactService.getAllUniqueContacts();
    }
}
