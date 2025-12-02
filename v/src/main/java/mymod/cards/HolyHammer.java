package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.ModTag;
import mymod.character.V;
import mymod.powers.ComprehendPower;
import mymod.util.CardStats;

public class HolyHammer extends BaseCard{
    public static final String ID = makeID(HolyHammer.class.getSimpleName());
    public static final CardStats info =new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    public HolyHammer() {
        super(ID,info);
        setDamage(4,2);
        setMagic(2,1);
        setSelfRetain( true);
        setExhaust( true);

        tags.add(ModTag.Card_SP);
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new DrawCardAction(1));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(p.hasPower(ComprehendPower.POWER_ID)){
                    p.getPower(ComprehendPower.POWER_ID).amount+=magicNumber;
                }
                this.isDone=true;
            }
        });

    }

    public AbstractCard makeCopy() {
        return new HolyHammer();
    }
}
