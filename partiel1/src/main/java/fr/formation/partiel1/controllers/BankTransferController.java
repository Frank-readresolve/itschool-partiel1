package fr.formation.partiel1.controllers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import fr.formation.partiel1.entities.*;

/**
 * A Rest controller to deal with bank transfers.
 *
 * @author Frank MARSHALL
 */
@Path("bankTransfer")
@Produces(MediaType.APPLICATION_JSON)
public class BankTransferController {

    private static final List<BankTransfer> TRANSFERS = new ArrayList<>();
    static {
	buildBankTransfers();
    }

    /**
     * Returns a singleton list containing the last bank transfer based on its
     * request date.
     *
     * @return a singleton list containing the last bank transfer; never
     *         {@code null}, may be empty
     */
    @GET
    @Path("/last")
    public List<BankTransfer> last() {
	return Collections.singletonList(TRANSFERS.get(0));
    }

    /**
     * Returns a list of all the bank transfers.
     *
     * @return a list of all the bank transfers; never {@code null}, may be
     *         empty
     */
    @GET
    @Path("/all")
    public List<BankTransfer> all() {
	return Collections.unmodifiableList(TRANSFERS);
    }

    private static void buildBankTransfers() {
	BigDecimal firstAmount = BigDecimal.valueOf(1000.50);
	BigDecimal secondAmount = BigDecimal.valueOf(2500.80);
	LocalDateTime firstRequestDate = LocalDateTime.now();
	LocalDateTime secondRequestDate = firstRequestDate.minusDays(2L);
	LocalDateTime firstExecutionDate = firstRequestDate.plusDays(2L);
	LocalDateTime secondExecutionDate = secondRequestDate.plusDays(2L);
	Bban firstBban = new Bban("30002", "00550", "21345678936", "25");
	Bban secondBban = new Bban("30001", "00551", "11345678936", "45");
	Iban origin = new Iban("FR", "33", firstBban);
	Iban destination = new Iban("GB", "44", secondBban);
	BankTransfer firstTransfer = new BankTransfer(firstAmount,
		firstRequestDate, firstExecutionDate, origin, destination);
	TRANSFERS.add(firstTransfer);
	BankTransfer secondTransfer = new BankTransfer(secondAmount,
		secondRequestDate, secondExecutionDate, origin, destination);
	TRANSFERS.add(secondTransfer);
    }
}
