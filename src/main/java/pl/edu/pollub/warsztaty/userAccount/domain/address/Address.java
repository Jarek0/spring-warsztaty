package pl.edu.pollub.warsztaty.userAccount.domain.address;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.ZipCode;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.converter.ZipCodeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

import static pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.factory.ZipCodeFactory.of;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

    @Column(nullable = false)
    private String street;

    @Convert(converter = ZipCodeConverter.class)
    @Column(nullable = false)
    private ZipCode zipCode;

    @Column(nullable = false)
    private String city;

    public Address(String street, String zipCodeValue, String city) {
        this.street = street;
        this.zipCode = of(zipCodeValue);
        this.city = city;
    }
}
