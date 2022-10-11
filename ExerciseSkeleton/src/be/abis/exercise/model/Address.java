package be.abis.exercise.model;

import be.abis.exercise.exception.ZipCodeNotCorrectException;

import java.util.Objects;

public class Address {
	
	private String street;
	private String nr;
	private String zipCode;
	private String town;
	private String country;
	private String countryCode;
	
	public Address(){
		
	}
	
	public Address(String street, String nr, String zipCode, String town, String country, String countryCode) {
		this();
		this.street = street;
		this.nr = nr;
		this.zipCode = zipCode;
		this.town = town;
		this.country = country;
		this.countryCode = countryCode;
	}

	public void checkZipCode() throws ZipCodeNotCorrectException {
		String regexp2 = "[1-9]\\d{3}";
		String regexp3 = "[1-9]\\d{3}[A-Z]{2}";
		if (countryCode.equals("BE")){
			if(!this.zipCode.matches(regexp2)) throw new ZipCodeNotCorrectException("zipcode incorrect for BE");
		}
		if (countryCode.equals("NL")){
			if(!this.zipCode.matches(regexp3)) throw new ZipCodeNotCorrectException("zipcode incorrect for NL");
		}

	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
	public String toString(){
		return street + " " + nr + ", " + countryCode + " - " +zipCode+ " " + town;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Address address = (Address) o;
		return street.equals(address.street) && nr.equals(address.nr) && zipCode.equals(address.zipCode) && town.equals(address.town) && country.equals(address.country) && countryCode.equals(address.countryCode);
	}

	@Override
	public int hashCode() {
		return Objects.hash(street, nr, zipCode, town, country, countryCode);
	}
}
