package Axis.Axis_Spring.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.*;
import  Axis.Axis_Spring.data.dto.ProductDto;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product")  //ProductEntity를 기반으로 디비에 테이블을 자동으로 생성해주는 옵션
// 이때 테이블의 이름을 name="product"으로 지정

public class Product {

    @Id //DB의 프라이머리 키와 동일한 의미이고 productId에 속성을 부여하였다.
    String productId;
    String productName;
    Integer productPrice;
    Integer productStock;

    public ProductDto toDto(){
        return ProductDto.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
