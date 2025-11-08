package mymod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;


import static mymod.BasicMod.makeID;

public class AutoIterativePower extends BasePower {
    public static final String POWER_ID = makeID(AutoIterativePower.class.getSimpleName());
    public static final PowerStrings powerString= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public AutoIterativePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerString.DESCRIPTIONS[0];
    }
    public void atStartOfTurn(){
        addToBot(new PressEndTurnButtonAction());
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner,POWER_ID));
    }
}
