package org.joseph.algorithm.dp;


/**
 * 动态规划
 *
 * 题目如下：
 * 如下图，某物流派送员p，需要给 a、b、c、d. 4个快递点派送包裹，请问派送员需要选择什么样的路线，才能完成最短路程的派送。
 * 假设如图派送员的起点坐标(0,0)，派送路线只能沿着图中的方格边行驶，每个小格都是正方形，且边长为1，如p到d的距离就是4。
 * 随机输入n个派送点坐标，求输出最短派送路线值（从起点开始完成n个点派送并回到起始点的距离）。
 */
public class MinPathPlan {

    static class Point {
        private int x;
        private int y;
        private boolean isVisited;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.isVisited = false;
        }

        public int distance(Point x) {
            return Math.abs(this.x - x.x) + Math.abs(this.y - x.y);
        }
    }

    static Point  StartPoint = new Point(0, 0);
    static int minPath = Integer.MAX_VALUE;

    public static int caculate(Point p, Point[] points, int totalPath, int count) {

        if (points.length == count) {
            minPath = Math.min(minPath, totalPath + p.distance(StartPoint));
            return minPath;
        }

        for (Point point : points) {

            if (!point.isVisited) {
                totalPath += point.distance(p);
                if (totalPath < minPath) {
                    point.isVisited = true;
                    caculate(point, points, totalPath, count + 1);
                }
            }
            totalPath -= point.distance(p);
            point.isVisited = false;
        }

        return minPath;
    }


    public static void main(String[] args) {
        int totalLen = 0, count = 0;
        Point startPoint = new Point(0, 0);
        Point[] points = {new Point(1, 4), new Point(2, 2), new Point(3, 1), new Point(5, 3)};
        int minLenth = caculate(startPoint, points, 0, 0);
        System.out.println(minLenth);

    }








}
