package mymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class ExtremeWindBladeAction extends AbstractGameAction {
    private final boolean upgraded;
    private final int[] multiDamage;
    private final AbstractCreature p;
    private final DamageInfo.DamageType damageType;


    public ExtremeWindBladeAction(AbstractPlayer p, int[] multiDamage,DamageInfo.DamageType damageType,boolean upgraded) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.upgraded = upgraded;


        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }


    public void update() {
        addToBot(new DamageAllEnemiesAction(null,multiDamage,this.damageType, AttackEffect.NONE,true));
        addToBot(new DamageAllEnemiesAction(null,multiDamage,this.damageType, AttackEffect.NONE,true));
        if(this.upgraded){
            addToBot(new DamageAllEnemiesAction(null,multiDamage,this.damageType, AttackEffect.NONE,true));
            addToBot(new DamageAllEnemiesAction(null,multiDamage,this.damageType, AttackEffect.NONE,true));
        }
        this.isDone = true;
    }
}

