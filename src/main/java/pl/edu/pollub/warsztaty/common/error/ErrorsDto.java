package pl.edu.pollub.warsztaty.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorsDto {
    private List<ErrorDto> fieldErrors;
    private List<GlobalErrorDto> globalErrors;
}
