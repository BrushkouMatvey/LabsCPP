package com.Repository;
import com.Entity.Cache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CacheRepository extends CrudRepository<Cache,Integer> {
    List<Cache> findAll();
    List<Cache> getAllByResponceId(int id);
}
