package Axis.Axis_Spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// 예외가 발생했을 때 예외 처리 흐름을 테스트하기 위한 컨트롤러 클래스, 여기에서는 exception 폴더의 axisSpringExceptionHandler에서 전역적으로 예외를 처리하지만
//exceptionHandler로 지역적으로 설정(전역보다 우선됨)했지만 이게 잘 안되고 있다.
@RestController   // RESTful 웹 서비스 컨트롤러 클래스임을 나타냄
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);  // 로깅을 위한 Logger 객체 생성

    @GetMapping("/hello")    // HTTP GET 요청을 처리하는 엔드포인트
    public String hello(){
        return "Hello World Axis Spring!!";  // 요청에 대해 간단한 문자열 반환
    }

    @PostMapping("/log-test")  // HTTP POST 요청을 처리하는 엔드포인트
    public void logTest() {  
        // 다양한 로그 레벨을 기록
        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");
    }

    @PostMapping("/exception")  // HTTP POST 요청을 처리하는 엔드포인트 (http://localhost:8080/exception)
    public void exceptionTest() throws Exception{  // 예외를 의도적으로 발생시켜서 예외 처리 흐름을 테스트
        LOGGER.info("exceptionTest() 메서드에서 예외 발생");
        throw new Exception("테스트 예외 발생");  // 의도적으로 예외를 발생
    }

    @ExceptionHandler(value = Exception.class)  // 지역 예외 처리기: 해당 클래스 내에서 발생한 모든 Exception을 처리
    public ResponseEntity<Map<String, String>> exceptionHandler(Exception e) { 
        // 예외가 @ExceptionHandler 메서드에 정상적으로 전달되는지 확인하는 로그
        LOGGER.info("HelloController Class 예외가 ******* 지역 얘외처리 *******처리됨 ");
        LOGGER.error("예외 발생: ", e);  // 예외 객체 출력 (스택 트레이스 포함)

        // 응답 헤더와 상태 코드 설정
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        // 응답 데이터 맵 작성
        Map<String, String> map = new HashMap<>();
        map.put("Error Type:", httpStatus.getReasonPhrase());  // 에러 타입 (Bad Request)
        map.put("Code", "404");  // 에러 코드
        map.put("Message", e.getMessage());  // 예외 메시지 (예외에서 전달된 메시지)

        // ResponseEntity 객체 생성 후 반환 (응답 본문, 헤더, 상태 코드 포함)
        ResponseEntity<Map<String, String>> re = new ResponseEntity<>(map, responseHeaders, httpStatus);
        LOGGER.info("ResponseEntity의 내용: " + re);
        return re;  // 클라이언트에 에러 정보 반환
    }
}
