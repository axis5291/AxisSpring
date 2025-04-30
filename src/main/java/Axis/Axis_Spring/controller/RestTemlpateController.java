package Axis.Axis_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Axis.Axis_Spring.data.dto.MemberDto;
import Axis.Axis_Spring.service.RestTemplateService;

//RestTemplateServiceImpl와 메서드가 각각 5개씩 매칭이 되어 있다.
//컨트롤러쪽에서는 RestTemplateServiceImpl의 메서드만 호출하는 구조이다

@RestController
@RequestMapping(value = "/api/rest-template")
public class RestTemlpateController {

    RestTemplateService restTemplateService;

    @Autowired
    public RestTemlpateController(RestTemplateService restTemplateService){
        this.restTemplateService=restTemplateService;
    }

    @GetMapping(value = "/axis")
    public String getAxis() {
        return restTemplateService.getAxis();
    }

    @GetMapping(value = "/name")
    public String getName() {
        return restTemplateService.getName();
    }

    @GetMapping(value = "/name2")
    public String getName2() {
        return restTemplateService.getName2();
    }

    @PostMapping(value = "/dto")
    public ResponseEntity<MemberDto> postDto() {
        return restTemplateService.postDto();
    }

    @PostMapping(value = "/add-header")
    public ResponseEntity<MemberDto> addHeader() {
        return restTemplateService.addHeader();
    }

}
