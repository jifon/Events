package com.eventshub.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ClubRequest {
    @NotBlank
    private String clubName;
    @NotBlank
    private String description;
    @NotBlank
    private MultipartFile multipartFile;

    public ClubRequest(String clubName, String description) {
        this.clubName = clubName;
        this.description = description;
    }
}
