package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;

public class Strike_V extends BaseCard{
    public static final String ID = makeID(Strike_V.class.getSimpleName());
    private static final CardStats info=new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public Strike_V(){
        super(ID,info);
        setDamage(DAMAGE,UPG_DAMAGE);

        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
        tags.add(ModTag.Card_Knight);
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(
                m,new DamageInfo(p,damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Strike_V();
    }
}
