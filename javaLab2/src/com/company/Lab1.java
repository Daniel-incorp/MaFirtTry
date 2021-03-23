package com.company;

import java.util.Map;
import java.util.Scanner;

public class Lab1 {

    public static void main(String[] args) {

        Point3d point1 = new Point3d();
        Point3d point2 = new Point3d();
        Point3d point3 = new Point3d(1, 1, 1);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты первой точки");
        point1.setX(Double.parseDouble(scanner.nextLine()));
        point1.setY(Double.parseDouble(scanner.nextLine()));
        point1.setZ(Double.parseDouble(scanner.nextLine()));

        System.out.println("Введите координаты второй точки");
        point2.setX(Double.parseDouble(scanner.nextLine()));
        point2.setY(Double.parseDouble(scanner.nextLine()));
        point2.setZ(Double.parseDouble(scanner.nextLine()));

        System.out.println("Введите координаты третьей точки");
        point3.setX(Double.parseDouble(scanner.nextLine()));
        point3.setY(Double.parseDouble(scanner.nextLine()));
        point3.setZ(Double.parseDouble(scanner.nextLine()));
        

        System.out.println("Первая и вторая точки одинаковы - " + point1.equalsTo(point2));
        System.out.println("Первая и третья точки одинаковы - " + point1.equalsTo(point3));
        System.out.println("Вторая и третья точки одинаковы - " + point2.equalsTo(point3));

        System.out.println("Расстояние между третьей и второй точками = " + point3.distanceTo(point2));

        if (point1.equalsTo(point2) || point1.equalsTo(point3) || point2.equalsTo(point3))
            System.out.println("Одна из точек равна другой! Невозможно вычислить площадь так как треугольника не существует.");
        else
            System.out.println("Площадь треугольника = " + computeArea(point1, point2, point3));

    }

    /** Из-за того, что метод distanceTo округляет значения до двух знаков
     * после запятой, значения площади могут быть неточными.
     * С 7 знака после запятой числа ненадёжны**/
    // площадь треугольника по формуле Герона
    public static double computeArea (Point3d firstPoint, Point3d secondPoint, Point3d thirdPoint){

        double a = firstPoint.distanceTo(secondPoint);
        double b = firstPoint.distanceTo(thirdPoint);
        double c = secondPoint.distanceTo(thirdPoint);

        double halfP = (a + b + c) / 2;

        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }

}
