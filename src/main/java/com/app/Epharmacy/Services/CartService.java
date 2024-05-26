package com.app.Epharmacy.Services;

import com.app.Epharmacy.Entity.Medicament;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    private final Map<Long, Medicament> cart = new HashMap<>();

    public void addToCart(Medicament medicament) {
        cart.put(medicament.getId(), medicament);
    }

    public boolean isInCart(Long id) {
        return cart.containsKey(id);
    }

    public Map<Long, Medicament> getCartItems() {
        return cart;
    }
    public void removeFromCart(Long id) {
        cart.remove(id);
    }
}
