package Axis.Axis_Spring.service.impl.impl;

import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.data.entity.Product;
import Axis.Axis_Spring.data.handler.ProductDataHandler;
import Axis.Axis_Spring.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        Product product =productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        ProductDto productDto=new ProductDto(product.getProductId(), product.getProductName(),
                                            product.getProductPrice(), product.getProductStock());
        //productEntity에서 productDto 변환작업을 해주었다.
        return productDto;
    }

    @Override
    public ProductDto getProduct(String productId) {
        Product product =productDataHandler.getProductEntity(productId);
        ProductDto productDto=new ProductDto(product.getProductId(), product.getProductName(),
                                             product.getProductPrice(), product.getProductStock());
        return productDto;
    }
}
