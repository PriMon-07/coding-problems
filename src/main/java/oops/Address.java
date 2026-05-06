package oops;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private Long pin;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address address)) return false;
        return Objects.equals(addressLine, address.addressLine) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(pin, address.pin) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressLine, city, state, pin, country);
    }
}
