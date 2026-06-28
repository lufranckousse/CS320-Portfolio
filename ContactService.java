import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Contact ID already exists");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        contacts.remove(contactId);
    }

    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getExistingContact(contactId);
        contact.setFirstName(firstName);
    }

    public void updateLastName(String contactId, String lastName) {
        Contact contact = getExistingContact(contactId);
        contact.setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        Contact contact = getExistingContact(contactId);
        contact.setPhone(phone);
    }

    public void updateAddress(String contactId, String address) {
        Contact contact = getExistingContact(contactId);
        contact.setAddress(address);
    }

    private Contact getExistingContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID does not exist");
        }
        return contact;
    }
}
