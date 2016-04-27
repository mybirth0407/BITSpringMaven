package mysite.dao;

import mysite.db.DBConnection;
import mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private DBConnection dbConnection;

    public UserVo get(String email) {
        UserVo userVo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.getConnection();
            String sql = "select no, email " +
                "from user " +
                "where email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userVo = new UserVo();
                userVo.setNo(rs.getLong(1));
                userVo.setEmail(rs.getString(2));
            }
            return userVo;
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

    public UserVo get(Long userNo) {
        UserVo userVo = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "select no, name, email, gender " +
                "from user " +
                "where no = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userNo);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String gender = rs.getString(4);

                userVo = new UserVo();
                userVo.setNo(no);
                userVo.setName(name);
                userVo.setEmail(email);
                userVo.setGender(gender);
            }
            return userVo;
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

    /* 보안 = 인증 + 권한
     * 인증 */
    public UserVo get(UserVo vo) {
        Connection conn = null;
        UserVo userVo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "select no, name, email " +
                "from user " +
                "where email = ? " +
                "and passwd = password(?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, vo.getEmail());
            pstmt.setString(2, vo.getPasswd());

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Long no = rs.getLong(1);
                String name = rs.getString(2);
                String email = rs.getString(3);

                userVo = new UserVo();
                userVo.setNo(no);
                userVo.setName(name);
                userVo.setEmail(email);
            }
            return userVo;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
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

    public void insert(UserVo userVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "insert into user " + "values(null, ?, ?, password" +
                "(?), ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userVo.getName());
            pstmt.setString(2, userVo.getEmail());
            pstmt.setString(3, userVo.getPasswd());
            pstmt.setString(4, userVo.getGender());
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

    public void update(UserVo userVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        System.out.println(userVo);
        try {
            conn = dbConnection.getConnection();
            if ("".equals(userVo.getPasswd())) {
                String sql = "update user " +
                    "set name = ?, gender = ? " +
                    "WHERE no = ?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setString(1, userVo.getName());
                pstmt.setString(2, userVo.getGender());
                pstmt.setLong(3, userVo.getNo());
            }
            else {
                String sql = "update user " +
                    "set name = ?, gender = ?, passwd = password(?) " +
                    "WHERE no = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userVo.getName());
                pstmt.setString(2, userVo.getGender());
                pstmt.setString(3, userVo.getPasswd());
                pstmt.setLong(4, userVo.getNo());
            }

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

    public void delete(UserVo userVo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dbConnection.getConnection();
            String sql = "delete from user " + "where no = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userVo.getNo());
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
}
