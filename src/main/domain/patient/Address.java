package domain.patient;

import java.util.Objects;

public final class Address {
    private final String street;
    private final String city;
    private final String province;
    private final String postalCode;

    public Address(String street, String city, String province, String postalCode) {
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.province = Objects.requireNonNull(province, "Province cannot be null");
        this.postalCode = Objects.requireNonNull(postalCode, "Postal code cannot be null");
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }

    @Override
    public String toString() {
        return street + ", " + city + ", " + province + " " + postalCode;
    }
}
