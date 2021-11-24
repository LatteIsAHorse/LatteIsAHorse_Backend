package team.latte.LatteIsAHorse.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.latte.LatteIsAHorse.model.user.LatteStackInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllLatteStackInfoRes {

    @JsonIgnore
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    private Long latteStackInfoId;

    private String date;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long quizId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long couponListId;

    private String title;

    private int deltaStack;

    private String reason;

    public static AllLatteStackInfoRes of(LatteStackInfo latteStackInfo) {

        if (latteStackInfo.getCouponList() != null) {
            return AllLatteStackInfoRes.builder()
                    .latteStackInfoId(latteStackInfo.getLatteStackInfoId())
                    .date(formatter.format(latteStackInfo.getCreatedAt()))
                    .couponListId(latteStackInfo.getCouponList().getCouponListId())
                    .title(latteStackInfo.getCouponList().getCoupon().getFranchisee().getName())
                    .deltaStack(latteStackInfo.getDeltaStack())
                    .reason(latteStackInfo.getReason().getDesc())
                    .build();
        }
        return AllLatteStackInfoRes.builder()
                .latteStackInfoId(latteStackInfo.getLatteStackInfoId())
                .date(formatter.format(latteStackInfo.getCreatedAt()))
                .quizId(latteStackInfo.getQuiz().getQuizId())
                .title(latteStackInfo.getQuiz().getTitle())
                .deltaStack(latteStackInfo.getDeltaStack())
                .reason(latteStackInfo.getReason().getDesc())
                .build();
    }

    public static List<AllLatteStackInfoRes> listOf(List<LatteStackInfo> latteStackInfos) {
        return latteStackInfos.stream().map(AllLatteStackInfoRes::of)
                .collect(Collectors.toList());
    }
}
