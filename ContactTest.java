import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testContactCreatedSuccessfully() {
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    public void testContactIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Smith", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testContactIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Smith", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testFirstNameCannotBeNullOrLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", null, "Smith", "1234567890", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "Johnathan11", "Smith", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testLastNameCannotBeNullOrLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", null, "1234567890", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smithfield1", "1234567890", "123 Main St");
        });
    }

    @Test
    public void testPhoneCannotBeNullAndMustBeExactlyTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", null, "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "123456789", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "12345678901", "123 Main St");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "12345abcde", "123 Main St");
        });
    }

    @Test
    public void testAddressCannotBeNullOrLongerThanThirtyCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "1234567890", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345", "John", "Smith", "1234567890", "1234567890123456789012345678901");
        });
    }

    @Test
    public void testSettersValidateFields() {
        Contact contact = new Contact("12345", "John", "Smith", "1234567890", "123 Main St");

        contact.setFirstName("Mike");
        contact.setLastName("Jones");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak St");

        assertEquals("Mike", contact.getFirstName());
        assertEquals("Jones", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak St", contact.getAddress());

        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("LongLastNam"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("1234567890123456789012345678901"));
    }
}
