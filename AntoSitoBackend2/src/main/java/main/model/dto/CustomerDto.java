package main.model.dto;

import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {

    private String name;

    private String email;

    private String message;

}
