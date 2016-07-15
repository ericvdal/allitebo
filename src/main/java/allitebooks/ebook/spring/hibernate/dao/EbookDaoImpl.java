package allitebooks.ebook.spring.hibernate.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SQLCriterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import allitebooks.ebook.spring.hibernate.model.EbookDetail;

@Repository
@Transactional(readOnly = true)
public class EbookDaoImpl implements EbookDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<EbookDetail> getAllEbookDetail() {
		Session session = sessionFactory.openSession();
		
		CriteriaBuilder critBuilder =  session.getCriteriaBuilder();
		critBuilder.createQuery();
		
		Criterion sqlCrit = Restrictions.sqlRestriction("FROM EBOOK_DETAIL");
		
	//	session.
		
		String hql = "FROM EBOOK_DETAIL";
		Query<EbookDetail> query = session.createQuery(hql);
		List<EbookDetail> ebookDetails = (List<EbookDetail>) query.list();
		return ebookDetails;
	}

	@Transactional(readOnly = false)
	@Override
	public void insertEbookDetail(EbookDetail ebookDetail) {
		 Session session = sessionFactory.openSession();
		 session.save(ebookDetail);
	}

}
