package Axis.Axis_Spring.data.dao;


import Axis.Axis_Spring.data.entity.ProductEntity;

public interface ProductDao {

    ProductEntity getProduct(String productId);
    ProductEntity deleteProduct(String productId);
    ProductEntity saveProduct(ProductEntity product);
   

}
