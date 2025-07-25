package com.guilhermeramos31.springbootjuniorcase.model.base.pagination;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasePaginationDTO {
    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private int page = 1;

    @Min(value = 10, message = "Limit must be greater than or equal to 10")
    private int limit = 10;

    @Pattern(regexp = "ASC|DESC", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Direction must be ASC or DESC")
    private String direction;
}
