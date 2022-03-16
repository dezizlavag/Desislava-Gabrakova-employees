import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Employee implements Comparable<Employee>{

    private int employeeId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    @Override
    public int compareTo(Employee object) {

        if (Objects.equals(projectId, object.projectId)) {
            return 1;
        }
        else return -1;
    }
}
