package pl.edu.pollub.warsztaty.userAccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAndCityDto {

    private String login;

    private String city;
}
