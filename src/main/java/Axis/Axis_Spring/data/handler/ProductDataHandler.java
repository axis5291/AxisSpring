package Axis.Axis_Spring.data.handler;

import Axis.Axis_Spring.data.entity.Product;

public interface ProductDataHandler {

    public Product getProductEntity(String productId) ;
    public Product deleteProductEntity(String productId) ;
    public Product saveProductEntity(String productId, String productName, int productPrice, int productStock);
    
}
