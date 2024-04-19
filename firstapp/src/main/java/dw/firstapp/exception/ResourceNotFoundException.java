package dw.firstapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // 예외코드를 적합하게 줘야 됨
//@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ResourceNotFoundException extends RuntimeException {
    // Service 에서 예외를 던지면 Controller 가 아니라 Exception 에서 처리
    // 실패의 경우에만 처리
    // 보통 게터만 만듦

    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
