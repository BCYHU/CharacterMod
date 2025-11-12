package mymod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mymod.actions.ExtremeWindBladeAction;
import mymod.character.V;
import mymod.util.CardStats;



public class ExtremeWindBlade extends BaseCard{
    public static final String ID = makeID(ExtremeWindBlade.class.getSimpleName());
    private static final CardStats info = new CardStats(
            V.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ALL_ENEMY,
            2
    );

    public ExtremeWindBlade(){
        super(ID,info);
        this.baseDamage=3;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

        this.damageType = DamageInfo.DamageType.THORNS;
        this.isMultiDamage = true;




        //次数
        setBlock(1,1);

    }

    public static int triggerCount = 0;
    private static int tCheck = 0;

    public static void onCardUsedWithTag(){
        triggerCount++;
    }
    public static void resetTriggerCount(){
        triggerCount = 0;
        tCheck=0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ExtremeWindBladeAction(p,this.multiDamage,this.damageTypeForTurn,this.upgraded,this.damage));
        triggerCount = 0;
        tCheck = 0;
    }


    public void triggerOnGlowCheck() {
        if (tCheck!=triggerCount){
            upgradeDamage(this.magicNumber);
            updateCost(-1);
            tCheck = triggerCount;
        }
        if (triggerCount>=2){
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }

    }

    public AbstractCard makeCopy() { //Optional
        AbstractCard tmp =new ExtremeWindBlade();
        try{
            if(AbstractDungeon.player!=null){
                tmp.updateCost(AbstractDungeon.player.damagedThisCombat);
            }
        }catch(Exception e){
            System.out.println("ExtremeWindBlade.makeCopy()");
        }

        return tmp;
    }

}
