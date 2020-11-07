package com.paradise.core.common.utils;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Paradise
 */
public class DistanceUtil {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(3L);
        list.add(2L);
        Set<Long> set = new HashSet<>(list);
        set.forEach(System.out::println);
//        System.out.println(getDistance("117.13519769281386,31.83807472796166", "117.11732613414763,31.8370766738247"));
//        System.out.println(getDistance("117.13519769281386,31.83807472796166", "117.21573101848601,31.920019564494538"));
//        System.out.println(getDistance("117.13519769281386,31.83807472796166", "117.28628384441375,34.20347716453021"));

//        double x = 3;
//        double y = 3;
//        double[] xx = new double[]{1, 1, 3, 5, 5};
//        double[] yy = new double[]{1, 5, 6, 5, 1};
//        System.out.println(DistanceUtil.isInAccurateArea(x, y, xx, yy));
//        System.out.println(isInPolygon(x, y, xx, yy));
//        System.out.println(isInPolygon(1, 5, xx, yy));
//        System.out.println(isInPolygon(1, 5.1, xx, yy));
//        System.out.println("---");
//        double[] x1x = new double[]{1, 2, 5, 5, 3};
//        double[] y1y = new double[]{3, 4, 6, 1, 1};
//        System.out.println(DistanceUtil.isInAccurateArea(x, y, x1x, y1y));
//        System.out.println(DistanceUtil.isInPolygon(x, y, x1x, y1y));

//        List<String> locationList = new ArrayList<>();
//        locationList.add("1,3");
//        locationList.add("2,4");
//        locationList.add("5,6");
//        locationList.add("5,1");
//        locationList.add("3,1");
//        System.out.println(locationList.toString());
//        System.out.println(isOrderInZone("3,3", locationList.toString()));

//        List<List<String>> listList = new ArrayList<>();
//        List<String> list = new ArrayList<>();
//        list.add("117.143415");
//        list.add("31.84421");
//        listList.add(list);
//        listList.add(list);
//        listList.add(list);
//        listList.add(list);
//        System.out.println(listList.toString());
//        System.out.println(isOrderInZone("3,3", listList.toString()));

    }

    private static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    public static BigDecimal getDistance(String from, String to) {
        double lof = Double.parseDouble(from.split(",")[0]);
        double laf = Double.parseDouble(from.split(",")[1]);
        double lot = Double.parseDouble(to.split(",")[0]);
        double lat = Double.parseDouble(to.split(",")[1]);
        double dis = getDistance(lof, laf, lot, lat);
        return new BigDecimal(String.valueOf(dis));
    }

    private static void extreme(List<String> locationList) {
        BigDecimal left;
        BigDecimal right;
        BigDecimal top;
        BigDecimal bottom;
        List<BigDecimal> los = new ArrayList<>();
        List<BigDecimal> lot = new ArrayList<>();
        for (String location : locationList) {
            String[] arr = location.split(",");
            los.add(new BigDecimal(arr[0]));
            lot.add(new BigDecimal(arr[1]));
        }
        left = DistanceUtil.min(los);
        right = max(los);
        top = max(lot);
        bottom = min(lot);
    }

    private static BigDecimal max(List<BigDecimal> decimalList) {
        return decimalList.stream().max(BigDecimal::compareTo).orElse(null);
    }

    private static BigDecimal min(List<BigDecimal> decimalList) {
        return decimalList.stream().min(BigDecimal::compareTo).orElse(null);
    }


    /**
     * 判断坐标是否在重点区域内
     *
     * @param pointLon 要判断的点的纵坐标
     * @param pointLat 要判断的点的横坐标
     * @param lon      指定区域的纵坐标组成的数组
     * @param lat      指定区域的横坐标组成的数组
     * @return 是否在多边形区域内
     */
    public static boolean isInAccurateArea(double pointLon, double pointLat, double[] lon, double[] lat) {
        // 代表有几个点
        int vertexNum = lon.length;
        boolean result = false;

        for (int i = 0, j = vertexNum - 1; i < vertexNum; j = i++) {
            // 满足条件，与多边形相交一次，result布尔值取反一次，奇数个则在区域内
            boolean flag1 = (lon[i] > pointLon) != (lon[j] > pointLon);

            if (flag1 && (pointLat < (lat[j] - lat[i]) * (pointLon - lon[i]) / (lon[j] - lon[i]) + lat[i])) {
                System.out.println(i);
                result = !result;
            }
        }
        return result;
    }


    /**
     * 判断是否在多边形区域内
     *
     * @param pointLon 要判断的点的纵坐标
     * @param pointLat 要判断的点的横坐标
     * @param lon      区域各顶点的纵坐标数组
     * @param lat      区域各顶点的横坐标数组
     * @return true 在  false 不在
     */
    public static boolean isInPolygon(double pointLon, double pointLat, double[] lon, double[] lat) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point2D.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<>();
        double polygonPointX, polygonPointY;
        for (int i = 0; i < lon.length; i++) {
            polygonPointX = lon[i];
            polygonPointY = lat[i];
            Point2D.Double polygonPoint = new Point2D.Double(polygonPointX, polygonPointY);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }

    /**
     * 一个点是否在多边形内
     *
     * @param point   要判断的点的横纵坐标
     * @param polygon 组成的顶点坐标集合
     * @return true 在  false 不在
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath generalPath = new java.awt.geom.GeneralPath();

        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        generalPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            generalPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        generalPath.lineTo(first.x, first.y);
        generalPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return generalPath.contains(point);
    }

    /**
     * 判断订单坐标是否在区域坐标内 - 主要是坐标解析处理
     *
     * @param orderLocation 订单坐标 经,纬
     * @param zoneLocations 区域服务中心电子围栏坐标
     * @return 是否在区域内 true-在
     */
    public static boolean isOrderInZone(String orderLocation, String zoneLocations) {
        String[] arr = zoneLocations.split("], \\[");
        double[] xx = new double[arr.length];
        double[] yy = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i].replace("[", "").replace("]", "");
            xx[i] = Double.parseDouble(s.split(",")[0]);
            yy[i] = Double.parseDouble(s.split(",")[1]);
        }
        double x = Double.parseDouble(orderLocation.split(",")[0]);
        double y = Double.parseDouble(orderLocation.split(",")[1]);
        return isInPolygon(x, y, xx, yy);
    }

}
