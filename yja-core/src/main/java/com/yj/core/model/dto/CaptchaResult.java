package com.yj.core.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证码响应对象
 *
 * @author haoxr
 * @since 2023/03/24
 */
@Schema(description ="验证码响应对象")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptchaResult {

    @Schema(description = "验证码缓存key")
    private String captchaKey;

    @Schema(description = "验证码图片Base64字符串")
    private String captchaBase64;
    @Schema(description = "验证码文本值")

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String text;

}
