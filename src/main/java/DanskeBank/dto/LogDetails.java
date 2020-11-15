package DanskeBank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDetails {


    private Long id;

    private Date requestDate;

    private String method;

    private String url;

    private Integer responseStatus;

}
