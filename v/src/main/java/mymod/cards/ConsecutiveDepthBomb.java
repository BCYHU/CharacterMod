package mymod.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import mymod.util.CardStats;
import mymod.character.V;

public class ConsecutiveDepthBomb extends BaseCard {
    public static final String ID = makeID(ConsecutiveDepthBomb.class.getSimpleName());
    public static final CardStats info =new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public ConsecutiveDepthBomb() {
        super(ID,info);
        setDamage(4);
        setMagic(1,1);
        setSelfRetain(true);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false)));
    }
    public AbstractCard makeCopy() {
        return new ConsecutiveDepthBomb();
    }
}
