package bauernhof.app.card;
import java.util.Set;

import bauernhof.preset.Either;
import bauernhof.preset.card.CardColor;
import bauernhof.preset.card.Effect;

public interface BeEf extends Effect {
    
    @Override
    public Set<Either<BeCa,CardColor>> getSelector();
}
