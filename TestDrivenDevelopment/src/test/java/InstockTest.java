import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.transform.FieldVisitorTee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class InstockTest {
    private ProductStock stock;

    @Before
    public void setUp() {
        this.stock = new Instock(); // създаваме празен склад
    }
    // тестваме метода contains + метода add
    @Test
    public void testContainsAndAdd() {
        Product product = new Product("water", 1.2, 2);
        Assert.assertFalse(this.stock.contains(product));
        this.stock.add(product);
        Assert.assertTrue(this.stock.contains(product));
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(0, stock.getCount());
        fillStock();
        Assert.assertEquals(3, stock.getCount());
    }

    @Test
    public void testFindReturnsCorrectProduct() {
        fillStock();
        Product foundProduct = this.stock.find(2);
        Assert.assertEquals(foundProduct.getLabel(), "cheese");
        Assert.assertEquals(foundProduct.getQuantity(), 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindThrowsExceptionIndexGreater() {
        fillStock();
        this.stock.find(4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindThrowsExceptionIndexNegative() {
        fillStock();
        this.stock.find(-1);
    }

    private void fillStock() {
        Product product1 = new Product("water", 1.20, 2);
        Product product2 = new Product("bread", 2.90, 3);
        Product product3 = new Product("cheese", 3.90, 5);
        this.stock.add(product1);
        this.stock.add(product2);
        this.stock.add(product3);
    }

    @Test
    public void testChangeQuantitySuccessfulUpdate() {
        fillStock();
        Product productBread = this.stock.find(1);
        this.stock.changeQuantity("bread", 9);
        Assert.assertEquals(productBread.getQuantity(), 9);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityThrowsInvalidProduct() {
        fillStock();
        this.stock.changeQuantity("wine", 8);
    }
    @Test
    public void testFindByLabelReturnsCorrectProduct() {
        fillStock();
        Product expectedProduct = this.stock.find(0);
        Product returnedProduct = this.stock.findByLabel("water");
        Assert.assertEquals(expectedProduct.getLabel(), returnedProduct.getLabel());
        Assert.assertEquals(expectedProduct.getQuantity(), returnedProduct.getQuantity());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelThrowsInvalidProduct(){
        fillStock();
        this.stock.findByLabel("potato");
    }
    @Test
    public void testFindFirstByAlphabeticalOrderCorrectCountProduct() {
        fillStock();
        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(2);
        int countGetProduct = 0;
        for (Product product : iterable) {
            countGetProduct++;
        }
        Assert.assertEquals(2, countGetProduct);
    }
    @Test
    public void testFindByAlphabeticalOrderCorrectSorted() {
        List<String> expectedProductLabels = new ArrayList<>();
        Product product1 = new Product("water", 1.20, 2);
        Product product2 = new Product("bread", 1.90, 3);
        Product product3 = new Product("cheese", 3.50, 5);
        this.stock.add(product1);
        expectedProductLabels.add(product1.getLabel());
        this.stock.add(product2);
        expectedProductLabels.add(product2.getLabel());
        this.stock.add(product3);
        expectedProductLabels.add(product3.getLabel());

        expectedProductLabels = expectedProductLabels
                .stream()
                .sorted()
                .collect(Collectors.toList());

        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(3);
        List<String> returnedProductLabels = new ArrayList<>();

        for (Product product : iterable) {
            returnedProductLabels.add(product.getLabel());
        }
        Assert.assertEquals(expectedProductLabels, returnedProductLabels);
    }
    @Test
    public void testFindByAlphabeticalOrderThrowsInvalidCount() {
        fillStock();
        Iterable<Product> iterable = this.stock.findFirstByAlphabeticalOrder(5);
        List<Product> returnedProducts = getProductsFromIterable(iterable);
        Assert.assertTrue(returnedProducts.isEmpty());
    }
    @Test
    public void testFindAllInPriceRange() {
        fillStock();
        Iterable<Product> returnedProducts = this.stock.findAllInRange(1.10, 3.50);
        int countReturnedProducts = 0;
        for (Product product : returnedProducts) {
            countReturnedProducts++;
        }
        Assert.assertEquals(2, countReturnedProducts);
    }
    @Test
    public void testFindAllInPriceRangeCorrectOrder() {
        fillStock();
        Iterable<Product> iterable = this.stock.findAllInRange(1.10, 2.95);
        List<Product> returnProducts = getProductsFromIterable(iterable);
        Assert.assertEquals("bread", returnProducts.get(0).getLabel());
        Assert.assertEquals("water", returnProducts.get(1).getLabel());
    }
    @Test
    public void testFindAllInPriceRangeNoMatch() {
        fillStock();
        Iterable<Product> iterable = this.stock.findAllInRange(10, 20);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testFindAllByPriceCorrectProducts() {
        fillStock();
        Iterable<Product> iterable = this.stock.findAllByPrice(3.90);
        List<Product> returnedProducts = getProductsFromIterable(iterable);
        Assert.assertEquals(1, returnedProducts.size());
        Assert.assertEquals("cheese", returnedProducts.get(0).getLabel());
    }

    private List<Product> getProductsFromIterable(Iterable<Product> iterable) {
        List<Product> returnedProducts = new ArrayList<>();
        for (Product product : iterable) {
            returnedProducts.add(product);
        }
        return returnedProducts;
    }

    @Test
    public void testFindAllByPriceEmptyProducts() {
        fillStock();
        Iterable<Product> iterable = this.stock.findAllByPrice(13.90);
        Assert.assertFalse(iterable.iterator().hasNext());
    }
    @Test
    public void testIterator() {
        fillStock();
        Iterator<Product> iterator = this.stock.iterator();
        List<Product> returnedProducts = new ArrayList<>();
        while(iterator.hasNext()) {
            returnedProducts.add(iterator.next());
        }

    }

}