package fr.formation.partiel1.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test class to deal with {@code Iban} class.
 */
@SuppressWarnings("unused")
class IbanTest {

    private static final Bban FIRST_BBAN = new Bban("30002", "00550",
	    "21345678936", "25");

    private static final Bban SECOND_BBAN = new Bban("30001", "00551",
	    "11345678936", "45");

    @Test
    void shouldNotConstructWithNull() {
	assertThrows(NullPointerException.class, () -> {
	    new Iban(null, null, null);
	});
    }

    @Test
    void shouldConstruct() {
	assertDoesNotThrow(() -> {
	    new Iban("FR", "33", FIRST_BBAN);
	});
    }

    @Test
    void shouldReturnExpectedValues() {
	Iban first = new Iban("FR", "33", FIRST_BBAN);
	assertEquals("FR", first.getCountry());
	assertEquals("33", first.getKey());
	assertEquals(FIRST_BBAN, first.getBban());
	Iban second = new Iban("FR", "33", FIRST_BBAN);
	assertEquals(first.toString(), second.toString());
    }

    @Test
    void shouldBeEqual() {
	Iban first = new Iban("FR", "33", FIRST_BBAN);
	Iban second = new Iban("FR", "33", FIRST_BBAN);
	assertTrue(first.equals(second));
    }

    @Test
    void shouldNotBeEqual() {
	Iban first = new Iban("FR", "33", FIRST_BBAN);
	Iban second = new Iban("GB", "44", SECOND_BBAN);
	assertFalse(first.equals(second));
	assertFalse(first.equals(null));
    }

    @Test
    void shouldHashCodeBeConsistent() {
	Iban first = new Iban("FR", "33", FIRST_BBAN);
	Iban second = new Iban("FR", "33", FIRST_BBAN);
	assertEquals(first.hashCode(), second.hashCode());
    }
}
