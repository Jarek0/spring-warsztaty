package pl.edu.pollub.warsztaty.userAccount.factory;

import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.userAccount.domain.address.Address;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class AddressFactory {

    public static Address createJarekHome() {
        return new Address("3 Maja", "1-122", "Skierbieszow");
    }

    public static Address createJarekBillingAddress() {
        return new Address("Nadbystrzycka", "1-123", "Lublin");
    }

    public static Address createAndrzejHome() {
        return new Address("1 Maja", "1-14", "Lublin");
    }
}
