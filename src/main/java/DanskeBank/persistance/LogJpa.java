package DanskeBank.persistance;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
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
