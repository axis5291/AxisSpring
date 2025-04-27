package Axis.Axis_Spring.data.dao;

import Axis.Axis_Spring.data.entity.Product;

public interface ProductDAO {

    Product saveProduct(Product product);
    Product getProduct(String productId);

}
