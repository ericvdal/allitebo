package allitebooks.ebook.spring.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allitebooks.ebook.spring.hibernate.dao.EbookDAO;
import allitebooks.ebook.spring.hibernate.model.EbookDetail;

@Service
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookDAO ebookDao;
	
	@Override
	public List<EbookDetail> getAllEbookDetail() {
		List<EbookDetail> ebookDetails = ebookDao.getAllEbookDetail();
		return ebookDetails;
	}

	@Override
	public void insertEbookDetail(EbookDetail ebookDetail) {
		ebookDao.insertEbookDetail(ebookDetail);

	}

}
