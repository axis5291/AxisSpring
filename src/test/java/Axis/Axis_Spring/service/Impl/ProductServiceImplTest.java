package Axis.Axis_Spring.service.Impl;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import Axis.Axis_Spring.data.dto.ProductDto;
import Axis.Axis_Spring.data.entity.ProductEntity;
import Axis.Axis_Spring.data.handler.ProductDataHandlerImpl;
import Axis.Axis_Spring.service.ProductServiceImpl;

// @SpringBootTest와 같이 전체를 가동하면 부하가 많이 걸리므로 필요한 부분만 가져다 쓰는 것이 아래와 같은 방식이다. @ExtendWith + @Import = 내가 필요한 가게 두세 개만 골라서 열기
//@ExtendWith(SpringExtension.class) ->DI(의존성 주입), 컨텍스트 관리 등 핵심 기능만
@ExtendWith(SpringExtension.class)  // @ExtendWith은 @SpringBootTest의 일부분이다.
@Import({ProductDataHandlerImpl.class, ProductServiceImpl.class})
public class ProductServiceImplTest {

    @MockBean  // ProductServiceImpl를 보면 아래 객체를 받기 때문에 똑같이 Mock 객체를 만들어 준다.
    ProductDataHandlerImpl productDataHandler;

    @Autowired  // 컨트롤러가 아니기 때문에 객체를 주입받는다.
    ProductServiceImpl productService;

    @Test
    public void getProductTest() {
        // when -> getProductEntity() 메서드를 호출할 때
        Mockito.when(productDataHandler.getProductEntity("123"))
                .thenReturn(new ProductEntity("123", "pen", 2000, 3000, null));  // 1번 문장

        ProductDto productDto = productService.getProduct("123");

        Assertions.assertEquals(productDto.getProductId(), "123");  // 2개의 매개변수가 같은지 체크
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).getProductEntity("123");  // 1번 문장이 제대로 수행되는지 체크
    }

    @Test
    public void saveProductTest() {
        // given
        ProductDto inputDto = new ProductDto("123", "pen", 2000, 3000);
        ProductEntity savedEntity = new ProductEntity("123", "pen", 2000, 3000, null);

        Mockito.when(productDataHandler.saveProductEntity(Mockito.any(ProductEntity.class)))
                .thenReturn(savedEntity);

        ProductDto productDto = productService.saveProduct(inputDto);

        Assertions.assertEquals(productDto.getProductId(), "123");
        Assertions.assertEquals(productDto.getProductName(), "pen");
        Assertions.assertEquals(productDto.getProductPrice(), 2000);
        Assertions.assertEquals(productDto.getProductStock(), 3000);

        verify(productDataHandler).saveProductEntity(Mockito.any(ProductEntity.class));
    }
}
