package it.polimi.ingsw.is24am14.server.utils;


import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;

/**
 * This class is used to create the deck of resource cards.
 */

public class ResourceCardDeckCreator {

    public Deck<ResourceCard> createResourceCardDeck() {
        ArrayList<ResourceCard> content = new ArrayList<>();
        Deck<ResourceCard> resourceCardDeck = new Deck<>(content);
        ArrayList<Corner> frontCorners = new ArrayList<>();
        ArrayList<Corner> backCorners = new ArrayList<>();
        CornerEnum.ResourceEnum resource = CornerEnum.ResourceEnum.FUNGI;
        int points=0;
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));



    // Red Cards

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_1.png", "src/main/resources/images/cards/resource_cards/red_backs/page_1.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_2.png", "src/main/resources/images/cards/resource_cards/red_backs/page_2.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_3.png", "src/main/resources/images/cards/resource_cards/red_backs/page_3.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_4.png", "src/main/resources/images/cards/resource_cards/red_backs/page_4.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_5.png", "src/main/resources/images/cards/resource_cards/red_backs/page_5.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_6.png", "src/main/resources/images/cards/resource_cards/red_backs/page_6.png"));

        frontCorners.clear();



        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_7.png", "src/main/resources/images/cards/resource_cards/red_backs/page_7.png"));

        frontCorners.clear();



        points=1;


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_8.png", "src/main/resources/images/cards/resource_cards/red_backs/page_8.png"));

        frontCorners.clear();




        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_9.png", "src/main/resources/images/cards/resource_cards/red_backs/page_9.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/red_fronts/page_10.png", "src/main/resources/images/cards/resource_cards/red_backs/page_10.png"));

        frontCorners.clear();

    // Green Cards

        points=0;
        resource= CornerEnum.ResourceEnum.PLANT;


        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_11.png", "src/main/resources/images/cards/resource_cards/green_backs/page_11.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_12.png", "src/main/resources/images/cards/resource_cards/green_backs/page_12.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_13.png", "src/main/resources/images/cards/resource_cards/green_backs/page_13.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_14.png", "src/main/resources/images/cards/resource_cards/green_backs/page_14.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_15.png", "src/main/resources/images/cards/resource_cards/green_backs/page_15.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_16.png", "src/main/resources/images/cards/resource_cards/green_backs/page_16.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_17.png", "src/main/resources/images/cards/resource_cards/green_backs/page_17.png"));

        frontCorners.clear();

        points=1;

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_18.png", "src/main/resources/images/cards/resource_cards/green_backs/page_18.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_19.png", "src/main/resources/images/cards/resource_cards/green_backs/page_19.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/green_fronts/page_20.png", "src/main/resources/images/cards/resource_cards/green_backs/page_20.png"));

        frontCorners.clear();

        // Blue Cards

        points=0;
        resource= CornerEnum.ResourceEnum.ANIMAL;

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_21.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_21.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_22.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_22.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_23.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_23.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_24.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_24.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_25.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_25.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_26.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_26.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_21.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_21.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_27.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_27.png"));

        frontCorners.clear();

        points=1;

        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_28.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_28.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_29.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_29.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/blue_fronts/page_30.png", "src/main/resources/images/cards/resource_cards/blue_backs/page_30.png"));

        frontCorners.clear();

    // Purple Cards

        resource = CornerEnum.ResourceEnum.INSECT;
        points =0;

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_31.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_31.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_32.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_32.png"));

        frontCorners.clear();

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_33.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_33.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_34.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_34.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_35.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_35.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_36.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_36.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        frontCorners.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_37.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_37.png"));

        frontCorners.clear();

        points=1;

        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_38.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_38.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_39.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_39.png"));

        frontCorners.clear();


        frontCorners.add(new Corner(CornerEnum.HIDDEN));
        frontCorners.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        frontCorners.add(new Corner(CornerEnum.EMPTY));
        frontCorners.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(points, resource, frontCorners, backCorners, "src/main/resources/images/cards/resource_cards/purple_fronts/page_40.png", "src/main/resources/images/cards/resource_cards/purple_backs/page_40.png"));


        return resourceCardDeck;
    }
}
