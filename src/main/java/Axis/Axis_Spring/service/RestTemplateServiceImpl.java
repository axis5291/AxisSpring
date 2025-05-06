package Axis.Axis_Spring.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import Axis.Axis_Spring.data.dto.MemberDto;
//이서비스를 사용할려면 AxisServerBox의 별도의 springApp를 사용하여 구동시켜야 한다. AxisServerBox에서 요청에 대한 응답을 하는 별도의 서버이다.
// RestTemplate은 Spring Framework에서 제공하는 HTTP 통신 클라이언트,  다른 서버(혹은 내 서버의 다른 서비스)와 통신할 수 있게 해주는 도구
@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER= LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getAxis(){
        System.out.println("getAxis() 메서드 실행");
        URI uri= UriComponentsBuilder   //*1.URI설정 UriComponentsBuilder : Spring에서 URI(주소)를 안전하고 깔끔하게 생성해주는 도구
                .fromUriString("http://localhost:9090")
                .path("/api/server/jong-ho")   //뒤에 붙는 경로
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경,   build().toUri(): 최종 URI 객체로 변환

        RestTemplate restTemplate=new RestTemplate();  //*2.통신을 위한 RestTemplate 객체 생성
        
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);  
       //3.* 실제로 HTTP 통신을 실행해서, 서버로부터 응답을 받아오는 핵심 코드, String.class->ResponseEntity<String>타입을 밎추기 위해서

            LOGGER.info("status code:{}", responseEntity.getStatusCode());
            LOGGER.info("body: {}", responseEntity.getBody());

            return responseEntity.getBody();
    }

    @Override
    public String getName1(){
        System.out.println("getName1() 메서드 실행");
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name1")  
                .queryParam("name", "Erlia")  //requestParameter->키값과 value값을 넣는다
                .encode()   
                .build()
                .toUri();  

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
       
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2(){
        System.out.println("getName2() 메서드 실행");
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/path-variable/{name}")   //.expand("Erlia")의 erlia를 넣어주게 된다
                .encode()  
                .build()
                .expand("Erlia2")  //복수의 값을 넣어야 할 경우 , 추가하여 구분
                .toUri(); 

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
      
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDto> postDto(){
        System.out.println("postDto() 메서드 실행");
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/member")
                .queryParam("name", "Erlia22")
                .queryParam("email", "erlia22@navre.com")
                .queryParam("group", "Axis22")
                .encode()   
                .build()
                .toUri(); 

        MemberDto memberDTO=new MemberDto();   //RequestBody에 값을 넣기 위해 사용
        memberDTO.setName("erlia22");
        memberDTO.setEmail("aaa22@ssa.com");
        memberDTO.setGroup("Axis22");

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDto> responseEntity=restTemplate.postForEntity(uri, memberDTO, MemberDto.class);
//request값:memberDTO, MemberDTO.class:리턴받는 타입
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }

    @Override
    public ResponseEntity<MemberDto> addHeader(){
        System.out.println("addHeader() 메서드 실행");
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/add-header")
                .encode()   
                .build()
                .toUri(); 

        MemberDto memberDTO=new MemberDto();
        memberDTO.setName("erlia5291");
        memberDTO.setEmail("aaa@ssa.com");
        memberDTO.setGroup("Axis5291");

        RequestEntity<MemberDto> requestEntity=RequestEntity   //여기서 차이점
                .post(uri)   //post메서드를 사용하겠다.
                .header("Axis-Header", "Axis Spring")  //키값과 value값
                .body(memberDTO);
     
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDto> responseEntity=restTemplate.exchange(requestEntity, MemberDto.class);
       
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }
}
