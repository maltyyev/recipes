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
public class Notes2NotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {

        final NotesCommand notes = new NotesCommand();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;

    }
}
