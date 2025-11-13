package mymod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.character.V;
import mymod.util.CardStats;

public class ShoutOfCourage extends BaseCard{
    public static final String ID = makeID(ShoutOfCourage.class.getSimpleName());
    private static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            2
    );
    public ShoutOfCourage(){
        super(ID,info);
        setBlock(10,5);
        setMagic(5,3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p,p,magicNumber));
        addToBot(new GainBlockAction(p,p,block));
    }

    public AbstractCard makeCopy() {
        return new ShoutOfCourage();
    }
}
