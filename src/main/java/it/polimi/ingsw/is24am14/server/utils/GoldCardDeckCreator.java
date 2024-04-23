package it.polimi.ingsw.is24am14.server.utils;

import it.polimi.ingsw.is24am14.server.model.card.*;

import java.util.ArrayList;

/**
 * This class is used to create the deck of Gold Cards
 */

public class GoldCardDeckCreator {

    public Deck<GoldCard> createGoldCardDeck() {

        ArrayList<GoldCard> content = new ArrayList<>();
        Deck<GoldCard> goldCardDeck = new Deck<>(content);

        // placeholders
        CornerCondition cornerCondition = new CornerCondition();
        Condition noCondition = null;

        ObjectCondition quillCondition = new ObjectCondition();
        quillCondition.addClause(CornerEnum.ObjectEnum.QUILL);
        ObjectCondition manuscriptCondition = new ObjectCondition();
        manuscriptCondition.addClause(CornerEnum.ObjectEnum.MANUSCRIPT);
        ObjectCondition inkwellCondition = new ObjectCondition();
        inkwellCondition.addClause(CornerEnum.ObjectEnum.INKWELL);


        // Red cards


        ArrayList<Corner> hidEmpEmpQui = new ArrayList<>();
        hidEmpEmpQui.add(new Corner(CornerEnum.HIDDEN));
        hidEmpEmpQui.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpQui.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpQui.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        ResourceCondition twoFungiOneAnimal = new ResourceCondition();
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        content.add(new GoldCard(1, quillCondition, CornerEnum.ResourceEnum.FUNGI, twoFungiOneAnimal, hidEmpEmpQui, "src/main/resources/images/cards/gold_cards/red_fronts/page_41.png", "src/main/resources/images/cards/gold_cards/red_backs/page_41.png"));
        

        ArrayList<Corner> empInkHidEmp = new ArrayList<>();
        empInkHidEmp.add(new Corner(CornerEnum.EMPTY));
        empInkHidEmp.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        empInkHidEmp.add(new Corner(CornerEnum.HIDDEN));
        empInkHidEmp.add(new Corner(CornerEnum.EMPTY));
        ResourceCondition twoFungiOnePlant = new ResourceCondition();
        twoFungiOnePlant.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOnePlant.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        content.add(new GoldCard(1, inkwellCondition, CornerEnum.ResourceEnum.FUNGI, twoFungiOnePlant, empInkHidEmp, "src/main/resources/images/cards/gold_cards/red_fronts/page_42.png", "src/main/resources/images/cards/gold_cards/red_backs/page_42.png"));
        

        ArrayList<Corner> manEmpEmpHid = new ArrayList<>();
        manEmpEmpHid.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        manEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        manEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        manEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        ResourceCondition twoFungiOneInsect = new ResourceCondition();
        twoFungiOneInsect.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneInsect.addClause(CornerEnum.ResourceEnum.FUNGI);
        twoFungiOneInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        content.add(new GoldCard(1, manuscriptCondition, CornerEnum.ResourceEnum.FUNGI, twoFungiOneInsect, manEmpEmpHid, "src/main/resources/images/cards/gold_cards/red_fronts/page_43.png", "src/main/resources/images/cards/gold_cards/red_backs/page_43.png"));
        


        ArrayList<Corner> empEmpHidEmp = new ArrayList<>();
        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        empEmpHidEmp.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        ResourceCondition threeFungiOneAnimal = new ResourceCondition();
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungiOneAnimal, empEmpHidEmp, "src/main/resources/images/cards/gold_cards/red_fronts/page_44.png", "src/main/resources/images/cards/gold_cards/red_backs/page_44.png"));
        

        ArrayList<Corner> empEmpEmpHid = new ArrayList<>();
        empEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        ResourceCondition threeFungiOnePlant = new ResourceCondition();
        threeFungiOnePlant.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOnePlant.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOnePlant.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungiOnePlant, empEmpEmpHid, "src/main/resources/images/cards/gold_cards/red_fronts/page_45.png", "src/main/resources/images/cards/gold_cards/red_backs/page_45.png"));
        

        ArrayList<Corner> empHidEmpEmp = new ArrayList<>();
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empHidEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        empHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        ResourceCondition threeFungiOneInsect = new ResourceCondition();
        threeFungiOneInsect.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneInsect.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneInsect.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungiOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungiOneInsect, empHidEmpEmp, "src/main/resources/images/cards/gold_cards/red_fronts/page_46.png", "src/main/resources/images/cards/gold_cards/red_backs/page_46.png"));
        



        ArrayList<Corner> empHidInkHid = new ArrayList<>();
        empHidInkHid.add(new Corner(CornerEnum.EMPTY));
        empHidInkHid.add(new Corner(CornerEnum.HIDDEN));
        empHidInkHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        empHidInkHid.add(new Corner(CornerEnum.HIDDEN));

        ResourceCondition threeFungi = new ResourceCondition();
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        threeFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        content.add(new GoldCard(3, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungi, empHidInkHid, "src/main/resources/images/cards/gold_cards/red_fronts/page_47.png", "src/main/resources/images/cards/gold_cards/red_backs/page_47.png"));
        

        ArrayList<Corner> inkEmpHidHid = new ArrayList<>();
        inkEmpHidHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        inkEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        inkEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        inkEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungi, inkEmpHidHid, "src/main/resources/images/cards/gold_cards/red_fronts/page_48.png", "src/main/resources/images/cards/gold_cards/red_backs/page_48.png"));


        ArrayList<Corner> hidManHidEmp = new ArrayList<>();
        hidManHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidManHidEmp.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        hidManHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidManHidEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(3, cornerCondition, CornerEnum.ResourceEnum.FUNGI, threeFungi, hidManHidEmp, "src/main/resources/images/cards/gold_cards/red_fronts/page_49.png", "src/main/resources/images/cards/gold_cards/red_backs/page_49.png"));
        


        ResourceCondition fiveFungi = new ResourceCondition();
        fiveFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        fiveFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        fiveFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        fiveFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        fiveFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        ArrayList<Corner> empHidEmpHid = new ArrayList<>();
        empHidEmpHid.add(new Corner(CornerEnum.EMPTY));
        empHidEmpHid.add(new Corner(CornerEnum.HIDDEN));
        empHidEmpHid.add(new Corner(CornerEnum.EMPTY));
        empHidEmpHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(5, noCondition, CornerEnum.ResourceEnum.FUNGI, fiveFungi, empHidEmpHid, "src/main/resources/images/cards/gold_cards/red_fronts/page_50.png", "src/main/resources/images/cards/gold_cards/red_backs/page_50.png"));
        

        // Green cards


        ResourceCondition twoPlantOneInsect = new ResourceCondition();
        twoPlantOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        ArrayList<Corner> quiEmpEmpHid = new ArrayList<>();
        quiEmpEmpHid.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        quiEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        quiEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        quiEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(1, quillCondition, CornerEnum.ResourceEnum.PLANT, twoPlantOneInsect, quiEmpEmpHid, "src/main/resources/images/cards/gold_cards/green_fronts/page_51.png", "src/main/resources/images/cards/gold_cards/green_backs/page_51.png"));
        

        ResourceCondition twoPlantOneFungi = new ResourceCondition();
        twoPlantOneFungi.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneFungi.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);
        ArrayList<Corner> empManHidEmp = new ArrayList<>();
        empManHidEmp.add(new Corner(CornerEnum.EMPTY));
        empManHidEmp.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        empManHidEmp.add(new Corner(CornerEnum.HIDDEN));
        empManHidEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(1, manuscriptCondition, CornerEnum.ResourceEnum.PLANT, twoPlantOneFungi, empManHidEmp, "src/main/resources/images/cards/gold_cards/green_fronts/page_52.png", "src/main/resources/images/cards/gold_cards/green_backs/page_52.png"));
        

        ResourceCondition twoPlantOneAnimal = new ResourceCondition();
        twoPlantOneAnimal.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneAnimal.addClause(CornerEnum.ResourceEnum.PLANT);
        twoPlantOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        ArrayList<Corner> empHidInkEmp = new ArrayList<>();
        empHidInkEmp.add(new Corner(CornerEnum.EMPTY));
        empHidInkEmp.add(new Corner(CornerEnum.HIDDEN));
        empHidInkEmp.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        empHidInkEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(1, inkwellCondition, CornerEnum.ResourceEnum.PLANT, twoPlantOneAnimal, empHidInkEmp, "src/main/resources/images/cards/gold_cards/green_fronts/page_53.png", "src/main/resources/images/cards/gold_cards/green_backs/page_53.png"));
        


        ResourceCondition threePlantOneInsect = new ResourceCondition();
        threePlantOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneInsect.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        ArrayList<Corner> hidEmpEmpEmp = new ArrayList<>();
        hidEmpEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        hidEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.PLANT, threePlantOneInsect, hidEmpEmpEmp, "src/main/resources/images/cards/gold_cards/green_fronts/page_54.png", "src/main/resources/images/cards/gold_cards/green_backs/page_54.png"));
        

        ResourceCondition threePlantOneAnimal = new ResourceCondition();
        threePlantOneAnimal.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneAnimal.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneAnimal.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.PLANT, threePlantOneAnimal, empEmpEmpHid, "src/main/resources/images/cards/gold_cards/green_fronts/page_55.png", "src/main/resources/images/cards/gold_cards/green_backs/page_55.png"));
        

        ResourceCondition threePlantOneFungi = new ResourceCondition();
        threePlantOneFungi.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneFungi.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneFungi.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlantOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.PLANT, threePlantOneFungi, empHidEmpEmp, "src/main/resources/images/cards/gold_cards/green_fronts/page_56.png", "src/main/resources/images/cards/gold_cards/green_backs/page_56.png"));
        



        ResourceCondition threePlant = new ResourceCondition();
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        threePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        ArrayList<Corner> empHidQuiHid = new ArrayList<>();
        empHidQuiHid.add(new Corner(CornerEnum.EMPTY));
        empHidQuiHid.add(new Corner(CornerEnum.HIDDEN));
        empHidQuiHid.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        empHidQuiHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.PLANT, threePlant, empHidQuiHid, "src/main/resources/images/cards/gold_cards/green_fronts/page_57.png", "src/main/resources/images/cards/gold_cards/green_backs/page_57.png"));
        


        ArrayList<Corner> manEmpHidHid = new ArrayList<>();
        manEmpHidHid.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        manEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        manEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        manEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.PLANT, threePlant, manEmpHidHid, "src/main/resources/images/cards/gold_cards/green_fronts/page_58.png", "src/main/resources/images/cards/gold_cards/green_backs/page_58.png"));
        

        ArrayList<Corner> hidInkHidEmp = new ArrayList<>();
        hidInkHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidInkHidEmp.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        hidInkHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidInkHidEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.PLANT, threePlant, hidInkHidEmp, "src/main/resources/images/cards/gold_cards/green_fronts/page_59.png", "src/main/resources/images/cards/gold_cards/green_backs/page_59.png"));
        


        ResourceCondition fivePlant = new ResourceCondition();
        fivePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        fivePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        fivePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        fivePlant.addClause(CornerEnum.ResourceEnum.PLANT);
        fivePlant.addClause(CornerEnum.ResourceEnum.PLANT);

        content.add(new GoldCard(5, noCondition, CornerEnum.ResourceEnum.PLANT, fivePlant, manEmpHidHid, "src/main/resources/images/cards/gold_cards/green_fronts/page_60.png", "src/main/resources/images/cards/gold_cards/green_backs/page_60.png"));
        


        // Blue Cards



        ResourceCondition twoAnimalOneInsect = new ResourceCondition();
        twoAnimalOneInsect.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneInsect.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneInsect.addClause(CornerEnum.ResourceEnum.INSECT);

        ArrayList<Corner> inkEmpEmpHid = new ArrayList<>();
        inkEmpEmpHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        inkEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        inkEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        inkEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(1, inkwellCondition, CornerEnum.ResourceEnum.ANIMAL, twoAnimalOneInsect, inkEmpEmpHid, "src/main/resources/images/cards/gold_cards/blue_fronts/page_61.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_61.png"));


        ResourceCondition twoAnimalOnePlant = new ResourceCondition();
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);

        ArrayList<Corner> hidEmpEmpMan = new ArrayList<>();
        hidEmpEmpMan.add(new Corner(CornerEnum.HIDDEN));
        hidEmpEmpMan.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpMan.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpMan.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        content.add(new GoldCard(1, manuscriptCondition, CornerEnum.ResourceEnum.ANIMAL, twoAnimalOnePlant, hidEmpEmpMan, "src/main/resources/images/cards/gold_cards/blue_fronts/page_62.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_62.png"));


        ResourceCondition twoAnimalOneFungi = new ResourceCondition();
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);

        content.add(new GoldCard(1, inkwellCondition, CornerEnum.ResourceEnum.ANIMAL, twoAnimalOneFungi, empHidInkEmp, "src/main/resources/images/cards/gold_cards/blue_fronts/page_63.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_63.png"));
        


        ResourceCondition threeAnimalOneInsect = new ResourceCondition();
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);


        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimalOneInsect, empEmpHidEmp, "src/main/resources/images/cards/gold_cards/blue_fronts/page_64.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_64.png"));
        

        ResourceCondition threeAnimalOneFungi = new ResourceCondition();
        threeAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimalOneFungi.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimalOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);


        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimalOneFungi, empHidEmpEmp, "src/main/resources/images/cards/gold_cards/blue_fronts/page_65.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_65.png"));
        


        ResourceCondition threeAnimalOnePlant = new ResourceCondition();
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);
        twoAnimalOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimalOnePlant, hidEmpEmpEmp, "src/main/resources/images/cards/gold_cards/blue_fronts/page_66.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_66.png"));
        



        ResourceCondition threeAnimal= new ResourceCondition();
        threeAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimalOneInsect.addClause(CornerEnum.ResourceEnum.ANIMAL);
        threeAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);


        ArrayList<Corner> empHidManHid = new ArrayList<>();
        empHidManHid.add(new Corner(CornerEnum.EMPTY));
        empHidManHid.add(new Corner(CornerEnum.HIDDEN));
        empHidManHid.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        empHidManHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimal, empHidManHid, "src/main/resources/images/cards/gold_cards/blue_fronts/page_67.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_67.png"));

        ArrayList<Corner> empInkHidHid = new ArrayList<>();
        empEmpEmpHid.add(new Corner(CornerEnum.EMPTY));
        empEmpEmpHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        empEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        empEmpEmpHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimal, empInkHidHid, "src/main/resources/images/cards/gold_cards/blue_fronts/page_68.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_68.png"));

        ArrayList<Corner> hidEmpHidQui = new ArrayList<>();
        hidEmpHidQui.add(new Corner(CornerEnum.EMPTY));
        hidEmpHidQui.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        hidEmpHidQui.add(new Corner(CornerEnum.HIDDEN));
        hidEmpHidQui.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.ANIMAL, threeAnimal, hidEmpHidQui, "src/main/resources/images/cards/gold_cards/blue_fronts/page_69.png", "src/main/resources/images/cards/gold_cards/blue_backs/page_69.png"));


        ResourceCondition fiveAnimal = new ResourceCondition();
        fiveAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        fiveAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        fiveAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        fiveAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);
        fiveAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);

        ArrayList<Corner> hidEmpHidEmp = new ArrayList<>();
        hidEmpHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        hidEmpHidEmp.add(new Corner(CornerEnum.HIDDEN));
        hidEmpHidEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(5, noCondition, CornerEnum.ResourceEnum.ANIMAL, fiveAnimal, hidEmpHidEmp, "src/main/resources/images/cards/gold_cards/blue_fronts/page_70.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_70.png"));


        // Purple Cards


        ResourceCondition twoInsectOnePlant = new ResourceCondition();
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);

        ArrayList<Corner> empQuiHidEmp = new ArrayList<>();
        empQuiHidEmp.add(new Corner(CornerEnum.EMPTY));
        empQuiHidEmp.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        empQuiHidEmp.add(new Corner(CornerEnum.HIDDEN));
        empQuiHidEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(1, quillCondition, CornerEnum.ResourceEnum.INSECT, twoInsectOnePlant, empQuiHidEmp, "src/main/resources/images/cards/gold_cards/purple_fronts/page_71.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_71.png"));


        ResourceCondition twoInsectOneAnimal = new ResourceCondition();
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOnePlant.addClause(CornerEnum.ResourceEnum.ANIMAL);

        ArrayList<Corner> empHidManEmp = new ArrayList<>();
        empHidManEmp.add(new Corner(CornerEnum.EMPTY));
        empHidManEmp.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        empHidEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        empHidManEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(1, manuscriptCondition, CornerEnum.ResourceEnum.INSECT, twoInsectOneAnimal, empHidManEmp, "src/main/resources/images/cards/gold_cards/purple_fronts/page_72.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_72.png"));


        ResourceCondition twoInsectOneFungi = new ResourceCondition();
        twoInsectOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);
        twoInsectOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);

        ArrayList<Corner> hidEmpEmpInk = new ArrayList<>();
        hidEmpEmpInk.add(new Corner(CornerEnum.EMPTY));
        hidEmpEmpInk.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        hidEmpEmpInk.add(new Corner(CornerEnum.HIDDEN));
        hidEmpEmpInk.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(1, inkwellCondition, CornerEnum.ResourceEnum.INSECT, twoInsectOnePlant, hidEmpEmpInk, "src/main/resources/images/cards/gold_cards/purple_fronts/page_73.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_73.png"));




        ResourceCondition threeInsectOneAnimal = new ResourceCondition();
        threeInsectOneAnimal.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneAnimal.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneAnimal.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneAnimal.addClause(CornerEnum.ResourceEnum.ANIMAL);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.INSECT, threeInsectOneAnimal, empEmpHidEmp, "src/main/resources/images/cards/gold_cards/purple_fronts/page_74.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_74.png"));


        ResourceCondition threeInsectOnePlant = new ResourceCondition();
        threeInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOnePlant.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOnePlant.addClause(CornerEnum.ResourceEnum.PLANT);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.INSECT, threeInsectOnePlant, empEmpEmpHid, "src/main/resources/images/cards/gold_cards/purple_fronts/page_75.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_75.png"));


        ResourceCondition threeInsectOneFungi = new ResourceCondition();
        threeInsectOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneFungi.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsectOneFungi.addClause(CornerEnum.ResourceEnum.FUNGI);

        content.add(new GoldCard(2, cornerCondition, CornerEnum.ResourceEnum.INSECT, threeInsectOneFungi, empHidEmpEmp, "src/main/resources/images/cards/gold_cards/purple_fronts/page_76.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_76.png"));



        ResourceCondition threeInsect = new ResourceCondition();
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        threeInsect.addClause(CornerEnum.ResourceEnum.INSECT);

        ArrayList<Corner> inkHidEmpHid = new ArrayList<>();
        inkHidEmpHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        inkHidEmpHid.add(new Corner(CornerEnum.HIDDEN));
        inkHidEmpHid.add(new Corner(CornerEnum.EMPTY));
        inkHidEmpHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.INSECT, threeInsect, inkHidEmpHid, "src/main/resources/images/cards/gold_cards/purple_fronts/page_77.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_77.png"));


        ArrayList<Corner> empManHidHid = new ArrayList<>();
        empManHidHid.add(new Corner(CornerEnum.ObjectEnum.EMPTY));
        empManHidHid.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        empManHidHid.add(new Corner(CornerEnum.HIDDEN));
        empManHidHid.add(new Corner(CornerEnum.HIDDEN));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.INSECT, threeInsect, empManHidHid, "src/main/resources/images/cards/gold_cards/purple_fronts/page_78.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_78.png"));


        ArrayList<Corner> hidHidQuiEmp = new ArrayList<>();
        hidHidQuiEmp.add(new Corner(CornerEnum.ObjectEnum.HIDDEN));
        hidHidQuiEmp.add(new Corner(CornerEnum.ObjectEnum.HIDDEN));
        hidHidQuiEmp.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        hidHidQuiEmp.add(new Corner(CornerEnum.EMPTY));
        content.add(new GoldCard(3, noCondition, CornerEnum.ResourceEnum.INSECT, threeInsect, hidHidQuiEmp, "src/main/resources/images/cards/gold_cards/purple_fronts/page_79.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_79.png"));



        ResourceCondition fiveInsect = new ResourceCondition();
        fiveInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        fiveInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        fiveInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        fiveInsect.addClause(CornerEnum.ResourceEnum.INSECT);
        fiveInsect.addClause(CornerEnum.ResourceEnum.INSECT);

        ArrayList<Corner> empEmpHidHid = new ArrayList<>();
        empEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        empEmpHidHid.add(new Corner(CornerEnum.EMPTY));
        empEmpHidHid.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new GoldCard(5, noCondition, CornerEnum.ResourceEnum.INSECT, fiveInsect, empEmpHidHid, "src/main/resources/images/cards/gold_cards/purple_fronts/page_80.png", "src/main/resources/images/cards/gold_cards/purple_backs/page_80.png"));


        return goldCardDeck;
    }
}
