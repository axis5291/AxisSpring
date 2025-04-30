package Axis.Axis_Spring.data.handler;
import Axis.Axis_Spring.data.entity.ProductEntity;
public interface ProductDataHandler {

    public ProductEntity getProductEntity(String productId) ;
    public ProductEntity deleteProductEntity(String productId) ;
    public ProductEntity saveProductEntity(ProductEntity productEntity) ;
    
}
