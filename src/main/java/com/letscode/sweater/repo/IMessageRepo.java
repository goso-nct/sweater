package com.letscode.sweater.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.letscode.sweater.domain.Message;

public interface IMessageRepo extends CrudRepository<Message, Long>{
	
	List<Message> findByTag(String tag);

}
