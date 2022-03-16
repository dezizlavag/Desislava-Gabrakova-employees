import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Employee {

    private int employeeId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

}
