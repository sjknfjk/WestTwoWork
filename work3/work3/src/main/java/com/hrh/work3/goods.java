package com.hrh.work3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class goods {
    public static void insert(int id, String name, double price) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into goods(goodsId,goodsName,price)value(?,?,?)";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setDouble(3, price);

            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("上架商品成功!");
            } else if (i == 0) {
                System.out.println("商品不合法");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("商品不合法");
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, null);
        }
    }

    public static void delete(int id) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "delete from goods where goodsId=?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);


            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("下架商品成功!");
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
            System.out.println("商品不合法");
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, null);
        }
    }

    public static void update(int id, String name, double price) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "update goods set goodsName=?,price=? where goodsId=?";
            pst = conn.prepareStatement(sql);

            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, id);

            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("更改商品信息成功!");
            } else if (i == 0) {
                System.out.println("商品不合法");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("商品不合法");
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, null);
        }
    }

    public static void select(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from goods where goodsId=?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getInt("goodsId") + " ");
                System.out.print(rs.getString("goodsName") + " ");
                System.out.println(rs.getDouble("price") + " ");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, rs);
        }
    }

    public static void orderByPrice() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from goods order by price";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getInt("goodsId") + " ");
                System.out.print(rs.getString("goodsName") + " ");
                System.out.println(rs.getDouble("price") + " ");
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, rs);
        }
    }
}
