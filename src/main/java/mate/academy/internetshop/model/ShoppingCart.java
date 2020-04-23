package mate.academy.internetshop.model;

import java.util.List;

public class ShoppingCart {
    private Long id;
    private List<Product> products;
    private Long orderId;
    private User user;

    public ShoppingCart(List<Product> items, Long orderId, User user) {
        this.id = id;
        this.products = items;
        this.orderId = orderId;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getItems() {
        return products;
    }

    public void setItems(List<Product> items) {
        this.products = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
