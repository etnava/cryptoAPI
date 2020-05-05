package repository;

import org.springframework.data.repository.CrudRepository;

import com.model.Cryptocurrency;

public interface CryptocurrencyRepository extends CrudRepository<Cryptocurrency, String>{

}
