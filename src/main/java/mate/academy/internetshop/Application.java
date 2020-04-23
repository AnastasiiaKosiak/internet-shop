package mate.academy.internetshop;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.service.ProductService;

public class Application {
    private static Injector injector = Injector.getInstance("mate.academy.internetshop");

    public static void main(String[] args) {
        ProductService itemService = (ProductService)injector.getInstance(ProductService.class);

        Product product1 = new Product("Little Prince", new BigDecimal(12.00));
        Product product2 = new Product("The Book thief", new BigDecimal(21.00));
        Product product3 = new Product("Steve Jobs. A Biography", new BigDecimal(15.34));

        itemService.create(product1);
        itemService.create(product2);
        itemService.create(product3);

        List<Product> itemList = itemService.getAll();
        for (Product item : itemList) {
            System.out.println(item);
        }
    }
}
