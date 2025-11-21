package mymod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import mymod.ModTag;
import mymod.character.V;
import mymod.util.CardStats;

import java.util.ArrayList;

public class Albatross extends BaseCard{
    public static  final String ID = makeID(Albatross.class.getSimpleName());
    private static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            1
    );

    public Albatross(){
        super(ID,info);
        setDamage(4,3);
        setMagic(1,1);
        setExhaust(true);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster target = null;
        ArrayList<AbstractMonster> monsters = AbstractDungeon.getMonsters().monsters;
        for(int i =monsters.size() -1;i>=0;i--){
            if(!monsters.get(i).isDeadOrEscaped()){
                target = monsters.get(i);
                break;
            }
        }
        if(target != null){
            addToBot(new DamageAction(target,new DamageInfo(null,damage, DamageInfo.DamageType.THORNS)));
            addToBot(new ApplyPowerAction(target,p,new StrengthPower(target,-magicNumber),-magicNumber));
        }
    }

    public AbstractCard makeCopy(){
        return new Albatross();
    }
}
