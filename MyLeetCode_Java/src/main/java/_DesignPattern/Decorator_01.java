package _DesignPattern;


// https://www.baeldung.com/java-decorator-pattern

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class Decorator_01 {

    public interface ChristmasTree {
        String decorate();
    }

    public class ChristmasTreeImpl implements ChristmasTree {

        @Override
        public String decorate() {
            return "Christmas tree";
        }
    }

    public abstract class TreeDecorator implements ChristmasTree {
        private ChristmasTree tree;

        public TreeDecorator(ChristmasTree tree) {
            this.tree = tree;
        }

        // standard constructors
        @Override
        public String decorate() {
            return tree.decorate();
        }
    }

    public class Garland extends TreeDecorator {

        public Garland(ChristmasTree tree) {
            super(tree);
        }

        public String decorate() {
            return super.decorate() + decorateWithBubbleLights();
        }

        private String decorateWithBubbleLights() {
            return " with Garland";
        }
    }

    public class BubbleLights extends TreeDecorator {

        public BubbleLights(ChristmasTree tree) {
            super(tree);
        }

        public String decorate() {
            return super.decorate() + decorateWithBubbleLights();
        }

        private String decorateWithBubbleLights() {
            return " with Bubble Lights";
        }
    }

    @Test
    public void whenDecoratorsInjectedAtRuntime_thenConfigSuccess() {
        ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
        assertEquals(tree1.decorate(),
                "Christmas tree with Garland");

        ChristmasTree tree2 = new BubbleLights(
                new Garland(new Garland(new ChristmasTreeImpl())));
        assertEquals(tree2.decorate(),
                "Christmas tree with Garland with Garland with Bubble Lights");
    }

}
