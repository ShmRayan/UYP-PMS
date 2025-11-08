package domain.patient;

import java.time.LocalDate;
import java.util.Objects;

public final class InsuranceInfo {
    private final String policyNumber;
    private final String provider;
    private final LocalDate expiryDate;

    public InsuranceInfo(String policyNumber, String provider, LocalDate expiryDate) {
        this.policyNumber = Objects.requireNonNull(policyNumber, "Policy number cannot be null");
        this.provider = Objects.requireNonNull(provider, "Provider cannot be null");
        this.expiryDate = Objects.requireNonNull(expiryDate, "Expiry date cannot be null");
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getProvider() { return provider; }
    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String toString() {
        return policyNumber + " (" + provider + "), expires " + expiryDate;
    }
}
