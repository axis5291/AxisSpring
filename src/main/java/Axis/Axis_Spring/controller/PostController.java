package Axis.Axis_Spring.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Axis.Axis_Spring.data.dto.MemberDTO;
/*
Post API- 리소스를 추가하기 위해 사용되는 API:일반적으로 추가하고 하는 리소스를 http body에 추가하여 서버에 요청, 그렇기 때문에 @ResponseBody를 이용하여 body에 담겨 있는 것을 받아야 함
참고사항:에러번호 400번대는 클라이언트 에러, 500번대 서버쪽 에러
* */
@RestController
@RequestMapping("/api/v1/post-api")   // http://localhost:8080/api/v1/post-api/default  그냥하면 에러, postman으로 테스트할때는 body에 넣어줘야 함
public class PostController {

    @PostMapping(value = "/default")
    public String postMethod(){
        return "Post Hello World";
    }
/*
 아래는 json형태로 바디값에 덧붙여서 전송해보자,  이것을 바탕으로 아래 2개의 메서드를 실행하는 방식이다. 이 Json을 안붙이면 서버에러를 뜻하는 400번대 에러가 발생한다.
        {
          "name":"erlia",
          "email":"erlia@naver.com",
           "group":"axis",
          "sex":"man"
        }
   */
/*
  @RequestBody 어노테이션은 HTTP 요청의 본문(body)에 포함된   JSON, XML 등  기타 포맷의 데이터를 Java 객체로 변환하는 역할을 합니다.
   1. @RequestBody로 받은 JSON 데이터를 Map으로 변환하여 사용
       Map<String, Object> 형태로 받아서  key와 value를 직접 꺼내서 처리함,   어떤 데이터가 들어올지 모를 때 유용  */

    @PostMapping(value = "/member")     // http://localhost:8080/api/v1/post-api/member
    public String postMember(@RequestBody Map<String, Object> postData){  //value값이 어떤게 들어올지 몰라서 Object로 설정
        StringBuilder sb=new StringBuilder();

       // postData.entrySet().forEach(map->{sb.append(map.getKey()+":"+map.getValue()+"\n"); });
        postData.forEach((key, value)->{sb.append(key+":"+value+"\n");});   //자바8부터 가능
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "-> http://localhost:8080/api/v1/post-api/member가 실행되었습니다");

        return sb.toString();
    }
  
/* 2. @RequestBody로 받은 JSON 데이터를 DTO 객체로 변환하여 사용 -> ***실무에서는 이게 더 많이 사용
      미리 정의된 MemberDTO 클래스가 필요, 필드 이름과 JSON key가 일치해야 함,  타입 안정성과 구조화된 데이터 처리에 유리 */
    @PostMapping(value = "/member2")         // http://localhost:8080/api/v1/post-api/member2
    public String postMember2(@RequestBody MemberDTO memberDTO){
        return memberDTO.toString();   //MemberDTO에 @toString() 어노테이션이 붙어있어야 함
    }
   
}
