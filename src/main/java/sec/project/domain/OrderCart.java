/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author veerakoskinen
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderCart implements Serializable {

    private Map<Item, Long> itemCounts;

    public OrderCart() {
        this.itemCounts = new TreeMap<>();
    }

    public void addToCart(Item product) {
        itemCounts.putIfAbsent(product, 0L);
        itemCounts.put(product, itemCounts.get(product) + 1);
    }
    
 // this one is now fixed (Only one if-sentence would have been enough, but now there cannot be the same mistake again.
    public void removeFromCart(Item product) {
        if (!itemCounts.containsKey(product) || itemCounts.get(product) <= 0l) {
            return;
        }

        itemCounts.put(product, itemCounts.get(product) - 1);
        if (itemCounts.get(product) == 0) {
            itemCounts.remove(product, itemCounts.get(product));
        }
    }

    public Map<Item, Long> getItems() {
        return itemCounts;
    }

    public double getSum() {
        return itemCounts.keySet().stream()
                .mapToDouble(item -> item.getPrice() * itemCounts.get(item))
                .sum();
    }
}
