package com.github.pieter_groenendijk.repository;
import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import java.util.Collections;
import com.github.pieter_groenendijk.model.product.MediaType;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;

import java.util.Optional;

public class ProductRepository implements IProductRepository {

    SessionFactory sessionFactory;

    public ProductRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ProductTemplate store(ProductTemplate product) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(product);
            session.flush();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;

    }


    @Override
    public Optional<ProductTemplate> deleteProductById(long productId) {
        Session session = null;
        ProductTemplate product = null;

        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            product = session.get(ProductTemplate.class, productId);
            if (product != null) {
                session.remove(product);
                session.getTransaction().commit();
            } else {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(product);

    }

    @Override
    public Optional<ProductTemplate> retrieveProductById(long productId) {
        Session session = sessionFactory.openSession();
        ProductTemplate product;

        try {
            product = session.get(ProductTemplate.class, productId);
        } finally {
            session.close();
        }
        return Optional.ofNullable(product);
    }


    @Override
    public ProductTemplate updateProduct(ProductTemplate product) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return product;
    }

    public ProductCopy updateProductCopy(ProductCopy productCopy) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.merge(productCopy);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productCopy;
    }

    public List<ProductCopy> retrieveCatalogue(String searchString,
                                               long genreId,
                                               boolean onlyAvailableProducts,
                                               MediaType mediaType) {
        List<ProductCopy> catalogue;
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder(
                    "FROM ProductCopy pc " +
                    "JOIN ProductTemplate pt ON pc.physicalProduct.productId = pt.productId " +
                    "WHERE 1=1");

            if (searchString != null && !searchString.isEmpty()) {
                hql.append(" AND (pt.name LIKE :searchString OR pt.description LIKE :searchString)");
            }
            if (genreId > 0) {
                hql.append(" AND pt.genre.id = :genreId");
            }
            if (onlyAvailableProducts) {
                hql.append(" AND pc.availabilityStatus = 'AVAILABLE'");
            }
            if (mediaType != null && mediaType != MediaType.NONE) {
                hql.append(" AND pt.mediaType = :mediaType");
            }

            Query<ProductCopy> query = session.createQuery(hql.toString(), ProductCopy.class);

            if (searchString != null && !searchString.isEmpty()) {
                query.setParameter("searchString", "%" + searchString + "%");
            }
            if (genreId > 0) {
                query.setParameter("genreId", genreId);
            }
            if (mediaType != null && mediaType != MediaType.NONE) {
                query.setParameter("mediaType", mediaType.name());
            }

            return catalogue = query.getResultList();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<ProductCopy> retrieveProductCopyById(long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                ProductCopy productCopy = session.get(ProductCopy.class, id);
                transaction.commit();
                return Optional.ofNullable(productCopy);
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}


