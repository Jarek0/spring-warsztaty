package pl.edu.pollub.warsztaty.billingDetails.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pollub.warsztaty.billingDetails.BillingDetails;

@Repository
public interface BillingDetailsDao extends JpaRepository<BillingDetails, Long> {
}