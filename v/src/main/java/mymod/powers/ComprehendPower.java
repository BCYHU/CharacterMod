package mymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import mymod.BasicMod;

import java.util.Collections;
import java.util.Random;

import static mymod.BasicMod.*;
import static mymod.relics.BeamSlice.isKnight;

public class ComprehendPower extends BasePower {
    public static final String POWER_ID = makeID(ComprehendPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private int currentSPIndex=0;

    public ComprehendPower(AbstractCreature owner,int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.amount=0;
        updateDescription();
    }
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }


    public void onUseCard(AbstractCard c, UseCardAction a) {
       if(isKnight(c)){
           this.amount++;
           if(this.amount==5){
               addToBot(new AbstractGameAction() {
                   @Override
                   public void update() {
                       BasicMod.logger.info("Attempting to draw SP card, available: " + BasicMod.spDrawPile.size());
                       if (!BasicMod.spDrawPile.isEmpty()){
                           AbstractCard cardToHand = spDrawPile.get(currentSPIndex).makeStatEquivalentCopy();
                           AbstractDungeon.player.hand.addToHand(cardToHand);
                           cardToHand.triggerWhenDrawn();
                           BasicMod.logger.info("Added SP card to hand: " + cardToHand.name);

                           //抽完牌后检查是否为sp能力牌，抽出来后再sp抽牌堆中移除
                           if (cardToHand.type==AbstractCard.CardType.POWER){
                               spDrawPile.remove(currentSPIndex);
                               currentSPIndex--;
                           }
                           //

                           currentSPIndex++;
                           if(currentSPIndex>=spDrawPile.size()){
                               currentSPIndex=0;
                               Collections.shuffle(spDrawPile,new Random(AbstractDungeon.cardRandomRng.randomLong()));
                           }
                       }
                       this.isDone=true;
                   }
               });
               AbstractDungeon.player.hand.refreshHandLayout();
               this.amount=0;
               flash();
           }
       }
    }


    //

}
