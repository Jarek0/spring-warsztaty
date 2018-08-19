package pl.edu.pollub.warsztaty.userAccount.factory;

import lombok.NoArgsConstructor;
import pl.edu.pollub.warsztaty.userAccount.domain.Address;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class AddressFactory {

    public static Address createJarekHome() {
        return new Address("3 Maja", "12-122", "Skierbieszow");
    }

    public static Address createJarekBillingAddress() {
        return new Address("Nadbystrzycka", "12-123", "Lublin");
    }

    public static Address createAndrzejHome() {
        return new Address("1 Maja", "12-124", "Lublin");
    }
}
