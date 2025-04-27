package Axis.Axis_Spring.data.dto;

import Axis.Axis_Spring.data.entity.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//상품의 정보를 등록하거나 빼거나 수정, 조회 작업을 할려고 만들었다.
//데이터를 넘길려고 만듬
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

    @NotNull  //dependencies에서 spring-boot-starter-validation을 추가하여 데이터의 유효성 검사를 부여할 수 있다.
    private String productId;

    @NotNull
    private String productName;

    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)  //validation 디펜던시
    private int productPrice;

    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int productStock;


    public Product toEntity(){
        return Product.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();

    }

}
