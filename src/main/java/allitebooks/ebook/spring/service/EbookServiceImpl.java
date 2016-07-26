package allitebooks.ebook.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import allitebooks.ebook.spring.dao.EbookDAO;
import allitebooks.ebook.spring.dao.EbookRepository;
import allitebooks.ebooks.spring.model.EbookDetail;

@Service
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookDAO ebookDao;
	
	@Autowired
	private EbookRepository repository;
	
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
