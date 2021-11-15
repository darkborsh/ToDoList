package parser;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommandDescription {
    private final String name;
    private final long id;
    private final String text;
}
