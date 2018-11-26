package fr.formation.partiel1.entities;

import java.util.Objects;

/**
 * This class represents a Basic Bank Account Number.
 * <p>
 * Class invariants:
 * <ul>
 * <li>All the properties are guaranteed not {@code null}
 * <li>The account number length is guaranteed not to exceed 11 characters
 * </ul>
 *
 * @author Frank MARSHALL
 */
public class Bban {

    private String bank;

    private String counter;

    private String account;

    private String key;

    /**
     * Creates a new {@code Bban} with given bank code, counter code, account
     * number and BBAN key.
     *
     * @param bank
     *            a bank code
     * @param counter
     *            a counter code
     * @param account
     *            an account number
     * @param key
     *            a BBAN key
     * @throws NullPointerException
     *             if any of the argument is {@code null}
     * @throws IllegalArgumentException
     *             if {@code account} length exceeds 11 characters
     */
    public Bban(String bank, String counter, String account, String key) {
	setBank(bank);
	setCounter(counter);
	setAccount(account);
	setKey(key);
    }

    /**
     * Returns the bank code.
     *
     * @return the bank code; never {@code null}
     */
    public String getBank() {
	return bank;
    }

    private void setBank(String bank) {
	Objects.requireNonNull(bank, "bank must not be null");
	this.bank = bank;
    }

    /**
     * Returns the counter code.
     *
     * @return the counter code; never {@code null}
     */
    public String getCounter() {
	return counter;
    }

    private void setCounter(String counter) {
	Objects.requireNonNull(counter, "counter must not be null");
	this.counter = counter;
    }

    /**
     * Returns the account number.
     *
     * @return the account number; never {@code null}
     */
    public String getAccount() {
	return account;
    }

    private void setAccount(String account) {
	// Null pointer exception if account is null:
	if (account.length() > 11) {
	    throw new IllegalArgumentException(
		    "account must not exceed 11 chars");
	}
	this.account = account;
    }

    /**
     * Returns the BBAN key.
     *
     * @return the BBAN key; never {@code null}
     */
    public String getKey() {
	return key;
    }

    private void setKey(String key) {
	Objects.requireNonNull(key, "key must not be null");
	this.key = key;
    }

    /**
     * Indicates whether or not given {@code obj} is equal to this {@code bban}.
     * <p>
     * Two {@code Bban} instances are considered as equal if their bank code and
     * acount number are equal.
     *
     * @param obj
     *            an object to compare against
     * @return {@code true} if {@code obj} is equal to this {@code bban};
     *         {@code false} otherwise
     * @see String#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == this) {
	    return true;
	}
	if (!(obj instanceof Bban)) {
	    return false;
	}
	Bban other = (Bban) obj;
	return bank.equals(other.bank) && account.equals(other.account);
    }

    /**
     * Returns a hash code fot his {@code bban}.
     * <p>
     * The implementation is consistent with {@code equals}.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
	return Objects.hash(bank, account);
    }

    /**
     * Returns a string representation of this {@code bban}.
     *
     * @return a string representation of this {@code bban}
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{bank=");
	builder.append(bank);
	builder.append(", counter=");
	builder.append(counter);
	builder.append(", account=");
	builder.append(account);
	builder.append(", key=");
	builder.append(key);
	builder.append("}");
	return builder.toString();
    }
}
