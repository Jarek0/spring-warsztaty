package pl.edu.pollub.warsztaty.common.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class GlobalErrorDto {
    private String message;
}
