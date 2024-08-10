package com.wangf.spring.caching;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryCustom {

    List<Book> findAllSlow();

    Optional<Book> findOneSlow(String isbn);


}
