package mymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;

public class LowerTheAnchorAction extends AbstractGameAction {
    private final int damage;
    private final AbstractMonster m;
    private final AbstractPlayer p;
    public LowerTheAnchorAction(AbstractPlayer p,AbstractMonster m, int damage) {
        this.p = p;
        this.m = m;
        this.damage = damage;

    }

    @Override
    public void update() {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new FlightPower(m, 2)));
        this.isDone = true;
    }

}
