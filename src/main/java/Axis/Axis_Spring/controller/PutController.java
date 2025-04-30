package Axis.Axis_Spring.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Axis.Axis_Spring.data.dto.MemberDto;

/*Put API:해당 리소스가 있으면 갱신하고, 없으면 새로 생성한다. 업데이트를 위한 메서드, 기본적인 동작 방식은 Post API와 동일 */
@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @PutMapping(value = "/default")
    public String putMethod(){
        return "Put Melthod";
    }

    @PutMapping(value = "/member")
    public String postMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb=new StringBuilder();
        putData.entrySet().forEach(map->{
            sb.append(map.getKey()+":"+map.getValue()+"\n");
        }
        );
        return sb.toString();
    }

    //아래 3가지가 핵심포인트
    //1.toString을 이용하는 방법..결과값 모양이 달라진다.
    @PutMapping(value = "/member1")
    public String postMemberDto1(@RequestBody MemberDto memberDTO){
        return memberDTO.toString();
    }
    //http://localhost:8080/api/v1/put-api/member1

    //2.Json형식 그대로 보여줌..일반적으로 클라이언트쪽에서도 제이슨 형식을 사용하므로  이방법을 더 선호
    @PutMapping(value = "/member2")
    public MemberDto postMemberDto2(@RequestBody MemberDto memberDTO){
        return memberDTO;
    }
    //http://localhost:8080/api/v1/put-api/member2

    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDto> postMemberDto3(@RequestBody MemberDto memberDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
        //HttpStatus.ACCEPTED->실행하면 코드값이 202임을 확인할 수 있다.
        // (ACCEPTED를 눌러 링크로 들어가 확인 하면 각종 enum을 확인가능
        //body(memberDTO) ->위의 return memberDTO;와 동일한 효과를 낸다.
    }
    //http://localhost:8080/api/v1/put-api/member3
   /*   Response Entity는 HttpEntity를 상속받아 구현한 클래스이다. public class ResponseEntity<T> extends HttpEntity<T>
        HttpEntity는 HTTP 요청 혹은 응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스이다.
        ResponseEnitity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다.
        따라서 HttpStatus, HttpHeaders, HttpBody를 포함한다.*/

}
