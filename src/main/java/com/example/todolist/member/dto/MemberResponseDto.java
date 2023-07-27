package com.example.todolist.member.dto;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private Long memberNo;
    private String memberEmail;
    private String memberNickname;
}
