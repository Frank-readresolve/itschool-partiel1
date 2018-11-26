package fr.formation.partiel1.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class to deal with {@code Bban} class.
 */
@SuppressWarnings("unused")
class BbanTest {

    @Test
    void shouldNotConstructWithNull() {
	assertThrows(NullPointerException.class, () -> {
	    new Bban(null, null, null, null);
	});
    }

    @Test
    void shouldNotConstructWithBadAccountLength() {
	assertThrows(IllegalArgumentException.class, () -> {
	    new Bban("30001", "00550", "213456789361", "25");
	});
    }

    @Test
    void shouldConstruct() {
	assertDoesNotThrow(() -> {
	    new Bban("30001", "00550", "21345678936", "25");
	});
    }

    @Test
    void shouldReturnExpectedValues() {
	Bban first = new Bban("30002", "00550", "21345678936", "25");
	assertEquals("30002", first.getBank());
	assertEquals("00550", first.getCounter());
	assertEquals("21345678936", first.getAccount());
	assertEquals("25", first.getKey());
	Bban second = new Bban("30002", "00550", "21345678936", "25");
	assertEquals(first.toString(), second.toString());
    }

    @Test
    void shouldBeEqual() {
	Bban first = new Bban("30002", "00550", "21345678936", "25");
	Bban second = new Bban("30002", "00550", "21345678936", "25");
	assertTrue(first.equals(second));
    }

    @Test
    void shouldNotBeEqual() {
	Bban first = new Bban("30001", "00550", "21345678936", "25");
	Bban second = new Bban("30002", "00550", "21345678936", "25");
	assertFalse(first.equals(second));
	assertFalse(first.equals(null));
    }

    @Test
    void shouldHashCodeBeConsistent() {
	Bban first = new Bban("30002", "00550", "21345678936", "25");
	Bban second = new Bban("30002", "00550", "21345678936", "25");
	assertEquals(first.hashCode(), second.hashCode());
    }
}
