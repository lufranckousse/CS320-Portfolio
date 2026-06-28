import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @Test
    public void testAddContactWithUniqueId() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");

        service.addContact(contact);

        assertEquals(contact, service.getContact("12345"));
    }

    @Test
    public void testAddContactRejectsDuplicateId() {
        ContactService service = new ContactService();
        Contact contactOne = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");
        Contact contactTwo = new Contact("12345", "Mike", "Jones", "0987654321", "456 Oak St");

        service.addContact(contactOne);

        assertThrows(IllegalArgumentException.class, () -> service.addContact(contactTwo));
    }

    @Test
    public void testAddContactRejectsNullContact() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.addContact(null));
    }

    @Test
    public void testDeleteContactById() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");

        service.addContact(contact);
        service.deleteContact("12345");

        assertNull(service.getContact("12345"));
    }

    @Test
    public void testDeleteContactRejectsMissingId() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("99999"));
    }

    @Test
    public void testUpdateFirstNameByContactId() {
        ContactService service = createServiceWithContact();
        service.updateFirstName("12345", "Mike");
        assertEquals("Mike", service.getContact("12345").getFirstName());
    }

    @Test
    public void testUpdateLastNameByContactId() {
        ContactService service = createServiceWithContact();
        service.updateLastName("12345", "Jones");
        assertEquals("Jones", service.getContact("12345").getLastName());
    }

    @Test
    public void testUpdatePhoneByContactId() {
        ContactService service = createServiceWithContact();
        service.updatePhone("12345", "0987654321");
        assertEquals("0987654321", service.getContact("12345").getPhone());
    }

    @Test
    public void testUpdateAddressByContactId() {
        ContactService service = createServiceWithContact();
        service.updateAddress("12345", "456 Oak St");
        assertEquals("456 Oak St", service.getContact("12345").getAddress());
    }

    @Test
    public void testUpdateRejectsInvalidValues() {
        ContactService service = createServiceWithContact();

        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("12345", null));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("12345", "LongLastNam"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("12345", "123"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("12345", "1234567890123456789012345678901"));
    }

    @Test
    public void testUpdateRejectsMissingContactId() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("99999", "Mike"));
        assertThrows(IllegalArgumentException.class, () -> service.updateLastName("99999", "Jones"));
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("99999", "0987654321"));
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("99999", "456 Oak St"));
    }

    private ContactService createServiceWithContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");
        service.addContact(contact);
        return service;
    }
}
