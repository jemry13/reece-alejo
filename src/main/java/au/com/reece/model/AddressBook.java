package au.com.reece.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddressBook {

    private Integer id;
    private List<Contact> contacts;
}
