package in.wimsyclimsy.plingo.connection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Pair<F, S> {
    private F first;
    private S second;
}

