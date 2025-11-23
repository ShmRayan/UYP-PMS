package domain.prescription;

import java.util.concurrent.atomic.AtomicInteger;

public class PrescriptionId {

    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private final String value;

    public PrescriptionId() {
        int number = COUNTER.getAndIncrement();
        this.value = String.format("PRESC-%03d", number);
    }

    public PrescriptionId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
