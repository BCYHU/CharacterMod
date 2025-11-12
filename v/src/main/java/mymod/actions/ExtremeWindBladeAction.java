package mymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class ExtremeWindBladeAction extends AbstractGameAction {
    private final boolean upgraded;
    private final int[] multiDamage;
    private final AbstractCreature p;
    private final DamageInfo.DamageType damageType;
    private final int Damage;


    public ExtremeWindBladeAction(AbstractPlayer p, int[] multiDamage,DamageInfo.DamageType damageType,boolean upgraded,int Damage) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.upgraded = upgraded;
        this.Damage = Damage;

        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
    }


    public void update() {
        int[] finalDamage =new int[this.multiDamage.length];
        for (int i = 0; i < this.multiDamage.length; i++) {
            finalDamage[i] = this.multiDamage[i] + this.Damage;
        }

        addToBot(new DamageAllEnemiesAction(this.p,finalDamage,this.damageType, AttackEffect.NONE,true));
        if(this.upgraded){
            addToBot(new DamageAllEnemiesAction(this.p,finalDamage,this.damageType, AttackEffect.NONE,true));
        }
        this.isDone = true;
    }
}

