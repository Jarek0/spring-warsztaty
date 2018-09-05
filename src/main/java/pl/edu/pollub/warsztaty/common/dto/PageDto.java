package pl.edu.pollub.warsztaty.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {

    int number;

    int total;

    List<T> content;
}
