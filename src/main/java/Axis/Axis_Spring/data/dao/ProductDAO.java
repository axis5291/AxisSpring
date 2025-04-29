package Axis.Axis_Spring.data.dao;

import Axis.Axis_Spring.data.entity.Product;

public interface ProductDAO {

    Product getProduct(String productId);
    Product deleteProduct(String productId);
    Product saveProduct(Product product);
   

}
