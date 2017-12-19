package my.domain.converters;

import lombok.Synchronized;
import my.domain.commands.NotesCommand;
import my.domain.models.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by maltyyev on 20.12.17
 */
@Component
public class NotesCommand2Notes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;

    }
}
