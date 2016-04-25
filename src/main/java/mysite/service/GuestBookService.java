package mysite.service;

import mysite.dao.GuestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestBookService {
    @Autowired
    private GuestBookDao guestBookDao;


}
