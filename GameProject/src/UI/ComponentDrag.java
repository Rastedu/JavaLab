package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class ComponentDrag {

    private MouseAdapter ma;
    private ArrayList<JComponent> components = new ArrayList<>();
    private int startingX, startingY;
    private boolean autoLayout = true;
    private final JFrame container;
    private JPanel glassPane;
    boolean firstTime = true;
    public JComponent lastClickedContainer;

    public ComponentDrag(final JFrame container) {
        this.container = container;
        glassPane = new JPanel();
        glassPane.setOpaque(false);
        glassPane.setLayout(null);
        this.container.setGlassPane(glassPane);

        new Timer(10, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (container.isVisible()) {
                    glassPane.setVisible(true);
                    ((Timer) ae.getSource()).stop();
                }
            }
        }).start();

        ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                super.mousePressed(me);
                componentPressed(me);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                super.mouseReleased(me);
                componentDropped(me);
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                super.mouseDragged(me);
                componentDragged(me);
            }
        };
    }

    public JComponent findDroppableUnderGlassPane(Point p, Container container, Component source) {
        JComponent c = null;
        Component[] comps = container.getComponents();
        for (Component com : comps) {
            if (com.getName() != null) {
                if (com.getName().equals("Droppable") && com instanceof JComponent) {
                    if (com.contains(SwingUtilities.convertPoint(source, p, com))) {
                        return (JComponent) com;
                    }
                } else if (com instanceof Container) {
                    findDroppableUnderGlassPane(p, (Container) com, source);
                }
            }
        }
        return c;
    }

    public boolean isAutoLayout() {
        return autoLayout;
    }

    public void setAutoLayout(boolean autoLayout) {
        this.autoLayout = autoLayout;
    }

    //public Component c;
    public int value_of_button;
    protected void componentDropped(MouseEvent me) {
        firstTime = true;
        Component droppedComponent = (Component) me.getSource();
        droppedComponent.setCursor(null);

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) == droppedComponent){
                //System.out.println("Found same");
                value_of_button = i;
                //System.out.println("Value = " + value_of_button);
                break;
            }

        }

        JComponent jc = findDroppableUnderGlassPane(me.getPoint(), container.getContentPane(), (Component) me.getSource());

        if (jc != null) {
            glassPane.removeAll();
            glassPane.revalidate();
            glassPane.repaint();

            jc.add(droppedComponent);

            if (autoLayout) {
                if (lastClickedContainer != null) {
                    lastClickedContainer.revalidate();
                    lastClickedContainer.repaint();
                }
                droppedComponent.revalidate();
                droppedComponent.repaint();
                jc.revalidate();
                jc.repaint();
            }

        } else {
            glassPane.removeAll();
            glassPane.revalidate();
            glassPane.repaint();
            if (lastClickedContainer != null) {
                lastClickedContainer.add(droppedComponent);
                lastClickedContainer.revalidate();
                lastClickedContainer.repaint();
            }
        }
    }
    protected void componentPressed(MouseEvent me) {
        JComponent jc = findDroppableUnderGlassPane(me.getPoint(), container.getContentPane(), (Component) me.getSource());

        if (jc != null && jc.getName().equals("Droppable")) {
            lastClickedContainer = jc;
        }

        boolean clickedRegisteredComponent = false;

        Component clickedComponent = (Component) me.getSource();


        for (Component component : ComponentDrag.this.components) {
            if (component.equals(clickedComponent)) {
                clickedRegisteredComponent = true;
                break;
            }
        }

        if (clickedRegisteredComponent) {
            startingX = me.getX();
            startingY = me.getY();
            clickedComponent.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }
    }

    protected void componentDragged(MouseEvent me) {
        Component draggedComponent = (Component) me.getSource();

        if (firstTime && lastClickedContainer != null) {
            firstTime = false;
            lastClickedContainer.remove(draggedComponent);
            lastClickedContainer.revalidate();
            lastClickedContainer.repaint();
            glassPane.add(draggedComponent);
            glassPane.revalidate();
            glassPane.repaint();
        }

        //MouseEvent will refire on each drag with the position being relative to the firing Component
        draggedComponent.setLocation((me.getX() - startingX) + draggedComponent.getLocation().x, (me.getY() - startingY) + draggedComponent.getLocation().y);
    }

    void registerComponent(JComponent draggableComp) {
        draggableComp.addMouseListener(ma);
        draggableComp.addMouseMotionListener(ma);
        components.add(draggableComp);
    }

    void deregisterComponent(JComponent draggableComp) {
        draggableComp.removeMouseListener(ma);
        draggableComp.removeMouseMotionListener(ma);
        components.remove(draggableComp);
    }
}
