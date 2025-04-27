package Axis.Axis_Spring.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Delete API
서버를 통해서 리소스를 삭제하는 API
@PathVariable을 통해 리소스ID를 받아 처리. 예)회원탈퇴를 ID를 통해 삭제 */

@RestController
@RequestMapping("/api/v1/get-api")
public class DeleteController {

    @DeleteMapping(value ="/delete/{variable}")   //{variable} =String variable 을 같게...
    public String DeleteVariable(@PathVariable String variable){
        return "'"+variable+"'가 삭제되었습니다.";
    }
    //http://localhost:8080/api/v1/get-api/delete/erlia    method=delete로 설정후 진행
    //원래는 {variable}으로 디비에서 조회를 하고 처리되었다는 결과 메세지를
    // return variable 자리에 보여주는 것이다.
}
