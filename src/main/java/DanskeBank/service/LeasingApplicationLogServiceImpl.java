package DanskeBank.service;

import DanskeBank.dto.LogDetails;
import DanskeBank.repository.LoggingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeasingApplicationLogServiceImpl implements LeasingApplicationLogService {

    @Autowired
    private LoggingRepository loggingRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<LogDetails> getLogs() {
        var details = loggingRepository.findAll();
        return details
                .stream()
                .map(d -> modelMapper.map(d, LogDetails.class))
                .collect(Collectors.toList());
    }
}
