package mymod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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



    public Undisputed(){
        super(ID,info);
        this.baseMagicNumber=2;
        this.magicNumber=this.baseMagicNumber;



    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new ApplyPowerAction(p,p,new FuryMode(p,this.magicNumber),this.magicNumber));
    }
    @Override
    public void upgrade(){
        if(!this.upgraded){
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Undisputed();
    }
}
