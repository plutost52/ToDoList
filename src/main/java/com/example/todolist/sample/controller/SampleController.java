package com.example.todolist.sample.controller;

import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.sample.dto.SampleRequestDto;
import com.example.todolist.sample.dto.SampleResponseDto;
import com.example.todolist.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping(value = "/sample")
    public ResponseMessage<String> createSample(@RequestBody SampleRequestDto sampleRequestDto){
        sampleService.createSample(sampleRequestDto);
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

    @GetMapping(value = "/sample/{id}")
    public ResponseMessage<String> searchSample(@PathVariable Long id){
        System.out.println("check searching");
        SampleResponseDto sampleResponseDto = sampleService.searchSample(id);
        return new ResponseMessage(MessageCode.SUCCESS, sampleResponseDto);
    }

    @GetMapping(value = "/sample/error")
    public ResponseMessage<String> errorSample(){
        sampleService.errorSample();
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

}
