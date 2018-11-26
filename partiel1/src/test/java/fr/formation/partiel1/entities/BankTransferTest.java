package fr.formation.partiel1.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Test class to deal with {@code BankTransfer} class.
 */
@SuppressWarnings("unused")
class BankTransferTest {

    private static final LocalDateTime NOW = LocalDateTime.now();

    private static final LocalDateTime PLUS_24 = NOW.plusHours(24L);

    private static final Bban FIRST_BBAN = new Bban("30002", "00550",
	    "21345678936", "25");

    private static final Bban SECOND_BBAN = new Bban("30001", "00551",
	    "11345678936", "45");

    private static final BigDecimal POS_AMOUNT = BigDecimal.valueOf(100.0);

    private static final Iban FIRST_IBAN = new Iban("FR", "33", FIRST_BBAN);

    private static final Iban SECOND_IBAN = new Iban("FR", "33", SECOND_BBAN);

    @Test
    void shouldNotConstructWithNull() {
	assertThrows(NullPointerException.class, () -> {
	    new BankTransfer(null, null, NOW, null, null);
	});
    }

    @Test
    void shouldNotConstructWithAmountNotPositive() {
	assertThrows(IllegalArgumentException.class, () -> {
	    new BankTransfer(BigDecimal.ZERO, NOW, null, FIRST_IBAN,
		    SECOND_IBAN);
	});
    }

    @Test
    void shouldNotConstructWithSameDates() {
	assertThrows(IllegalArgumentException.class, () -> {
	    new BankTransfer(POS_AMOUNT, NOW, NOW, FIRST_IBAN, SECOND_IBAN);
	});
    }

    @Test
    void shouldNotConstructWithSameAccounts() {
	assertThrows(IllegalArgumentException.class, () -> {
	    new BankTransfer(POS_AMOUNT, NOW, PLUS_24, FIRST_IBAN, FIRST_IBAN);
	});
    }

    @Test
    void shouldConstructWithNullExecutionDate() {
	assertDoesNotThrow(() -> {
	    new BankTransfer(POS_AMOUNT, NOW, null, FIRST_IBAN, SECOND_IBAN);
	});
    }

    @Test
    void shouldReturnExpectedValues() {
	BankTransfer first = new BankTransfer(POS_AMOUNT, NOW, PLUS_24,
		FIRST_IBAN, SECOND_IBAN);
	assertEquals(POS_AMOUNT, first.getAmount());
	assertEquals(NOW, first.getRequestDate());
	assertEquals(PLUS_24, first.getExecutionDate());
	assertEquals(FIRST_IBAN, first.getOrigin());
	assertEquals(SECOND_IBAN, first.getDestination());
	BankTransfer second = new BankTransfer(POS_AMOUNT, NOW, PLUS_24,
		FIRST_IBAN, SECOND_IBAN);
	assertEquals(first.toString(), second.toString());
    }
}
