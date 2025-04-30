package Axis.Axis_Spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.data.entity.ProductEntity;
import Axis.Axis_Spring.data.handler.ProductDataHandler;

      /*  서비스 클래스에서 Entity를 Dto로 변환해주는 작업을 했다.
        이 변환작업은 컨트롤러에서도 할수 있는데 이것은 프로젝트별 상황에 맞게 바낄수 있다.*/

@Service
public class ProductServiceImpl implements ProductService {

    ProductDataHandler productDataHandler;

    @Autowired
   public ProductServiceImpl(ProductDataHandler productDataHandler){
       this.productDataHandler=productDataHandler;
   }

   @Override
   public ProductDto getProduct(String productId) {
       ProductEntity productEntity =productDataHandler.getProductEntity(productId);
       ProductDto productDto=new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
       productEntity.getProductPrice(), productEntity.getProductStock());
       return productDto;
   }

   @Override
   public ProductDto deleteProduct(String productId) {
        ProductEntity productEntity = productDataHandler.deleteProductEntity(productId);
        ProductDto productDto=new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
        productEntity.getProductPrice(), productEntity.getProductStock());
        return productDto;
      
   }
   
    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity =productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        ProductDto productDto=new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
        productEntity.getProductPrice(), productEntity.getProductStock());
        //productEntity에서 productDto 변환작업을 해주었다.
        return productDto;
    }

   
}