package com.example.todolist.sample.dao;

import com.example.todolist.sample.dto.SampleRequestDto;
import com.example.todolist.sample.dto.SampleResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Deprecated
public interface SampleDao {
    void sampleCreate(SampleRequestDto sampleRequestDto);

    SampleResponseDto sampleSearch(Long id);
}
