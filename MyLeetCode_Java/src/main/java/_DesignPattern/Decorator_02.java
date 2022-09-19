package _DesignPattern;

import org.testng.annotations.Test;

public class Decorator_02 {

    public abstract class Coffee {
        public abstract String getDescription();

        public abstract double getCost();
    }


    public abstract class CoffeeDecorator extends Coffee {
        Coffee coffee;

        public CoffeeDecorator(Coffee coffee) {
            this.coffee = coffee;
        }

    }


    public class Expresso extends Coffee {
        String description;

        public Expresso() {
            description = "Expresso";
        }

        public String getDescription() {
            return description;
        }

        public double getCost() {
            return 1.99;
        }
    }

    public class HouseBlend extends Coffee {
        String description;

        public HouseBlend() {
            description = "HouseBlend";
        }

        public String getDescription() {
            return description;
        }

        public double getCost() {
            return 2.99;
        }
    }


    public class MoCha extends CoffeeDecorator {

        public MoCha(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {
            return coffee.getDescription() + ", Mocha";
        }

        public double getCost() {
            return coffee.getCost() + 0.5;
        }
    }

    public class Milk extends CoffeeDecorator {

        public Milk(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {
            return coffee.getDescription() + ", Milk";
        }

        public double getCost() {
            return coffee.getCost() + 0.8;
        }
    }

    public class Soy extends CoffeeDecorator {

        public Soy(Coffee coffee) {
            super(coffee);
        }

        public String getDescription() {
            return coffee.getDescription() + ", Soy";
        }

        public double getCost() {
            return coffee.getCost() + 0.4;
        }
    }

    @Test
    public void test1() {
        Coffee coffee_1 = new Expresso();
        System.out.println(coffee_1.getDescription() + " $" + coffee_1.getCost());

        Coffee coffee_2 = new MoCha(coffee_1);
        System.out.println(coffee_2.getDescription() + " $" + coffee_2.getCost());

        Coffee coffee_3 = new MoCha(coffee_2);
        System.out.println(coffee_3.getDescription() + " $" + coffee_3.getCost());

        Coffee coffee_4 = new Milk(coffee_3);
        System.out.println(coffee_4.getDescription() + " $" + coffee_4.getCost());

        Coffee coffee_5 = new Soy(coffee_4);
        System.out.println(coffee_5.getDescription() + " $" + coffee_5.getCost());
    }

    @Test
    public void test2() {
        Coffee coffee_1 = new HouseBlend();
        System.out.println(coffee_1.getDescription() + " $" + coffee_1.getCost());

        Coffee coffee_2 = new MoCha(coffee_1);
        System.out.println(coffee_2.getDescription() + " $" + coffee_2.getCost());

        Coffee coffee_3 = new MoCha(coffee_2);
        System.out.println(coffee_3.getDescription() + " $" + coffee_3.getCost());

        Coffee coffee_4 = new Milk(coffee_3);
        System.out.println(coffee_4.getDescription() + " $" + coffee_4.getCost());

        Coffee coffee_5 = new Soy(coffee_4);
        System.out.println(coffee_5.getDescription() + " $" + coffee_5.getCost());
    }

}
