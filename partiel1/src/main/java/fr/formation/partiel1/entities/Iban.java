package fr.formation.partiel1.entities;

import java.util.Objects;

/**
 * Represents an International Bank Account Number.
 * <p>
 * Class invariants:
 * <ul>
 * <li>All the properties are guaranteed not {@code null}
 * </ul>
 *
 * @author Frank MARSHALL
 */
public class Iban {

    private String country;

    private String key;

    private Bban bban;

    /**
     * Creates a new {@code Iban} with given country code, IBAN key and BBAN.
     *
     * @param country
     *            a country code
     * @param key
     *            an IBAN key
     * @param bban
     *            a BBAN
     * @throws NullPointerException
     *             if any of the argument is {@code null}
     */
    public Iban(String country, String key, Bban bban) {
	setCountry(country);
	setKey(key);
	setBban(bban);
    }

    /**
     * Returns the country code.
     *
     * @return the country code; never {@code null}
     */
    public String getCountry() {
	return country;
    }

    private void setCountry(String country) {
	Objects.requireNonNull(country, "country must not be null");
	this.country = country;
    }

    /**
     * Returns the IBAN key.
     *
     * @return the IBAN key; never {@code null}
     */
    public String getKey() {
	return key;
    }

    private void setKey(String key) {
	Objects.requireNonNull(key, "key must not be null");
	this.key = key;
    }

    /**
     * Returns the BBAN.
     *
     * @return the BBAN; never {@code null}
     */
    public Bban getBban() {
	return bban;
    }

    private void setBban(Bban bban) {
	Objects.requireNonNull(bban, "bban must not be null");
	this.bban = bban;
    }

    /**
     * Indicates whether or not given {@code obj} is equal to this {@code iban}.
     * <p>
     * Two {@code Iban} instances are considered as equal if their BBAN are
     * equal.
     *
     * @param obj
     *            an object to compare against
     * @return {@code true} if {@code obj} is equal to this {@code iban};
     *         {@code false} otherwise
     * @see Bban#equals(Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (obj == this) {
	    return true;
	}
	if (!(obj instanceof Iban)) {
	    return false;
	}
	Iban other = (Iban) obj;
	return bban.equals(other.bban);
    }

    /**
     * Returns a hash code fot his {@code iban}.
     * <p>
     * The implementation is consistent with {@code equals}.
     *
     * @return a hash code value
     * @see Bban#hashCode()
     */
    @Override
    public int hashCode() {
	return Objects.hash(bban);
    }

    /**
     * Returns a string representation of this {@code iban}.
     *
     * @return a string representation of this {@code iban}
     * @see Bban#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("{country=");
	builder.append(country);
	builder.append(", key=");
	builder.append(key);
	builder.append(", bban=");
	builder.append(bban);
	builder.append("}");
	return builder.toString();
    }
}
