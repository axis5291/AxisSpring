package Axis.Axis_Spring.service;

import Axis.Axis_Spring.data.dto.ProductDto;


//loose coupling-> DB가 바뀔수 있는 경우를 상정해서 우선 인터페이스를 작성한다.
public interface ProductService {
 ProductDto getProduct(String productId);
 ProductDto deleteProduct(String productId);
 ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);
 

}
