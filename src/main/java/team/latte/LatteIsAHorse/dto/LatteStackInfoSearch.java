package team.latte.LatteIsAHorse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LatteStackInfoSearch {

    @NotNull
    @Min(2021) @Max(2100)
    private int year;

    @NotNull
    @Min(1) @Max(12)
    private int month;
}
