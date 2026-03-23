public interface AddOnPricingPolicy {
    Money monthlyChargeFor(AddOn addOn);
}
