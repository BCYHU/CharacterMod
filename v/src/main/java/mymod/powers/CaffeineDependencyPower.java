package mymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static mymod.BasicMod.makeID;


public class CaffeineDependencyPower extends BasePower {


    public static final String POWER_ID = makeID(CaffeineDependencyPower.class.getSimpleName());
    public static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);


    public CaffeineDependencyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.DEBUFF,false,owner,amount);
        updateDescription();
    }

    public void updateDescription() {
        if((owner.hasPower(StrengthPower.POWER_ID)) && (owner.getPower(StrengthPower.POWER_ID).amount>0)){
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1]+powerStrings.DESCRIPTIONS[2]+amount+powerStrings.DESCRIPTIONS[3];
        }
        else{
            this.description = powerStrings.DESCRIPTIONS[0] + amount + powerStrings.DESCRIPTIONS[1];
        }
    }

    public void atStartOfTurn() {
        flash();
        addToBot((new ApplyPowerAction(owner,owner,new StrengthPower(owner,-amount),-amount)));
        if((owner.hasPower(StrengthPower.POWER_ID)) && (owner.getPower(StrengthPower.POWER_ID).amount>0)){
            addToBot((new ApplyPowerAction(owner,owner,new PlatedArmorPower(owner,amount))));
        }
    }
}
