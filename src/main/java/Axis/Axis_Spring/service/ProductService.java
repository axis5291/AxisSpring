package Axis.Axis_Spring.service;

import java.util.List;

import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.data.entity.ProductEntity;


//loose coupling-> DB가 바뀔수 있는 경우를 상정해서 우선 인터페이스를 작성한다.
public interface ProductService {
 ProductDto getProduct(String productId);
 List<ProductDto> getAllProduct();
 ProductDto deleteProduct(String productId);
 ProductDto saveProduct(ProductEntity productEntity);
 

}
