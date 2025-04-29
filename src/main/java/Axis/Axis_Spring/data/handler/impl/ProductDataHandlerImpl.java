package Axis.Axis_Spring.data.handler.impl;

import Axis.Axis_Spring.data.dao.ProductDAO;
import Axis.Axis_Spring.data.entity.Product;
import Axis.Axis_Spring.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {
    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerImpl(ProductDAO productDAO){
        this.productDAO=productDAO;
    }

    @Override  //DB에서 엔티티를 불러오는 단계
    public Product getProductEntity(String productId) {
       return productDAO.getProduct((productId));
    }

    @Override
    public Product deleteProductEntity(String productId) {
        return productDAO.deleteProduct(productId);
    }

    @Override  //DB에 저장하는 작업
    public Product saveProductEntity(String productId, String productName, int productPrice, int productStock) {
       Product product =new Product(productId, productName, productPrice, productStock);
       return  productDAO.saveProduct(product);
     }

   
}
