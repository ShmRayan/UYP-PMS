package domain.patient;

import java.util.Objects;

public final class Allergy {
    private final String substance;
    private final String reaction;

    public Allergy(String substance, String reaction) {
        this.substance = Objects.requireNonNull(substance, "Substance cannot be null");
        this.reaction = Objects.requireNonNull(reaction, "Reaction cannot be null");
    }

    public String getSubstance() { return substance; }
    public String getReaction() { return reaction; }

    @Override
    public String toString() {
        return substance + " → " + reaction;
    }
}
