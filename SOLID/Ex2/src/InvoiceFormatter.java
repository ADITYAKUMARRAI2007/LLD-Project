import java.util.List;
import java.util.Map;

public class InvoiceFormatter {

    public static String format(String invId,
                                List<OrderLine> lines,
                                Map<String, MenuItem> menu,
                                InvoiceData data) {

        StringBuilder out = new StringBuilder();

        out.append("Invoice# ").append(invId).append("\n");

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n",
                    item.name, l.qty, lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", data.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.taxPercent, data.tax));
        out.append(String.format("Discount: -%.2f\n", data.discount));
        out.append(String.format("TOTAL: %.2f\n", data.total));

        return out.toString();
    }
}