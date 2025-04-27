package Axis.Axis_Spring.service.impl;

import Axis.Axis_Spring.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
    public String getAxis();
    public String getName();
    public String getName2();

    public ResponseEntity<MemberDTO> postDto();
    public ResponseEntity<MemberDTO> addHeader();

}
