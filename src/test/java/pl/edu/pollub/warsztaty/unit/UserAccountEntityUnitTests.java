package pl.edu.pollub.warsztaty.unit;

import org.junit.Test;
import pl.edu.pollub.warsztaty.userAccount.domain.UserAccountEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static pl.edu.pollub.warsztaty.utils.UserAccountFactory.of;

public class UserAccountEntityUnitTests {

    @Test
    public void shouldEqualsWhenTheSamePesel() {
        UserAccountEntity jarek = of(1L, "99999999999");
        UserAccountEntity jarekClone = of(2L, "99999999999");

        assertEquals(jarek, jarekClone);
    }

    @Test
    public void shouldSameHashCodeWhenTheSamePesel() {
        UserAccountEntity jarek = of(1L, "99999999999");
        UserAccountEntity jarekClone = of(2L, "99999999999");

        assertEquals(jarek.hashCode(), jarekClone.hashCode());
    }

    @Test
    public void shouldNotEqualsWhenPeselIsDifferent() {
        UserAccountEntity jarek = of(1L, "99999999999");
        UserAccountEntity andrzej = of(1L, "99999999998");

        assertNotEquals(jarek, andrzej);
    }

    @Test
    public void shouldNotSameHashCodeWhenPeselIsDifferent() {
        UserAccountEntity jarek = of(1L, "99999999999");
        UserAccountEntity andrzej = of(1L, "99999999998");

        assertNotEquals(jarek.hashCode(), andrzej.hashCode());
    }
}
