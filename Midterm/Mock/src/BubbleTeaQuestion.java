import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

/*
    Some key thoughts to think of while doing this kind of question: 

    1) Let's take note of some issues when 'UPGRADING' the model / class
    
        b) What additional attributes do we need to better design the BubbleTea? 
            Have we done enough to ensure that it is obvious to ensure that
           invalid types of BubbleTea are not allowed? 

        b)  We need to do error handling. So what types of Exception do we need here? 
            Is there a possibility that we need to do testing. If so, what's the 
            best way to do such kind of testing? 

    3) Immutability. Have we done checks beforehand to ensure the BubbleTea 
        we are creating is valid? Most likely we'll have to do them in psvm. 
 */
public class BubbleTeaQuestion {

    public static void main(String[] args) throws Exception {
        try {
            BubbleTea duplicateToppingsTea = new BubbleTea().addTopping(Topping.BOBA).addTopping(Topping.BOBA).finish();
        } catch (BubbleTeaException e) {
            System.out.println(e);
        }

        try {
            BubbleTea emptyTea = new BubbleTea().finish();
        } catch (BubbleTeaException e) {
            System.out.println(e);
        }

        try {
            BubbleTea tooManyIngredientsTea = new BubbleTea().addBlackTea(250).addSoyMilk(150).finish().addSoyMilk(250);
        } catch (BubbleTeaException e) {
            System.out.println(e);
        }

        BubbleTea normalTea = new BubbleTea().addBlackTea(250).addSoyMilk(250).addMachaTea(150).addLycheeSyrup(150).addTopping(Topping.BOBA).finish();
        BubbleTea originalTea = new OriginalTea().addTopping(Topping.ALOE).finish();
        BubbleTea machaTea = new MachaTea().addTopping(Topping.AIYU).finish();
        BubbleTea lycheeTea = new LycheeTea().addTopping(Topping.BOBA).finish();

    }
}

enum Topping {
    BOBA, 
    ALOE, 
    AIYU
}

/*
    There the reason why we can do this as mentioned earlier 
    one java file can have more than one class. 

    However, it needs to have one PUBLIC CLASS, aka the main class 
    in order for public static void main(Stirng[] args) to work. 
*/
class BubbleTea {
    private final int blackTea;
    private final int machaTea;
    private final int lycheeSyrup;
    private final int soyMilk;

    /*
        After it is finalized, NO MORE changes can be made. 
    */
    private final boolean isFinalized;

    /*
        Use a Set to help check for duplicate value addition.
    */
    private final Set<Topping> toppings;

    /*
        A BubbleTea should have NOTHING at first
    */
    public BubbleTea() {
        this.blackTea = 0;
        this.machaTea = 0;
        this.lycheeSyrup = 0;
        this.soyMilk = 0;
        this.isFinalized = false;
        this.toppings = new HashSet<>();
    }

    /*
        For Child Classes to access
    */
    public BubbleTea(int blackTea, int machaTea, int lycheeSyrup, int soyMilk) {
        this.blackTea = blackTea;
        this.machaTea = machaTea;
        this.lycheeSyrup = lycheeSyrup;
        this.soyMilk = soyMilk;
        this.isFinalized = false;
        this.toppings = new HashSet<>();
    }

    /*
        private so that only instance of class can access this constructor 
    */
    private BubbleTea(int blackTea, int machaTea, int lycheeSyrup, int soyMilk, Set<Topping> toppings, boolean isFinalized) {
        this.blackTea = blackTea;
        this.machaTea = machaTea;
        this.lycheeSyrup = lycheeSyrup;
        this.soyMilk = soyMilk;
        this.toppings = toppings;
        this.isFinalized = isFinalized;
    }   

    public BubbleTea addBlackTea(int amount) throws BubbleTeaException  {
        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        return new BubbleTea(blackTea + amount, machaTea, lycheeSyrup, soyMilk, toppings, isFinalized);
    }

    public BubbleTea addMachaTea(int amount) throws BubbleTeaException  {
        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        return new BubbleTea(blackTea, machaTea + amount, lycheeSyrup, soyMilk, toppings, isFinalized);
    }

    public BubbleTea addLycheeSyrup(int amount) throws BubbleTeaException  {
        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        return new BubbleTea(blackTea, machaTea, lycheeSyrup + amount, soyMilk, toppings, isFinalized);
    }

    public BubbleTea addSoyMilk(int amount) throws BubbleTeaException  {
        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk + amount, toppings, isFinalized);
    }

    public BubbleTea addTopping(Topping topping) throws BubbleTeaException {
        Set<Topping> newToppings = toppings;

        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        if (!newToppings.add(topping)) {
            throw BubbleTeaException.DUPLICATE_TOPPINGS_EXCEPTION;
        }

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk, newToppings, isFinalized);
    }

    public BubbleTea finish() throws BubbleTeaException {
        if (isFinalized) {
            throw BubbleTeaException.FINALIZED_TEA_EXCEPTION;
        }

        return new BubbleTea(blackTea, machaTea, lycheeSyrup, soyMilk, toppings, true);
    }

    /*
        If the instance of the BubbleTea has the right amounts,
        we are sure that it belongs to that specific type of tea. 
    */
    private boolean hasSameIngredients(BubbleTea otherTea) {
        return this.blackTea == otherTea.blackTea
            && this.machaTea == otherTea.machaTea
            && this.lycheeSyrup == otherTea.lycheeSyrup
            && this.soyMilk == otherTea.soyMilk;
    }

}

class OriginalTea extends BubbleTea {
    private final static int BLACK_TEA_AMOUNT = 150;
    private final static int SOY_MILK_AMOUNT = 150;

    public OriginalTea() {
        super(BLACK_TEA_AMOUNT, 0, 0, SOY_MILK_AMOUNT);
    }
}

class MachaTea extends BubbleTea {
    private final static int MACHA_TEA_AMOUNT = 250;
    private final static int SOY_MILK_AMOUNT = 150;

    public MachaTea() {
        super(0, MACHA_TEA_AMOUNT, 0, SOY_MILK_AMOUNT);
    }
}

class LycheeTea extends BubbleTea {
    private final static int BLACK_TEA_AMOUNT = 150;
    private final static int LYCHEE_SYRUP_AMOUNT = 250;

    public LycheeTea() {
        super(BLACK_TEA_AMOUNT, 0, LYCHEE_SYRUP_AMOUNT, 0);
    }
}

class BubbleTeaException extends Exception {
    public static final BubbleTeaException DUPLICATE_TOPPINGS_EXCEPTION = new BubbleTeaException("You cannot add duplicate toppings!");
    public static final BubbleTeaException FINALIZED_TEA_EXCEPTION = new BubbleTeaException("You cannot add new ingredients or toppings when finalized!");

    private BubbleTeaException(String message) {
        super(message);
    }
}