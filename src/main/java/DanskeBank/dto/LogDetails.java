package DanskeBank.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LogDetails {


    private Long id;

    private Date requestDate;

    private String method;

    private String url;

    private Integer responseStatus;

}
