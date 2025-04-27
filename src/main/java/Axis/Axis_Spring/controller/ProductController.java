package Axis.Axis_Spring.controller;

import Axis.Axis_Spring.common.Constants;
import Axis.Axis_Spring.common.exception.AxisSpringException;
import Axis.Axis_Spring.data.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Axis.Axis_Spring.service.impl.ProductService;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private ProductService productService;  //프로그래머가 작성한 클래스

    @Autowired   //생성자 부분에서 이 어노테이션을 쓰면 메모리에 떠 있는 productService를 끌어다 연결해준다.
    public ProductController(ProductService productService){
                this.productService=productService;
    }

    //Get방식은 데이터를 조회할 때 쓰는 방식임으로 여기선 id를 통해 데이터를 가져오는 것을 구현한 것이다.
    //http://localhost:8080/api/v1/product-api/product/{productId}
    //http://localhost:8080/api/v1/product-api/product/Axis-Book 이걸로 Get방식으로 해보자
    //Id로 조회하는 방식
    @GetMapping(value = "/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId){
        return productService.getProduct(productId);
    }

    //http://localhost:8080/api/v1/product-api/product    post방식으로 실행할것
    /* 제이슨 내용
    {
        "productId":"Axis-Book",
        "productName":"Axis-Book-1",
        "productPrice":"5000",
        "productStock":"5"
    }
    */
    //Post방식은 데이터를 집어는 넣는 방식을 쓰는 것..
    @PostMapping(value = "/product")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto){
   //@Valid 유효성 검사를 하는 항목인데, ProductDto에서의 필드값에 유효하지 않은 값이 넘어가면 에러가 발생하게 설정하는것
        //상품가격을 -500등으로 넘기면 에러가 발생하게 설정
        System.out.println("getProductId를 찍어보자="+productDto.getProductId());
        String productId=productDto.getProductId();
        //http://localhost:8080/api/v1/product-api/product 페이지에서 제이슨 형태의 데이터를 메모리에 떠있는 productDto객체에 넘긴다.
        //이미 메모리에 떠있는 productDto객체의 각필드값의 제이슨 데이터가 넘어간것을 다시 가져온다.
        String productName=productDto.getProductName();
        int productPrice=productDto.getProductPrice();
        int productStock=productDto.getProductStock();

        ProductDto response=productService.saveProduct(productId, productName, productPrice, productStock);



        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    //http://localhost:8080/api/v1/product-api/product/{productId}
    @DeleteMapping(value = "/product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId){
        return null;
    }

    @PostMapping(value = "/product/exception")
    public void exceptionTest() throws AxisSpringException{
        throw  new AxisSpringException(Constants.ExceptionClass.PRODUCT, HttpStatus.FORBIDDEN, " 접근이 금지되었습니다.");
   }
}
