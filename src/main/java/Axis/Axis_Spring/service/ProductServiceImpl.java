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
       ProductDto productDto=productEntity.toDto(); //ProductEntity에서 ProductDto로 변환하는 메서드
       return productDto;
   }  //맨 아래의 것을 productEntity.toDto()를 이용하여 변환작업을 해주었다.


//    @Override
//    public List<ProductDto> getAllProduct() {
//        List<ProductEntity> productEntityList = productDataHandler.getAllProductEntity();
       
//    }

   @Override
   public ProductDto deleteProduct(String productId) {
        ProductEntity productEntity = productDataHandler.deleteProductEntity(productId);
        ProductDto productDto=productEntity.toDto(); //ProductEntity에서 ProductDto로 변환하는 메서드
        return productDto;
      
   }
   
    @Override
    public ProductDto saveProduct(ProductEntity productEntity) {
        ProductEntity productEntitySaved =productDataHandler.saveProductEntity(productEntity);
        ProductDto productDto=productEntitySaved.toDto(); //저장된 ProductEntity를 ProductDto로 변환
        return productDto;
    }

}

//    @Override
//    public ProductDto getProduct(String productId) {
//        ProductEntity productEntity =productDataHandler.getProductEntity(productId);
//        ProductDto productDto=new ProductDto(productEntity.getProductId(), productEntity.getProductName(),
//        productEntity.getProductPrice(), productEntity.getProductStock());
//        return productDto;
//    }


// @Override
// public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
//     ProductEntity productEntity =productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);
//     ProductDto productDto=productEntity.toDto(); //ProductEntity에서 ProductDto로 변환하는 메서드
//     return productDto;
// }
