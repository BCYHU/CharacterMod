package mymod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static mymod.BasicMod.makeID;

public class SpeedAlongPower extends BasePower{
    public static final String POWER_ID = makeID(SpeedAlongPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public SpeedAlongPower(AbstractCreature owner,int amount){
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0]+amount+powerStrings.DESCRIPTIONS[1];
    }

    public void stackPower(int stackAmount) {
        this.fontScale=8.0F;
        this.amount+=stackAmount;
    }

    public int onAttackedToChangeDamage(DamageInfo info,int damageAmount){
        if(damageAmount>0){
            int damageToReturn = damageAmount/2;
            if(this.amount ==1){
                addToBot(new RemoveSpecificPowerAction(owner,owner,this.ID));
            }else{
                addToBot(new ReducePowerAction(owner,owner,this.ID,1));
            }
            return damageToReturn;
        }
        return damageAmount;
    }

}
