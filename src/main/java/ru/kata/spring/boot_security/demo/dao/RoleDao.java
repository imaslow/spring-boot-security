package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDao {
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRolesList() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
