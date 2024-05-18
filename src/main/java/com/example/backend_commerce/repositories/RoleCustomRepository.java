package com.example.backend_commerce.repositories;

import com.example.backend_commerce.models.Role;
import com.example.backend_commerce.models.User;
import jakarta.persistence.EntityManager;
import org.hibernate.transform.Transformers;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Transformer;
import java.util.List;

@Repository
public class RoleCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getRolesByUserEmail(User user) {

        StringBuilder sql = new StringBuilder()
                .append("SELECT r.`name` as name from users u JOIN users_role ur on u.id = ur.user_id \n" +
                        "JOIN roles r ON r.id = ur.role_id ");

        sql.append(" WHERE 1=1 ");
        if (user.getEmail() != null) {
            sql.append(" AND email = :email");
        }
        NativeQuery<Role> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if (user.getEmail() != null) {
            query.setParameter("email", user.getEmail());
        }
        query.addScalar("name", StandardBasicTypes.STRING);
        query.setResultTransformer(Transformers.aliasToBean(Role.class));
        return query.list();
    }
}
