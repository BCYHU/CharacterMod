package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mymod.character.MyCharacter;
import mymod.powers.FuryMode;
import mymod.util.CardStats;

public class Undisputed extends BaseCard{
    public static final String ID = makeID(Undisputed.class.getSimpleName());
    private static final CardStats info=new CardStats(
            MyCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            0
    );
    private static final int DAMAGE = 5;


    public Undisputed(){
        super(ID,info);
        setDamage(DAMAGE);


    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new DamageAction(p,new DamageInfo(p,this.damage,DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p,(AbstractCreature)p,(AbstractPower)new FuryMode((AbstractCreature)p,1),1));
    }
    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            upgradeDamage(-2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Undisputed();
    }
}
