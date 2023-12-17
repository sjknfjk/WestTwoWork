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

    public static void update(int id1, int id2, String name, double price) {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "update goods set goodsId=?,goodsName=?,price=? where goodsId=?";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, id2);
            pst.setString(2, name);
            pst.setDouble(3, price);
            pst.setInt(4, id1);

            int i = pst.executeUpdate();
            if (i > 0) {
                System.out.println("更改商品信息成功!");
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

    public static void select(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from goods ";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("goodsId"));
                System.out.println(rs.getString("goodsName"));
                System.out.println(rs.getDouble("price"));
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("未找到该商品");
            e.printStackTrace();
        } finally {
            orderManage.release(conn, pst, rs);
        }
    }

    public static void orderByPrice(){
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs=null;

        try {
            conn = orderManage.getConnection();
            conn.setAutoCommit(false);
            String sql = "select * from goods order by price";
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getInt("goodsId"));
                System.out.println(rs.getString("goodsName"));
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
            orderManage.release(conn, pst, rs);
        }
    }
}
