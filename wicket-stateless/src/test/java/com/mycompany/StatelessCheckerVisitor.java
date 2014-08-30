package com.mycompany;

import org.apache.wicket.Component;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

public class StatelessCheckerVisitor implements IVisitor<Component, Component>
{

    @Override
    public void component(Component component, IVisit<Component> iVisit)
    {
        if (!component.isStateless())
        {
            iVisit.stop(component);
        }
    }

}

