package DanskeBank.component;

import DanskeBank.persistance.LogJpa;
import DanskeBank.repository.LoggingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class LoggingComponent implements HttpTraceRepository  {

    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

    @Autowired
    private LoggingRepository loggingRepository;

    @Override
    public List<HttpTrace> findAll() {
        return Collections.singletonList(lastTrace.get());
    }

    @Override
    public void add(HttpTrace trace) {
        if (!trace.getRequest().getUri().toString().contains("h2-console")) {
            LogJpa logJpa = LogJpa.builder().requestDate(Date.from(trace.getTimestamp()))
                    .method(trace.getRequest().getMethod())
                    .responseStatus(trace.getResponse().getStatus()).url(trace.getRequest().getUri().toString()).build();
            loggingRepository.save(logJpa);
        }
    }
}
