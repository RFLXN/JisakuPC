package bean;

import java.io.Serializable;

public class StackedProduct implements Serializable {
    private Product product;
    private int stack;
    private String stackedPrice;

    public StackedProduct() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public String getStackedPrice() {
        return Integer.toString(Integer.parseInt(this.product.getPrice()) * stack);
    }
}
