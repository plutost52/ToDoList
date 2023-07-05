package com.example.todolist.sample.service;

import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.sample.dao.SampleDao;
import com.example.todolist.sample.dto.SampleRequestDto;
import com.example.todolist.sample.dto.SampleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SampleService {

    private final SampleDao sampleDao;
    public void createSample(SampleRequestDto sampleRequestDto) {

        sampleDao.sampleCreate(sampleRequestDto);
    }

    public SampleResponseDto searchSample(Long id) {
        return sampleDao.sampleSearch(id);
    }

    public void errorSample() {
        throw new CustomException(ErrorCode.SERVER_ERROR);
    }
}
