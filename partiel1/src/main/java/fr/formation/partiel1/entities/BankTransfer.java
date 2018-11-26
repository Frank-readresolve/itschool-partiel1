package fr.formation.partiel1.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Represents a bank transfer. A bank transfer is the transfer of an amount of
 * money from one account to another; specifying request and execution dates.
 * <p>
 * Class invariants:
 * <ul>
 * <li>All the properties are guaranteed not {@code null} but the execution date
 * <li>If the execution date is not {@code null}, it's guaranteed to be at least
 * 24 hours after the request date
 * <li>The amount is guaranteed to be positive
 * <li>The accounts (IBANs) are guaranteed not equal
 * </ul>
 *
 * @author Frank MARSHALL
 */
public class BankTransfer {

    private BigDecimal amount;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime requestDate;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime executionDate;

    private Iban origin;

    private Iban destination;

    /**
     * Creates a new {@code BankTransfer} with given amount, request and
     * execution dates; and origin and destination accounts (IBANs).
     *
     * @param amount
     *            an amount
     * @param requestDate
     *            a request date
     * @param executionDate
     *            an execution date
     * @param origin
     *            an origin account
     * @param destination
     *            a destination account
     * @throws NullPointerException
     *             if any of the argument is {@code null}, optional for the
     *             execution date
     * @throws IllegalArgumentException
     *             if {@code executionDate} is not {@code null} and not at least
     *             24h after {@code requestDate}
     * @throws IllegalArgumentException
     *             if {@code amount} is not positive
     * @throws IllegalArgumentException
     *             if {@code origin} and {@code destination} are equal
     * @see Iban#equals(Object)
     */
    public BankTransfer(BigDecimal amount, LocalDateTime requestDate,
	    LocalDateTime executionDate, Iban origin, Iban destination) {
	checkDates(requestDate, executionDate);
	checkAccounts(origin, destination);
	setAmount(amount);
	setRequestDate(requestDate);
	setExecutionDate(executionDate);
	setOrigin(origin);
	setDestination(destination);
    }

    private static void checkDates(LocalDateTime request,
	    LocalDateTime execution) {
	if (execution != null) {
	    // Null pointer exception if request is null:
	    LocalDateTime floor = request.plusHours(24L);
	    if (execution.isBefore(floor)) {
		throw new IllegalArgumentException(
			"execution date must be at least 24h after request date");
	    }
	} else { // Check only request
	    Objects.requireNonNull(request);
	}
    }

    private static void checkAccounts(Iban origin, Iban destination) {
	Objects.requireNonNull(destination,
		"destination account must not be null");
	// Null pointer exception if origin is null:
	if (origin.equals(destination)) {
	    throw new IllegalArgumentException("accounts must not be equal");
	}
    }

    /**
     * Returns the amount for this {@code transfer}.
     *
     * @return the amount for this {@code transfer}; nerver {@code null}
     */
    public BigDecimal getAmount() {
	return amount;
    }

    private void setAmount(BigDecimal amount) {
	// Null pointer exception if amount is null:
	if (amount.compareTo(BigDecimal.ZERO) <= 0) {
	    throw new IllegalArgumentException("amount must be positive");
	}
	this.amount = amount;
    }

    /**
     * Returns the origin account for this {@code transfer}.
     *
     * @return the origin account for this {@code transfer}; nerver {@code null}
     */
    public Iban getOrigin() {
	return origin;
    }

    private void setOrigin(Iban origin) {
	this.origin = origin;
    }

    /**
     * Returns the destination account for this {@code transfer}.
     *
     * @return the destination account for this {@code transfer}; nerver
     *         {@code null}
     */
    public Iban getDestination() {
	return destination;
    }

    private void setDestination(Iban destination) {
	this.destination = destination;
    }

    /**
     * Returns the request date for this {@code transfer}.
     *
     * @return the request date for this {@code transfer}; nerver {@code null}
     */
    public LocalDateTime getRequestDate() {
	return requestDate;
    }

    private void setRequestDate(LocalDateTime date) {
	requestDate = date;
    }

    /**
     * Returns the execution date for this {@code transfer}.
     *
     * @return the execution date for this {@code transfer}; nerver {@code null}
     */
    public LocalDateTime getExecutionDate() {
	return executionDate;
    }

    private void setExecutionDate(LocalDateTime date) {
	executionDate = date;
    }

    /**
     * Returns a string representation of this {@code transfer}.
     *
     * @return a string representation of this {@code transfer}
     * @see LocalDateTime#toString()
     * @see Iban#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{amount=");
	builder.append(amount);
	builder.append(", requestDate=");
	builder.append(requestDate);
	builder.append(", executionDate=");
	builder.append(executionDate);
	builder.append(", origin=");
	builder.append(origin);
	builder.append(", destination=");
	builder.append(destination);
	builder.append("}");
	return builder.toString();
    }
}
