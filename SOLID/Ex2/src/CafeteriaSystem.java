import java.util.*;
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceCalculator calculator;
    private int invoiceSeq = 1000;
    public CafeteriaSystem(InvoiceStore store, InvoiceCalculator calculator) {
        this.store = store;
        this.calculator = calculator;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        InvoiceData data = calculator.calculate(customerType, lines, menu);

        String printable = InvoiceFormatter.format(invId, lines, menu, data);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}