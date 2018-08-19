package pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.converter;

import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.ZipCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static java.util.Objects.isNull;
import static pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.factory.ZipCodeFactory.of;

@Converter
public class ZipCodeConverter implements AttributeConverter<ZipCode, String> {

    @Override
    public String convertToDatabaseColumn(ZipCode attribute) {
        if(isNull(attribute)) return null;
        return attribute.getValue();
    }

    @Override
    public ZipCode convertToEntityAttribute(String s) {
        return of(s);
    }
}
