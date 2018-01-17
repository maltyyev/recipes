package my.domain.services.impl;

import my.domain.commands.UnitOfMeasureCommand;
import my.domain.converters.UnitOfMeasure2UnitOfMeasureCommand;
import my.domain.repositories.UnitOfMeasureRepository;
import my.domain.services.UomService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by maltyyev on 17.01.18 22:33
 */
@Service
public class UomServiceImpl implements UomService {

    UnitOfMeasureRepository uomRepository;
    UnitOfMeasure2UnitOfMeasureCommand unitOfMeasure2UnitOfMeasureCommand;

    public UomServiceImpl(UnitOfMeasureRepository uomRepository,
                          UnitOfMeasure2UnitOfMeasureCommand unitOfMeasure2UnitOfMeasureCommand) {
        this.uomRepository = uomRepository;
        this.unitOfMeasure2UnitOfMeasureCommand = unitOfMeasure2UnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAll() {
        return StreamSupport.stream(uomRepository.findAll()
                .spliterator(), false)
                .map(unitOfMeasure2UnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
