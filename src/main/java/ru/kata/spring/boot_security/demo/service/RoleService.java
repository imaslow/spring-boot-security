package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
    public List<Role> getRolesList() {
        return roleDao.getRolesList();
    }
}
