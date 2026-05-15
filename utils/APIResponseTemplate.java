package MVC.studentmanagement.utils;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public record APIResponseTemplate<T>(
        int status,
        String message,
        LocalDate timeStamp,
        T data
) {
    public T getData() {
        return data;
    }


}
