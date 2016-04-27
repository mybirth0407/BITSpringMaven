package mysite.service;

import mysite.dao.GuestBookDao;
import mysite.vo.GuestBookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class GuestBookService {
    @Autowired
    private GuestBookDao guestBookDao;

    public List<GuestBookVo> getList() throws SQLException{
        return guestBookDao.getList();
    }

    public void insert(GuestBookVo guestBookVo) {
        guestBookDao.insert(guestBookVo);
    }

    public void delete(GuestBookVo guestBookVo) {
        guestBookDao.delete(guestBookVo.getNo(), guestBookVo.getPasswd());
    }

    public GuestBookVo get(Long no) {
        return guestBookDao.get(no);
    }

}
