package mymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import mymod.ModTag;

import java.util.ArrayList;

import static mymod.BasicMod.makeID;
import static mymod.relics.BeamSlice.isV;

public class ComprehendPower extends BasePower {
    public static final String POWER_ID = makeID(ComprehendPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public ComprehendPower(AbstractCreature owner,int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.amount=0;
        updateDescription();
    }
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }


    public void onUseCard(AbstractCard c, UseCardAction a) {
       if(isV(c)){
           this.amount++;
           if(this.amount==10){
               addToBot(new AbstractGameAction() {
                   @Override
                   public void update() {
                       ArrayList<AbstractCard> Cl =new ArrayList<>();
                       for (AbstractCard c:AbstractDungeon.player.drawPile.group){
                           if (c.hasTag(ModTag.Card_SP)){
                               Cl.add(c);
                           }
                       }
                       if (!Cl.isEmpty()){
                           AbstractCard cardToHand=Cl.get(0);
                           AbstractDungeon.player.drawPile.moveToHand(cardToHand);
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
}
