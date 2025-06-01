package se.kth.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.pos.model.observer.SaleObserver;

/**
 * Represents one sale transaction.
 */
public class Sale {
    private final LocalDateTime saleTime = LocalDateTime.now();
    private final List<ItemDTO> items = new ArrayList<>();
    private Amount runningTotal = new Amount(0);
    private DiscountDTO discount;
    private final List<SaleObserver> saleObservers = new ArrayList<>();

    public void addSaleObserver(SaleObserver obs) {
        saleObservers.add(obs);
    }

    public void removeSaleObserver(SaleObserver obs) {
        saleObservers.remove(obs);
    }

    private void notifySaleObservers() {
        SaleDTO dto = createSaleDTO(null);
        for (SaleObserver obs : saleObservers) {
            obs.saleUpdated(dto);
        }
    }

    public void addItem(ItemDTO item) {
        boolean exists = false;
        for (ItemDTO e : items) {
            if (e.getIdentifier().equals(item.getIdentifier())) {
                e.increaseQuantity();
                exists = true;
                break;
            }
        }
        if (!exists) {
            items.add(item);
        }
        updateRunningTotal();
        notifySaleObservers();
    }

    public Amount getRunningTotal() {
        return runningTotal;
    }

    public Amount getTotal() {
        return runningTotal;
    }

    public SaleDTO createSaleDTO(ItemDTO lastAddedItem) {
        return new SaleDTO(getItems(), runningTotal, lastAddedItem);
    }

    public Amount applyDiscount(DiscountDTO discount) {
        this.discount = discount;
        Amount discounted = runningTotal.multiply(discount.getDiscountRate());
        notifySaleObservers();
        return runningTotal.subtract(discounted);
    }

    public ReceiptDTO generateReceipt() {
        notifySaleObservers();
        return new ReceiptDTO(saleTime, items, runningTotal, discount);
    }

    private void updateRunningTotal() {
        runningTotal = new Amount(0);
        for (ItemDTO i : items) {
            runningTotal = runningTotal.add(i.getPrice().multiply(i.getQuantity()));
        }
    }

    public List<ItemDTO> getItems() {
        return new ArrayList<>(items);
    }
}
