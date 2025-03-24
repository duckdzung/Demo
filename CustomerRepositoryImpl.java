import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Customer> searchCustomers(String name, String sex, LocalDate birthdayFrom, LocalDate birthdayTo, Pageable pageable) {
        QCustomer qCustomer = QCustomer.customer;
        BooleanBuilder builder = new BooleanBuilder();

        if (name != null && !name.isEmpty()) {
            builder.and(qCustomer.name.containsIgnoreCase(name));
        }
        if (sex != null && !sex.isEmpty()) {
            builder.and(qCustomer.sex.eq(sex));
        }
        if (birthdayFrom != null) {
            builder.and(qCustomer.birthday.goe(birthdayFrom));
        }
        if (birthdayTo != null) {
            builder.and(qCustomer.birthday.loe(birthdayTo));
        }

        JPAQuery<Customer> query = new JPAQuery<>(entityManager);
        List<Customer> customers = query.from(qCustomer)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qCustomer.name.asc())
                .fetch();

        long total = query.from(qCustomer).where(builder).fetchCount();
        return new PageImpl<>(customers, pageable, total);
    }
}
