package com.mycompany;

import java.util.ArrayList;
import java.util.List;

public final class FooList extends Object{
    
    private static List<Foo> foos = new ArrayList<>();
    
    private FooList() {
    }
    
    public static List<Foo> getList() {
        if (foos.size() == 0) {
            Foo fooA = new Foo("A");
            {
                Foo fooAA = new Foo(fooA, "AA");
                {
                    new Foo(fooAA, "AAA");
                    new Foo(fooAA, "AAB");
                }
                Foo fooAB = new Foo(fooA, "AB");
                {
                    new Foo(fooAB, "ABA");
                    Foo fooABB = new Foo(fooAB, "ABB");
                    {
                        new Foo(fooABB, "ABBA");
                        Foo fooABBB = new Foo(fooABB, "ABBB");
                        {
                            new Foo(fooABBB, "ABBBA");
                        }
                    }
                    new Foo(fooAB, "ABC");
                    new Foo(fooAB, "ABD");
                }
                Foo fooAC = new Foo(fooA, "AC");
                {
                    new Foo(fooAC, "ACA");
                    new Foo(fooAC, "ACB");
                }
            }
            foos.add(fooA);

            Foo fooB = new Foo("B");
            {
                new Foo(fooB, "BA");
                new Foo(fooB, "BB");
            }
            foos.add(fooB);

            Foo fooC = new Foo("C");
            foos.add(fooC);
        }
        return foos;
    }

    public static Foo getFoo(String id)
    {
        return findFoo(foos, id);
    }

    private static Foo findFoo(List<Foo> foos, String id)
    {
        for (Foo foo : foos)
        {
            if (foo.getId().equals(id))
            {
                return foo;
            }

            Foo temp = findFoo(foo.getFoos(), id);
            if (temp != null)
            {
                return temp;
            }
        }

        return null;
    }
}
