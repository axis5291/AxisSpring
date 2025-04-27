package Axis.Axis_Spring.data.handler;

import Axis.Axis_Spring.data.entity.Product;

public interface ProductDataHandler {

    public Product saveProductEntity(String productId, String productName, int productPrice, int productStock);
    public Product getProductEntity(String productId) ;
}
