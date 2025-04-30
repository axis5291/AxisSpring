package Axis.Axis_Spring.data.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Axis.Axis_Spring.data.dao.ProductDao;
import Axis.Axis_Spring.data.entity.ProductEntity;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {
    ProductDao productDao;

    @Autowired
    public ProductDataHandlerImpl(ProductDao productDao){
        this.productDao=productDao;
    }

    @Override  //DB에서 엔티티를 불러오는 단계
    public ProductEntity getProductEntity(String productId) {
       return productDao.getProduct((productId));
    }

    @Override
    public ProductEntity deleteProductEntity(String productId) {
        return productDao.deleteProduct(productId);
    }

    @Override  //DB에 저장하는 작업
    public ProductEntity saveProductEntity(ProductEntity productEntity) {
              return  productDao.saveProduct(productEntity);
     }

   
}

// @Override  //DB에 저장하는 작업
// public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
//     ProductEntity productEntity =new ProductEntity(productId, productName, productPrice, productStock);
//    return  productDao.saveProduct(productEntity);
//  }

