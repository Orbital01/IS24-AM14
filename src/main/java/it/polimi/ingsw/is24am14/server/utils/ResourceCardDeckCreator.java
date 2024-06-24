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
        ArrayList<Corner> backCorners = new ArrayList<>();
        CornerEnum.ResourceEnum resource = CornerEnum.ResourceEnum.FUNGI;
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));
        backCorners.add(new Corner(CornerEnum.EMPTY));



    // Red Cards
        ArrayList<Corner> funEmpFunHid = new ArrayList<>();
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.EMPTY));
        funEmpFunHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funEmpFunHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funEmpFunHid, backCorners, "/images/cards/resource_cards/red_fronts/page_1.png", "/images/cards/resource_cards/red_backs/page_1.png"));

        

        ArrayList<Corner> funFunHidEmp = new ArrayList<>();
        funFunHidEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funFunHidEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funFunHidEmp.add(new Corner(CornerEnum.HIDDEN));
        funFunHidEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funFunHidEmp, backCorners, "/images/cards/resource_cards/red_fronts/page_2.png", "/images/cards/resource_cards/red_backs/page_2.png"));

        

        ArrayList<Corner> empHidFunFun = new ArrayList<>();
        empHidFunFun.add(new Corner(CornerEnum.EMPTY));
        empHidFunFun.add(new Corner(CornerEnum.HIDDEN));
        empHidFunFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        empHidFunFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, empHidFunFun, backCorners, "/images/cards/resource_cards/red_fronts/page_3.png", "/images/cards/resource_cards/red_backs/page_3.png"));

        

        ArrayList<Corner> hidFunEmpFun = new ArrayList<>();
        hidFunEmpFun.add(new Corner(CornerEnum.HIDDEN));
        hidFunEmpFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        hidFunEmpFun.add(new Corner(CornerEnum.EMPTY));
        hidFunEmpFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, hidFunEmpFun, backCorners, "/images/cards/resource_cards/red_fronts/page_4.png", "/images/cards/resource_cards/red_backs/page_4.png"));

        

        ArrayList<Corner> hidQuiPlaFun = new ArrayList<>();
        hidQuiPlaFun.add(new Corner(CornerEnum.HIDDEN));
        hidQuiPlaFun.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        hidQuiPlaFun.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        hidQuiPlaFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, hidQuiPlaFun, backCorners, "/images/cards/resource_cards/red_fronts/page_5.png", "/images/cards/resource_cards/red_backs/page_5.png"));

        

        ArrayList<Corner> inkFunHidAni = new ArrayList<>();
        inkFunHidAni.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        inkFunHidAni.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        inkFunHidAni.add(new Corner(CornerEnum.HIDDEN));
        inkFunHidAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, inkFunHidAni, backCorners, "/images/cards/resource_cards/red_fronts/page_6.png", "/images/cards/resource_cards/red_backs/page_6.png"));

        


        ArrayList<Corner> funInsManEmp = new ArrayList<>();
        funInsManEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funInsManEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        funInsManEmp.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        funInsManEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.FUNGI, funInsManEmp, backCorners, "/images/cards/resource_cards/red_fronts/page_7.png", "/images/cards/resource_cards/red_backs/page_7.png"));

        



        

        ArrayList<Corner> empFunEmpHid = new ArrayList<>();
        empFunEmpHid.add(new Corner(CornerEnum.EMPTY));
        empFunEmpHid.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        empFunEmpHid.add(new Corner(CornerEnum.EMPTY));
        empFunEmpHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.FUNGI, empFunEmpHid, backCorners, "/images/cards/resource_cards/red_fronts/page_8.png", "/images/cards/resource_cards/red_backs/page_8.png"));

        



        ArrayList<Corner> funHidEmpEmp = new ArrayList<>();
        funHidEmpEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funHidEmpEmp.add(new Corner(CornerEnum.ResourceEnum.HIDDEN));
        funHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        funHidEmpEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.FUNGI, funHidEmpEmp, backCorners, "/images/cards/resource_cards/red_fronts/page_9.png", "/images/cards/resource_cards/red_backs/page_9.png"));

        

        ArrayList<Corner> hidEmpFunEmp = new ArrayList<>();
        hidEmpFunEmp.add(new Corner(CornerEnum.HIDDEN));
        hidEmpFunEmp.add(new Corner(CornerEnum.EMPTY));
        hidEmpFunEmp.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        hidEmpFunEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.FUNGI, hidEmpFunEmp, backCorners, "/images/cards/resource_cards/red_fronts/page_10.png", "/images/cards/resource_cards/red_backs/page_10.png"));



    // Green Cards


        resource= CornerEnum.ResourceEnum.PLANT;

        ArrayList<Corner> plaEmpPlaHid = new ArrayList<>();
        plaEmpPlaHid.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaEmpPlaHid.add(new Corner(CornerEnum.EMPTY));
        plaEmpPlaHid.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaEmpPlaHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, plaEmpPlaHid, backCorners, "/images/cards/resource_cards/green_fronts/page_11.png", "/images/cards/resource_cards/green_backs/page_11.png"));



        ArrayList<Corner> plaPlaHidEmp = new ArrayList<>();
        plaPlaHidEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaPlaHidEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaPlaHidEmp.add(new Corner(CornerEnum.HIDDEN));
        plaPlaHidEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, plaPlaHidEmp, backCorners, "/images/cards/resource_cards/green_fronts/page_12.png", "/images/cards/resource_cards/green_backs/page_12.png"));



        ArrayList<Corner> empHidPlaPla = new ArrayList<>();
        empHidPlaPla.add(new Corner(CornerEnum.EMPTY));
        empHidPlaPla.add(new Corner(CornerEnum.HIDDEN));
        empHidPlaPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empHidPlaPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, empHidPlaPla, backCorners, "/images/cards/resource_cards/green_fronts/page_13.png", "/images/cards/resource_cards/green_backs/page_13.png"));



        ArrayList<Corner> hidPlaEmpPla = new ArrayList<>();
        hidPlaEmpPla.add(new Corner(CornerEnum.HIDDEN));
        hidPlaEmpPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        hidPlaEmpPla.add(new Corner(CornerEnum.EMPTY));
        hidPlaEmpPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, hidPlaEmpPla, backCorners, "/images/cards/resource_cards/green_fronts/page_14.png", "/images/cards/resource_cards/green_backs/page_14.png"));



        ArrayList<Corner> hidInsQuiPla = new ArrayList<>();
        hidInsQuiPla.add(new Corner(CornerEnum.HIDDEN));
        hidInsQuiPla.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        hidInsQuiPla.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        hidInsQuiPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, hidInsQuiPla, backCorners, "/images/cards/resource_cards/green_fronts/page_15.png", "/images/cards/resource_cards/green_backs/page_15.png"));



        ArrayList<Corner> funPlaHidInk = new ArrayList<>();
        funPlaHidInk.add(new Corner(CornerEnum.ResourceEnum.FUNGI));
        funPlaHidInk.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        funPlaHidInk.add(new Corner(CornerEnum.HIDDEN));
        funPlaHidInk.add(new Corner(CornerEnum.ObjectEnum.INKWELL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, funPlaHidInk, backCorners, "/images/cards/resource_cards/green_fronts/page_16.png", "/images/cards/resource_cards/green_backs/page_16.png"));



        ArrayList<Corner>  manHidPlaAni = new ArrayList<>();
        manHidPlaAni.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        manHidPlaAni.add(new Corner(CornerEnum.HIDDEN));
        manHidPlaAni.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        manHidPlaAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.PLANT, manHidPlaAni, backCorners, "/images/cards/resource_cards/green_fronts/page_17.png", "/images/cards/resource_cards/green_backs/page_17.png"));

        

        
        ArrayList<Corner> empEmpPlaHid = new ArrayList<>();
        empEmpPlaHid.add(new Corner(CornerEnum.EMPTY));
        empEmpPlaHid.add(new Corner(CornerEnum.EMPTY));
        empEmpPlaHid.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        empEmpPlaHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.PLANT, empEmpPlaHid, backCorners, "/images/cards/resource_cards/green_fronts/page_18.png", "/images/cards/resource_cards/green_backs/page_18.png"));



        ArrayList<Corner> empEmpHidPla = new ArrayList<>();
        empEmpHidPla.add(new Corner(CornerEnum.EMPTY));
        empEmpHidPla.add(new Corner(CornerEnum.EMPTY));
        empEmpHidPla.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidPla.add(new Corner(CornerEnum.ResourceEnum.PLANT));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.PLANT, empEmpHidPla, backCorners, "/images/cards/resource_cards/green_fronts/page_19.png", "/images/cards/resource_cards/green_backs/page_19.png"));



        ArrayList<Corner> hidPlaEmpEmp = new ArrayList<>();
        hidPlaEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        hidPlaEmpEmp.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        hidPlaEmpEmp.add(new Corner(CornerEnum.EMPTY));
        hidPlaEmpEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.PLANT, hidPlaEmpEmp, backCorners, "/images/cards/resource_cards/green_fronts/page_20.png", "/images/cards/resource_cards/green_backs/page_20.png"));

        

        // Blue Cards


        resource= CornerEnum.ResourceEnum.ANIMAL;
        ArrayList<Corner> aniAniEmpHid = new ArrayList<>();
        aniAniEmpHid.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        aniAniEmpHid.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        aniAniEmpHid.add(new Corner(CornerEnum.EMPTY));
        aniAniEmpHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, aniAniEmpHid, backCorners, "/images/cards/resource_cards/blue_fronts/page_21.png", "/images/cards/resource_cards/blue_backs/page_21.png"));



        ArrayList<Corner> hidEmpAniAni = new ArrayList<>();
        hidEmpAniAni.add(new Corner(CornerEnum.HIDDEN));
        hidEmpAniAni.add(new Corner(CornerEnum.EMPTY));
        hidEmpAniAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        hidEmpAniAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, hidEmpAniAni, backCorners, "/images/cards/resource_cards/blue_fronts/page_22.png", "/images/cards/resource_cards/blue_backs/page_22.png"));



        ArrayList<Corner> aniHidAniEmp = new ArrayList<>();
        aniHidAniEmp.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        aniHidAniEmp.add(new Corner(CornerEnum.HIDDEN));
        aniHidAniEmp.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        aniHidAniEmp.add(new Corner(CornerEnum.ResourceEnum.EMPTY));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, aniHidAniEmp, backCorners, "/images/cards/resource_cards/blue_fronts/page_23.png", "/images/cards/resource_cards/blue_backs/page_23.png"));


        ArrayList<Corner> empAniHidAni = new ArrayList<>();
        empAniHidAni.add(new Corner(CornerEnum.EMPTY));
        empAniHidAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        empAniHidAni.add(new Corner(CornerEnum.HIDDEN));
        empAniHidAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, empAniHidAni, backCorners, "/images/cards/resource_cards/blue_fronts/page_24.png", "/images/cards/resource_cards/blue_backs/page_24.png"));


        ArrayList<Corner> hidInsInkAni = new ArrayList<>();
        hidInsInkAni.add(new Corner(CornerEnum.HIDDEN));
        hidInsInkAni.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        hidInsInkAni.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        hidInsInkAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, hidInsInkAni, backCorners, "/images/cards/resource_cards/blue_fronts/page_25.png", "/images/cards/resource_cards/blue_backs/page_25.png"));


        ArrayList<Corner> plaAniHidMan = new ArrayList<>();
        plaAniHidMan.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        plaAniHidMan.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        plaAniHidMan.add(new Corner(CornerEnum.HIDDEN));
        plaAniHidMan.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, plaAniHidMan, backCorners, "/images/cards/resource_cards/blue_fronts/page_26.png", "/images/cards/resource_cards/blue_backs/page_26.png"));


        ArrayList<Corner> quiHidAniFun = new ArrayList<>();
        quiHidAniFun.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        quiHidAniFun.add(new Corner(CornerEnum.HIDDEN));
        quiHidAniFun.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        quiHidAniFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.ANIMAL, quiHidAniFun, backCorners, "/images/cards/resource_cards/blue_fronts/page_27.png", "/images/cards/resource_cards/blue_backs/page_27.png"));

        

        
        ArrayList<Corner> hidEmpAniEmp = new ArrayList<>();
        hidEmpAniEmp.add(new Corner(CornerEnum.HIDDEN));
        hidEmpAniEmp.add(new Corner(CornerEnum.EMPTY));
        hidEmpAniEmp.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        hidEmpAniEmp.add(new Corner(CornerEnum.ResourceEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, hidEmpAniEmp, backCorners, "/images/cards/resource_cards/blue_fronts/page_28.png", "/images/cards/resource_cards/blue_backs/page_28.png"));


        ArrayList<Corner> empHidEmpAni = new ArrayList<>();
        empHidEmpAni.add(new Corner(CornerEnum.EMPTY));
        empHidEmpAni.add(new Corner(CornerEnum.HIDDEN));
        empHidEmpAni.add(new Corner(CornerEnum.EMPTY));
        empHidEmpAni.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, empHidEmpAni, backCorners, "/images/cards/resource_cards/blue_fronts/page_29.png", "/images/cards/resource_cards/blue_backs/page_29.png"));


        ArrayList<Corner> empAniEmpHid = new ArrayList<>();
        empAniEmpHid.add(new Corner(CornerEnum.EMPTY));
        empAniEmpHid.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        empAniEmpHid.add(new Corner(CornerEnum.EMPTY));
        empAniEmpHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.ANIMAL, empAniEmpHid, backCorners, "/images/cards/resource_cards/blue_fronts/page_30.png", "/images/cards/resource_cards/blue_backs/page_30.png"));

        

    // Purple Cards

        resource = CornerEnum.ResourceEnum.INSECT;

        ArrayList<Corner> insInsEmpHid = new ArrayList<>();
        insInsEmpHid.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insInsEmpHid.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insInsEmpHid.add(new Corner(CornerEnum.EMPTY));
        insInsEmpHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, insInsEmpHid, backCorners, "/images/cards/resource_cards/purple_fronts/page_31.png", "/images/cards/resource_cards/purple_backs/page_31.png"));



        ArrayList<Corner> hidEmpInsIns = new ArrayList<>();
        hidEmpInsIns.add(new Corner(CornerEnum.HIDDEN));
        hidEmpInsIns.add(new Corner(CornerEnum.EMPTY));
        hidEmpInsIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        hidEmpInsIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, hidEmpInsIns, backCorners, "/images/cards/resource_cards/purple_fronts/page_32.png", "/images/cards/resource_cards/purple_backs/page_32.png"));


        ArrayList<Corner> insHidInsEmp = new ArrayList<>();
        insHidInsEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insHidInsEmp.add(new Corner(CornerEnum.HIDDEN));
        insHidInsEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insHidInsEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, insHidInsEmp, backCorners, "/images/cards/resource_cards/purple_fronts/page_33.png", "/images/cards/resource_cards/purple_backs/page_33.png"));



        ArrayList<Corner> empInsHidIns = new ArrayList<>();
        empInsHidIns.add(new Corner(CornerEnum.EMPTY));
        empInsHidIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        empInsHidIns.add(new Corner(CornerEnum.HIDDEN));
        empInsHidIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, empInsHidIns, backCorners, "/images/cards/resource_cards/purple_fronts/page_34.png", "/images/cards/resource_cards/purple_backs/page_34.png"));



        ArrayList<Corner> hidQuiAniIns = new ArrayList<>();
        hidQuiAniIns.add(new Corner(CornerEnum.HIDDEN));
        hidQuiAniIns.add(new Corner(CornerEnum.ObjectEnum.QUILL));
        hidQuiAniIns.add(new Corner(CornerEnum.ResourceEnum.ANIMAL));
        hidQuiAniIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, hidQuiAniIns, backCorners, "/images/cards/resource_cards/purple_fronts/page_35.png", "/images/cards/resource_cards/purple_backs/page_35.png"));



        ArrayList<Corner> manInsHidFun = new ArrayList<>();
        manInsHidFun.add(new Corner(CornerEnum.ObjectEnum.MANUSCRIPT));
        manInsHidFun.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        manInsHidFun.add(new Corner(CornerEnum.HIDDEN));
        manInsHidFun.add(new Corner(CornerEnum.ResourceEnum.FUNGI));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, manInsHidFun, backCorners, "/images/cards/resource_cards/purple_fronts/page_36.png", "/images/cards/resource_cards/purple_backs/page_36.png"));



        ArrayList<Corner> insPlaInkHid = new ArrayList<>();
        insPlaInkHid.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insPlaInkHid.add(new Corner(CornerEnum.ResourceEnum.PLANT));
        insPlaInkHid.add(new Corner(CornerEnum.ObjectEnum.INKWELL));
        insPlaInkHid.add(new Corner(CornerEnum.HIDDEN));

        content.add(new ResourceCard(0, CornerEnum.ResourceEnum.INSECT, insPlaInkHid, backCorners, "/images/cards/resource_cards/purple_fronts/page_37.png", "/images/cards/resource_cards/purple_backs/page_37.png"));

        

        
        ArrayList<Corner> insHidEmpEmp = new ArrayList<>();
        insHidEmpEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        insHidEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        insHidEmpEmp.add(new Corner(CornerEnum.EMPTY));
        insHidEmpEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.INSECT, insHidEmpEmp, backCorners, "/images/cards/resource_cards/purple_fronts/page_38.png", "/images/cards/resource_cards/purple_backs/page_38.png"));



        ArrayList<Corner> empEmpHidIns = new ArrayList<>();
        empEmpHidIns.add(new Corner(CornerEnum.EMPTY));
        empEmpHidIns.add(new Corner(CornerEnum.EMPTY));
        empEmpHidIns.add(new Corner(CornerEnum.HIDDEN));
        empEmpHidIns.add(new Corner(CornerEnum.ResourceEnum.INSECT));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.INSECT, empEmpHidIns, backCorners, "/images/cards/resource_cards/purple_fronts/page_39.png", "/images/cards/resource_cards/purple_backs/page_39.png"));



        ArrayList<Corner> hidInsEmpEmp = new ArrayList<>();
        hidInsEmpEmp.add(new Corner(CornerEnum.HIDDEN));
        hidInsEmpEmp.add(new Corner(CornerEnum.ResourceEnum.INSECT));
        hidInsEmpEmp.add(new Corner(CornerEnum.EMPTY));
        hidInsEmpEmp.add(new Corner(CornerEnum.EMPTY));

        content.add(new ResourceCard(1, CornerEnum.ResourceEnum.INSECT, hidInsEmpEmp, backCorners, "/images/cards/resource_cards/purple_fronts/page_40.png", "/images/cards/resource_cards/purple_backs/page_40.png"));


        return new Deck<>(content);
    }
}
