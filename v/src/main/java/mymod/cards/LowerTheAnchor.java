package mymod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.actions.LowerTheAnchorAction;
import mymod.character.V;
import mymod.util.CardStats;


public class LowerTheAnchor extends BaseCard {
    public static final String ID = makeID(LowerTheAnchor.class.getSimpleName());
    public static final CardStats info =new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    public LowerTheAnchor() {
        super(ID,info);
        setDamage(10,4);


        tags.add(ModTag.Card_SP);
        setSelfRetain(true);
        setExhaust(true);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LowerTheAnchorAction(p,m,damage));
    }
    public AbstractCard makeCopy() {
        return new LowerTheAnchor();
    }
}

