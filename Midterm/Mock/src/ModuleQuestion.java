import java.util.List;

/*
    To ensure that it works fine. 
*/
public class ModuleQuestion {
  
}

/*
    Both Loan and Scholarship are some form 
    of Monetary Benefit! 
*/
interface MonetaryBonus {

}

class Loan implements MonetaryBonus {
    private int amount;
}

class Scholarship implements MonetaryBonus {
    private int amount;
}

/*
    Student is Payable. 

    This ensures that the FinanceOffice is only able 
    to access methods related to payment / loans / scholarships.
*/
interface Payable {
    public List<MonetaryBonus> getMonetaryBonuses();
    public void receiveBonus(MonetaryBonus bonus);
}

/*
    Student is Gradeable. 

    This ensures that the AdminOffice is only able 
    to access methods related to marks / modules
*/
interface Gradeable {
    public List<Module> getModules();
    public void receiveModule(Module module);
    public void receiveMarks(Module module, double marks);
}

class Module {
    private double marks;

    public void changeMarks(double marks) {
        this.marks = marks;
    }
}

/*
    Must have a List of Modules for the AdminOffice to access
    Must have a List of MonetaryBonus for the FinanceOffice to access 
*/
class Student implements Gradeable, Payable {
    private List<MonetaryBonus> activeBonuses;
    private List<Module> currentModules;

    public List<MonetaryBonus> getMonetaryBonuses() {
        return this.activeBonuses;
    }

    public List<Module> getModules() {
        return this.currentModules;
    }

    public void receiveBonus(MonetaryBonus bonus) {

    }

    public void receiveModule(Module module) {

    }

    public void receiveMarks(Module module, double marks) {

    }
}

class Undergraduate extends Student {

}

class ExchangeStudent extends Student {

}

/*
    Contains logic wrt an Office
*/
interface Office {

}

/*
    Only access method wrt to a Gradeable instance.
*/
class AdminOffice implements Office {
    private List<Gradeable> payees; 

    public void assignModule(Gradeable coursee, Module module) {

    }

    public void assignMarks(Gradeable coursee, Module module) {

    }
}

/*
    Only access method wrt to a Payable instance.
*/
class FinanceOffice implements Office {
    private List<Payable> payees; 

    public void assignBonus(Payable payed, MonetaryBonus bonus) {

    }
}