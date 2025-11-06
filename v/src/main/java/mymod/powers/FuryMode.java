package mymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static mymod.BasicMod.makeID;
import static mymod.BasicMod.modID;

public class FuryMode extends BasePower{
    public static final String POWER_ID = makeID(FuryMode.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(modID+":FuryMode");
    public FuryMode(AbstractCreature owner,int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public void updateDescription() {
        if(this.amount==1){
            this.description = powerStrings.DESCRIPTIONS[0];
        }else{
            this.description = powerStrings.DESCRIPTIONS[1]+ this.amount + powerStrings.DESCRIPTIONS[2];
        }

    }


    public void onUseCard(AbstractCard c, UseCardAction action) {
        if(c.type==AbstractCard.CardType.ATTACK){
            for(int i=0;i<this.amount;i++){
                addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, this.amount), 1));
                addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, this.amount), 1));
            }

        }

        flash();
    }
    public void atEndOfTurn(boolean isPlayer) {
        if(this.amount==0){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner,modID+"FuryMode"));
        }else{
           addToBot(new ReducePowerAction(this.owner, this.owner,modID+"FuryMode",1));

        }
    }
}
