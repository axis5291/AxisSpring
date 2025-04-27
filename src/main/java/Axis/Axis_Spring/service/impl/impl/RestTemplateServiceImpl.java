package Axis.Axis_Spring.service.impl.impl;

import Axis.Axis_Spring.data.dto.MemberDTO;
import Axis.Axis_Spring.service.impl.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
//이서비스를 사용할려면 AxisServerBox의 별도의 springApp를 사용하여 구동시켜야 한다.
//AxisServerBox에서 요청에 대한 응답을 하는 별도의 서버이다.
@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER= LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getAxis(){
        URI uri= UriComponentsBuilder   //어떤 경로로 요청을 할건지...
                .fromUriString("http://localhost:9090")
                .path("/api/server/jong-ho")   //뒤에 붙는 경로
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
            //String.class->ResponseEntity<String>타입을 밎추기 위해서
            LOGGER.info("status code:{}", responseEntity.getStatusCode());
            LOGGER.info("body: {}", responseEntity.getBody());

            return responseEntity.getBody();
    }

    @Override
    public String getName(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/name")   //뒤에 붙는 경로
                .queryParam("name", "Erlia")  //requestParameter->키값과 value값을 넣는다
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
        //String.class->ResponseEntity<String>타입을 밎추기 위해서
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/path-variable/{name}")   //.expand("Erlia")의 erlia를 넣어주게 된다
                .encode()    //UTF-8로 인코딩
                .build()
                .expand("Erlia2")  //복수의 값을 넣어야 할 경우 , 추가하여 구분
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity=restTemplate.getForEntity(uri, String.class);
        //String.class->ResponseEntity<String>타입을 밎추기 위해서
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/member")
                .queryParam("name", "Erlia22")
                .queryParam("email", "erlia22@navre.com")
                .queryParam("group", "Axis22")
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        MemberDTO memberDTO=new MemberDTO();   //RequestBody에 값을 넣기 위해 사용
        memberDTO.setName("erlia22");
        memberDTO.setEmail("aaa22@ssa.com");
        memberDTO.setGroup("Axis22");

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity=restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);
//request값:memberDTO, MemberDTO.class:리턴받는 타입
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }

    @Override
    public ResponseEntity<MemberDTO> addHeader(){
        URI uri= UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/add-header")
                .encode()    //UTF-8로 인코딩
                .build()
                .toUri();  //위에 build()로 반환이 component로 됨으로 uri로 변경

        MemberDTO memberDTO=new MemberDTO();
        memberDTO.setName("erlia5291");
        memberDTO.setEmail("aaa@ssa.com");
        memberDTO.setGroup("Axis5291");

        RequestEntity<MemberDTO> requestEntity=RequestEntity   //여기서 차이점
                .post(uri)   //post메서드를 사용하겠다.
                .header("Axis-Header", "Axis Spring")  //키값과 value값
                .body(memberDTO);
        //requestEntity에는 post방식, header, body값이 들어가 있다.

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity=restTemplate.exchange(requestEntity, MemberDTO.class);
        //MemberDTO.class->response받을 타입만 설정
        LOGGER.info("status code:{}", responseEntity.getStatusCode());
        LOGGER.info("body: {}", responseEntity.getBody());

        return responseEntity;

    }
}
