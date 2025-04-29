package Axis.Axis_Spring.data.dao.impl;

import Axis.Axis_Spring.data.dao.ProductDAO;
import Axis.Axis_Spring.data.entity.Product;
import Axis.Axis_Spring.data.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//DAO는 Repository를 이용하여 작성한다.
@Service
public  class ProductDAOImpl implements ProductDAO {

    ProductRepository productRepository;

    //작성자가 객체를 만들지 않고 스프링이 만들어 낸 객체를 끌어와서 쓴다..의존성주입, 스프링은 싱클톤만 사용하기 때문에 미리 레포지토리객체를 하나 띄워놓고 이 하나를 여러곳에서 사용하는 방식
    //그래서 미리 메모리에 띄워져있는 productRepository 객체를 주입
    @Autowired   
    public ProductDAOImpl(ProductRepository productRepository){
        this.productRepository=productRepository;
    }

    @Override
    public Product getProduct(String productId) {
        Product product =productRepository.getById(productId); //productId를 넘겨주고 productEntity를 받아옴, getById() ->ProductRepository에는 없지만 조상인터페이스에 있음
        return product;
    }

    @Override
    public Product deleteProduct(String productId) {
       // 1. 먼저 삭제할 Product 조회
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다: " + productId));

        // 2. 그 다음 삭제
        productRepository.delete(product);
        System.out.println("삭제된 상품: " + productId);

        // 3. 삭제 전에 조회한 product 객체를 사용
        return product;
        
    }


    @Override
    public Product saveProduct(Product product){
        productRepository.save(product);  //productEntity를 넘겨주면 DB에 저장, save()는 ProductRepository에는 없지만 조상인터페이스에 있음
        return product;
    }

  


}
