package it.polimi.ingsw.is24am14.server.utils.GSONAdapters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.is24am14.server.model.card.*;
import it.polimi.ingsw.is24am14.server.model.game.GameArea;
import it.polimi.ingsw.is24am14.server.model.player.Player;

public class InitGSON {
    public static Gson init() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Condition.class, new ConditionAdapter());
        builder.registerTypeAdapter(CardCondition.class, new CardConditionSerializer());
        builder.registerTypeAdapter(GameArea.class, new GameAreaSerializer());
        builder.registerTypeAdapter(Card.class, new CardTypeAdapter());
        builder.registerTypeAdapter(CornerEnum.class, new CornerEnumTypeAdapter());
        builder.registerTypeAdapter(GoldCard.class, new GoldCardAdapter());
        builder.registerTypeAdapter(ResourceCard.class, new ResourceCardAdapter());
        builder.registerTypeAdapter(StarterCard.class, new StarterCardAdapter());
        builder.registerTypeAdapter(PlayableCard.class, new PlayableCardAdapter());
        builder.registerTypeAdapter(CornerCondition.class, new CornerConditionAdapter());
        return builder.create();
    }
}
