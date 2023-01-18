package Com.sda;

import java.util.ArrayList;
import java.util.List;

public class Tray {

    private List<Product> products;

    public Tray() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }

    @Override
    public String toString() {
        return "Tray{" +
                "products=" + products +
                '}';
    }
}
