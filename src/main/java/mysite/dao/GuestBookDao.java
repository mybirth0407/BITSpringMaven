package mysite.dao;

import mysite.config.Config;
import mysite.vo.GuestBookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GuestBookDao {
    @Autowired
    private SqlSession sqlSession;

    public GuestBookVo get(Long no) {
        GuestBookVo guestBookVo = sqlSession.selectOne(
            "guestbook.selectByNo", no);
        return guestBookVo;
    }

    public Long insert(GuestBookVo guestBookVo) {
        sqlSession.insert("guestbook.insert", guestBookVo);
        return guestBookVo.getNo();
    }

    public int delete(Long no, String passwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("no", no);
        map.put("passwd", passwd);
        int count = sqlSession.delete("guestbook.deleteByNoAndPasswd", map);
        return count;
    }

    public List<GuestBookVo> getList() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<GuestBookVo> list = sqlSession.selectList(
            "guestbook.selectList");
        stopWatch.stop();
        System.out.println(
            "execution time GuestBookDao.GetList: " +
            stopWatch.getTotalTimeMillis());
        return list;
    }

    public List<GuestBookVo> getList(Long page) {
        Long p = (page - 1) * 5;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", p);
        map.put("N_LIST", Config.N_LIST);
        List<GuestBookVo> list = sqlSession.selectList(
            "guestbook.selectListByPage", map);
        return list;
    }
}
