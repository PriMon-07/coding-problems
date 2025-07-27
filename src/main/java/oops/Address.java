package oops;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private Long pin;
    private String country;
}
