package Axis.Axis_Spring.service.Impl;


import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.data.entity.Product;
import Axis.Axis_Spring.data.handler.impl.ProductDataHandlerImpl;
import Axis.Axis_Spring.service.impl.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
//내가 어떤 객체를 가져올지 모르겠다하면 아래 어노테션을 붙인다.
// @SpringBootTest(classes = {ProductDataHandlerImpl.class, ProductServiceImpl.class})

//필요한 부분만 가져다 쓰는 것이 아래와 같은 방식이다.
@ExtendWith(SpringExtension.class)  //@ExtendWith은 @SpringBootTest의 일부분이다.
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean  //ProductServiceImpl를 보면 아래 객체를 받기 때문에 똑같이 Mock객체를 만들어 준다.
    ProductDataHandlerImpl productDataHandler;

    @Autowired  //컨트롤러가 아니기 때문에 객체를 주입받는다.
    ProductServiceImpl productService;

    @Test
    public void getProductTest() {
        // when->getProductEntity()메서드를 호출할 때
        Mockito.when(productDataHandler.getProductEntity("123"))
                .thenReturn(new Product("123", "pen", 2000, 3000));//1번문장
        //하나의 사전세팅 작업으로 when()일때 그러면 thenReturn()메서드를 사용해서 반환해라..의미

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getProductId(), "123");  //2개의 매개변수가 같은지 체크
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).getProductEntity("123");  //1번문장이 제대로 수행되는지 체크
    }

    @Test
    public void saveProductTest() {
        // given
        Mockito.when(productDataHandler.saveProductEntity("123", "pen", 2000, 3000))
                .thenReturn(new Product("123", "pen", 2000, 3000));

        ProductDto productDto = productService.saveProduct("123", "pen", 2000, 3000);

        Assertions.assertEquals(productDto.getProductId(), "123");
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).saveProductEntity("123", "pen", 2000, 3000);
    }
}
