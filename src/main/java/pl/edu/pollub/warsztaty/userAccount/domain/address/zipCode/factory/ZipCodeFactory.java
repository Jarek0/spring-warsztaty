package pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.factory;

import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.ZipCode;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.impl.GermanZipCode;
import pl.edu.pollub.warsztaty.userAccount.domain.address.zipCode.impl.SwissZipCode;

public class ZipCodeFactory {
    public static ZipCode of(String s) {
        if(s.length() == 5) {
            return new GermanZipCode(s);
        }
        else if(s.length() == 4){
            return new SwissZipCode(s);
        }

        throw new IllegalArgumentException("Niebsługiwany kod pocztowy w bazie danych: " + s);
    }
}
