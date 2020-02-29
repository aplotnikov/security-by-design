package io.github.aplotnikov.domain.v1;

final class RiskCheckResult {

    private final boolean isRisky;

    private RiskCheckResult(boolean isRisky) {
        this.isRisky = isRisky;
    }

    static RiskCheckResult risky() {
        return new RiskCheckResult(true);
    }

    static RiskCheckResult notRisky() {
        return new RiskCheckResult(false);
    }

    boolean isRisky() {
        return isRisky;
    }

    @Override
    public String toString() {
        return "RiskCheckResult{isRisky=" + isRisky + '}';
    }
}
