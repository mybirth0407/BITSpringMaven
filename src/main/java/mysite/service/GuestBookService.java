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

    public List<GuestBookVo> getList(Long page) {
        System.out.println(guestBookDao.getList(page));
        return guestBookDao.getList(page);
    }

    public GuestBookVo insert(GuestBookVo guestBookVo) {
        Long no = guestBookDao.insert(guestBookVo);
        GuestBookVo retGuestVo = guestBookDao.get(no);
        return retGuestVo;
    }

    public boolean delete(GuestBookVo guestBookVo) {
        boolean ret = true;
        System.out.println(guestBookVo);
        if (guestBookDao.delete(
            guestBookVo.getNo(), guestBookVo.getPasswd()) == 0) {
            ret = false;
        }
        return ret;
    }

    public GuestBookVo get(Long no) {
        return guestBookDao.get(no);
    }
}
