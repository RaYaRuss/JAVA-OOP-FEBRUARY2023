import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;
    public Instock() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return this.products.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.products.contains(product);
    }

    @Override
    public void add(Product product) {
       this.products.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        Product foundProduct = this.products
                .stream()
                .filter(p -> p.getLabel().equals(product))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        foundProduct.setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        return this.products.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        return this.products
                .stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count > this.products.size()) {
            return new ArrayList<>();
        }
        return this.products
                .stream()
                .limit(count)
                .sorted(Comparator.comparing(Product::getLabel))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.products
                .stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products
                .stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        return null;
        //TODO
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        throw new UnsupportedOperationException();
    }
    //TODO
    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
}
