package com.hrh.work3;

import java.sql.*;

public class Orders {
    public static void insert(int id, int num, int goodsId, Date date, double price) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into orders(ordersId,num,goodsId,time,price)value(?,?,?,?,?)";
            pst = conn.prepareStatement(sql);

            if (id <0||price<0) {
                System.out.println("输入不合法");
            }else {
                pst.setInt(1, id);
                pst.setInt(2, num);
                pst.setInt(3, goodsId);
                pst.setDate(4, date);
                pst.setDouble(5, price);

                int i = pst.executeUpdate();
                if (i > 0) {
                    System.out.println("下单成功!");
                }
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("订单不合法");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, null);
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from orders where ordersId=?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);

            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("取消订单成功!");
            } else if (i == 0) {
                System.out.println("没有该商品");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("订单不合法");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, null);
        }
    }

    public static void update(int id, int num, int goodsId, Date date, double price) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "update orders set num=?,goodsId=?,time=?,price=? where ordersId=?";
            pst = conn.prepareStatement(sql);

            if (price<0) {
                System.out.println("价格不合法");
            }else {
                pst.setInt(1, num);
                pst.setInt(2, goodsId);
                pst.setDate(3, date);
                pst.setDouble(4, price);
                pst.setInt(5, id);

                int i = pst.executeUpdate();
                if (i > 0) {
                    System.out.println("更改商品信息成功!");
                } else if (i == 0) {
                    System.out.println("商品id不合法");
                }
                conn.commit();
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("商品不合法");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, null);
        }
    }

    public static void select(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from orders where ordersId=?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("ordersId") + " ");
                System.out.print(rs.getInt("num") + " ");
                System.out.print(rs.getInt("goodsId") + " ");
                System.out.print(rs.getDate("time") + " ");
                System.out.println(rs.getDouble("price") + " ");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("未找到该订单");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, rs);
        }
    }

    public static void orderByPrice() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from orders order by price";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("ordersId") + " ");
                System.out.print(rs.getInt("num") + " ");
                System.out.print(rs.getInt("goodsId") + " ");
                System.out.print(rs.getDate("time") + " ");
                System.out.println(rs.getDouble("price") + " ");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("未找到该订单");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, rs);
        }
    }

    public static void orderByTime() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = OrderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from orders order by TIME";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("ordersId") + " ");
                System.out.print(rs.getInt("num") + " ");
                System.out.print(rs.getInt("goodsId") + " ");
                System.out.print(rs.getDate("time") + " ");
                System.out.println(rs.getDouble("price"));
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("未找到该订单");
            e.printStackTrace();
        } finally {
            OrderManage.release(conn, pst, rs);
        }
    }
}
