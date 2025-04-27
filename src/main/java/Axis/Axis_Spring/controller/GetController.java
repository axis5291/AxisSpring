package Axis.Axis_Spring.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Axis.Axis_Spring.data.dto.MemberDTO;

/*@RestController:@Controller와 @ResponseBody가 합쳐진 어노테이션, 반환하려는 주류는 JSON 형태의 객체 데이터다.
REST API를 개발할 때 주로 사용하며 마찬가지로 ResponseEntity로 감싸서 주로 반환한다.*/

@RestController
@RequestMapping("api/v1/get-api")  //버전관리를 위해 V1이라고 표시...공통되는 url을 사용하기 위해서 사용, 밑에 각 @GetMapping의 상위페이지
public class GetController {

    @GetMapping(value = "/name")   //http://localhost:8080/api/v1/get-api/name
    public String getName(){     
        return "Erlia";
    }
  
    @GetMapping(value="/variable1/{variable}")   //  http://localhost:8080/api/v1/get-api/variable1/안녕하세요  ->이렇게 해볼것
    public String getVaiable1(@PathVariable String variable){     
        return variable;
    }
    //@PathVariable:Get형식 요청에서 파라미터를 전달하기 위해 URL에 값을 담아 전달하는 방법, {"variable"}의 이름과 String "variable"와 같이 이름을 일치시켜야 함

    @GetMapping(value="variable2/{variable}")   //http://localhost:8080/api/v1/get-api/variable2/hello
    public String getVaiable2(@PathVariable("variable") String var){  //{variable}를 var로 변수의 이름을 바꿔서 받음
        return var;
    }
    // {"variable"}의 이름과 String "variable"와 같이 이름을 일치시킬수 없을 때 쓰는 방식

    // http://localhost:8080/api/v1/get-api/variable1/{String 값}

/*
    아래의 것은
    '?'를 기준으로 우측에 {키}={값}의 형태로 전달되며, 복수 형태로 전달할 경우 &를 사용하는
    방식을 말한다.
*/

    @GetMapping(value = "request1")   // http://localhost:8080/api/v1/get-api/request1?name=erlia&email=erlia@naver.com&group=axis 
    public String getRequestParam(@RequestParam String name,  
                                  @RequestParam String email,
                                  @RequestParam String group){
        return "이름:"+name+", 이메일:"+email+", 조직:"+group;
    }
    //@RequestParam :Get 형식의 요청에서 쿼리 문자열을 전달하기 위해 사용하는 방법  '?'를 기준으로 우측에 {키}={값}의 형태로 전달되며, 복수 형태로 전달할 경우 &를 사용함
   
    @GetMapping(value = "request2")  //이방법은 입력것이 url에 무작위로 들어올때 쓰는 방법
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();
       //param.entrySet().forEach(map -> {sb.append(map.getKey() + ":" + map.getValue() + "\n"); });  set으로 받아서 반복문을 돌려서 출력하는 방법
         param.forEach((key, value) -> {  //Map 자체에 바로 forEach를 쓰는 방법: map을 받아서 key와 value를 각각 받아서 반복문을 돌려서 출력하는 방법, 자바8부터 가능
            sb.append(key + ":" + value + "\n");
        });
        return sb.toString();   //스트링빌더를 스트링으로 변환해서 출력
    }
    //위 예시 코드는 어떤 요청값이 뭐가 들어올지 모를 경우 사용하는 방식
    //1. http://localhost:8080/api/v1/get-api/request2?name=마&email=엑시스@naver.com&group=하하하&sex=남자
    //2. http://localhost:8080/api/v1/get-api/request2?성별=골라보자
    //1.2번과 같이 아무렇게나 키=값 형태로 갯수와 상관없이 넘겨주면 그대로 출력된다.


    //키와 값이 정해져 있지만 받아야할 파라미터가 많을 경우 DTO 객체를 이용하는 방식
    @GetMapping(value = "request3")     //  http://localhost:8080/api/v1/get-api/request3?name=미현&email=엑시스@naver.com&group=하하하&sex=여자
    public String getRequestParam3(MemberDTO memberDTO){
       // return memberDTO.getName()+""+memberDTO.getEmail()+""+memberDTO.getGroup();  이런식으로 호출할수도 있다.
        return memberDTO.toString();  //MembrDTO의 @toString이 있어서 가능   
    }
    // 
    // DTO클래스의 멤버변수들에 각 파라미터가 대응된다.
}
