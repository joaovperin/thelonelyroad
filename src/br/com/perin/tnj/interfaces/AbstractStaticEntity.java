package br.com.perin.tnj.interfaces;

public abstract class AbstractStaticEntity extends AbstractEntity implements StaticEntity {

    private double dx;
    private double dy;

    public AbstractStaticEntity(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.dx = 0;
        this.dy = 0;
    }

}
