package com.wangf.spring.mapper;

import com.wangf.spring.dto.BookDto;
import com.wangf.spring.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDTO(Book book);


    List<BookDto> toDTOList(List<Book> books);


}