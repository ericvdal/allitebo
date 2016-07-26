package allitebooks.ebook.spring.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import allitebooks.ebooks.spring.model.EbookDetail;

@Service(value="ebookRepository")
public interface EbookRepository extends MongoRepository<EbookDetail, String>{

    public EbookDetail findByTitle(String title);
    
    public List<EbookDetail> findByCategories(String category);
    
	public List<EbookDetail> findByOrderByTitleAsc();
    
}
