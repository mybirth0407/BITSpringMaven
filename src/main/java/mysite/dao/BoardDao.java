package mysite.dao;

import mysite.db.DBConnection;
import mysite.vo.BoardVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private DBConnection dbConnection;

    public BoardDao(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public BoardVo get(Long boardNo) {
        BoardVo boardVo = new BoardVo();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "select * " +
                "from board b " +
                "where b.no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, boardNo);

            rs = pstmt.executeQuery();

            if (rs.next() == true) {
                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String regData = rs.getString(4);
                Long userNo = rs.getLong(5);
                Long groupNo = rs.getLong(6);
                Long orderNo = rs.getLong(7);
                Long depth = rs.getLong(8);
                Long viewCount = rs.getLong(9);

                boardVo.setNo(no);
                boardVo.setTitle(title);
                boardVo.setContent(content);
                boardVo.setRegDate(regData);
                boardVo.setUserNo(userNo);
                boardVo.setGroupNo(groupNo);
                boardVo.setOrderNo(orderNo);
                boardVo.setDepth(depth);
                boardVo.setViewCount(viewCount);
            }
            else {
                return null;
            }
            return boardVo;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshOrder(Long groupNo, Long orderNo) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "select no, order_no " +
                "from board " +
                "where group_no = ? " +
                "and order_no >= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, groupNo);
            pstmt.setLong(2, orderNo);
            rs = pstmt.executeQuery();

            while (true) {
                if (rs.next() == false) {
                    break;
                }
                stmt = conn.createStatement();
                sql = "update board " +
                    "set order_no = " + "rs.getLong(2) + 1 " +
                    "where no = " + "rs.getLong(1)";
                stmt.executeUpdate(sql);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshViewCount(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "update board " +
                "set view_count = view_count + 1 " +
                "where no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(BoardVo boardVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "insert into board " +
                "values(null, ?, ?, now(), ?, " +
                "(select ifnull(max(group_no), 0) + 1 from board b), " +
                "1, 0, 0)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, boardVo.getTitle());
            pstmt.setString(2, boardVo.getContent());
            pstmt.setLong(3, boardVo.getUserNo());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long no) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "delete from board " + "where no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void modify(BoardVo boardVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "update board " +
                "set title = ?, content = ?, reg_date = now() " +
                "WHERE no = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, boardVo.getTitle());
            pstmt.setString(2, boardVo.getContent());
            pstmt.setLong(3, boardVo.getNo());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void reply(BoardVo boardVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "insert into board " +
                "values(null, ?, ?, now(), ?, ?, ?, ?, 0)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, boardVo.getTitle());
            pstmt.setString(2, boardVo.getContent());
            pstmt.setLong(3, boardVo.getUserNo());
            pstmt.setLong(4, boardVo.getGroupNo());

            refreshOrder(boardVo.getGroupNo(), boardVo.getOrderNo());

            pstmt.setLong(5, boardVo.getOrderNo());
            pstmt.setLong(6, boardVo.getDepth());

            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BoardVo> getList() {
        List<BoardVo> list = new ArrayList<BoardVo>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "select b.*, u.name " +
                "from board b, user u " +
                "where b.user_no = u.no " +
                "order by b.group_no desc, b.order_no asc";
            rs = stmt.executeQuery(sql);

            while (true) {
                if (rs.next() == false) {
                    break;
                }

                Long no = rs.getLong(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String regDate = rs.getString(4);
                Long userNo = rs.getLong(5);
                Long groupNo = rs.getLong(6);
                Long orderNo = rs.getLong(7);
                Long depth = rs.getLong(8);
                Long viewCount = rs.getLong(9);
                String userName = rs.getString(10);

                BoardVo boardVo = new BoardVo();
                boardVo.setNo(no);
                boardVo.setTitle(title);
                boardVo.setContent(content);
                boardVo.setRegDate(regDate);
                boardVo.setUserNo(userNo);
                boardVo.setGroupNo(groupNo);
                boardVo.setOrderNo(orderNo);
                boardVo.setDepth(depth);
                boardVo.setViewCount(viewCount);
                boardVo.setUserName(userName);

                list.add(boardVo);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<BoardVo> pageGetList(
        String keyword, long page, long N_LIST) {
        List<BoardVo> list = new ArrayList<BoardVo>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql;

            if (keyword == null) {
                sql = "select b.no, b.title, u.no " +
                    "as user_no, u.name, b.view_count, " +
                        "b.reg_date, b.depth " +
                    "from board b, user u " +
                    "where b.user_no = u.no " + "" +
                    "order by b.group_no desc, b.order_no asc " +
                    "limit ?, " + N_LIST;
                pstmt = conn.prepareStatement(sql);
            }
            else {
                sql = "select distinct b.no, b.title, u.no " +
                    "as user_no, u.name, b.view_count, b.reg_date, " +
                        "b.depth, b.group_no, b.order_no " +
                    "from board b, user u " +
                    "where b.user_no = u.no " +
                        "and (title like '%" + keyword + "%' " +
                        "or content like '%" + keyword + "%') " +
                    "order by b.group_no desc, b.order_no asc " +
                    "limit ?, " + N_LIST;
                pstmt = conn.prepareStatement(sql);
            }
            pstmt.setLong(1, (page - 1) * N_LIST);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long boardNo = rs.getLong(1);
                String title = rs.getString(2);
                Long userNo = rs.getLong(3);
                String userName = rs.getString(4);
                Long viewCount = rs.getLong(5);
                String regDate = rs.getString(6);
                Long depth = rs.getLong(7);

                BoardVo vo = new BoardVo();
                vo.setNo(boardNo);
                vo.setTitle(title);
                vo.setUserNo(userNo);
                vo.setUserName(userName);
                vo.setViewCount(viewCount);
                vo.setRegDate(regDate);
                vo.setDepth(depth);

                list.add(vo);
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return list;

    }

    public long searchCount(String keyword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long count = 0;

        try {
            conn = dbConnection.getConnection();

            String sql;
            if (keyword == null || "".equals(keyword)) {
                sql = "select count(*) " +
                    "from board";
            }
            else {
                sql = "select count(*) " +
                    "from board " +
                    "where title like '%" + keyword + "%'" +
                        "or content like '%" + keyword + "%'";
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getLong(1);
            }
            return count;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
