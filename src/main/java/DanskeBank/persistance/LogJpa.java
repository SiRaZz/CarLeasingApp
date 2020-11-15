package DanskeBank.persistance;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LOGS")
public class LogJpa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "REQUEST_DATE")
    private Date requestDate;

    @Column(name = "METHOD")
    private String method;

    @Column(name = "URL")
    private String url;

    @Column(name = "RESPONSE_STATUS")
    private Integer responseStatus;


}
