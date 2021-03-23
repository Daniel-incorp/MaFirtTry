package com.company;

public class Point3d extends Point2d{


    private double zCoord;

    public Point3d(double x, double y, double z) {
        setX(x);
        setY(y);
        zCoord = z;
    }

    public Point3d() {
        this(0, 0, 0);
    }

    public double getZ () {
        return zCoord;
    }


    public void setZ ( double val) {
        zCoord = val;
    }

    public double distanceTo (Point3d otherPoint){
        return Math.floor(100 * Math.sqrt(
                (getX() - otherPoint.getX()) * (getX() - otherPoint.getX()) +
                (getY() - otherPoint.getY()) * (getY() - otherPoint.getY()) +
                (zCoord - otherPoint.getZ()) * (zCoord - otherPoint.getZ()) )) / 100;
    }

    public boolean equalsTo (Point3d otherPoint){
        return (getX() == otherPoint.getX() &&
                getY() == otherPoint.getY() &&
                zCoord == otherPoint.getZ());
    }
}
