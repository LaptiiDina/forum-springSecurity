package telran.b7a.forum.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import telran.b7a.configuration.Constants;

@Getter
public class DatePeriodDto {

	@JsonFormat(pattern = Constants.DATE_FORMAT)
	LocalDate dateFrom;
	@JsonFormat(pattern = Constants.DATE_FORMAT)
	LocalDate dateTo;
}
