package my.domain.services;

import my.domain.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * Created by maltyyev on 17.01.18 22:33
 */
public interface UomService {

    Set<UnitOfMeasureCommand> listAll();
}
