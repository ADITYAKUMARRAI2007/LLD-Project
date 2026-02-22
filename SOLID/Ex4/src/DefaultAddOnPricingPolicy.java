import java.util.EnumMap;
import java.util.Map;

public class DefaultAddOnPricingPolicy implements AddOnPricingPolicy {
    private final Map<AddOn, Money> prices = new EnumMap<>(AddOn.class);

    public DefaultAddOnPricingPolicy() {
        prices.put(AddOn.MESS, new Money(1000.0));
        prices.put(AddOn.LAUNDRY, new Money(500.0));
        prices.put(AddOn.GYM, new Money(300.0));
    }

    @Override
    public Money monthlyChargeFor(AddOn addOn) {
        return prices.getOrDefault(addOn, new Money(0.0));
    }
}
